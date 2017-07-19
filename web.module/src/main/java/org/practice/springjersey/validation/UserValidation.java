package org.practice.springjersey.validation;

import lombok.extern.slf4j.Slf4j;
import org.practice.springjersey.domain.User;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Component
@Constraint(validatedBy = UserValidation.Validation.class)
public @interface UserValidation {

    String message() default "{}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Slf4j
    @Component
    public class Validation implements ConstraintValidator<UserValidation, User> {

        static final String FIRST_LAST_NAME_ERROR_MSG = "First name and Last name should not be same";

        @Override
        public void initialize(UserValidation userValidation) {
            // nothing required as of now
        }

        @Override
        public boolean isValid(User user, ConstraintValidatorContext context) {
            boolean isValid = true;


            if(StringUtils.isEmpty(user.getFirstName()) && StringUtils.isEmpty(user.getLastName())
                    && user.getFirstName().equalsIgnoreCase(user.getLastName())){

                log.error("First and Last name same for user {}", user);

                context.buildConstraintViolationWithTemplate(FIRST_LAST_NAME_ERROR_MSG).addConstraintViolation();
                isValid = false;
            }

            if(!isValid){
                context.disableDefaultConstraintViolation();
            }
            return isValid;
        }


    }

}

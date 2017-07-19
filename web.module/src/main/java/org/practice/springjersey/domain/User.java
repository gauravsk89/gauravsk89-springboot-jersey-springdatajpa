package org.practice.springjersey.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "USER_DETAIL")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @NotNull(message = "First name is required")
    @Length(min = 1, message = "First name is required")
    @Column(name = "FIRST_NAME")
    private String firstName;

    @NotNull(message = "Last name is required")
    @Length(min = 1, message = "Last name is required")
    @Column(name = "LAST_NAME")
    private String lastName;

}

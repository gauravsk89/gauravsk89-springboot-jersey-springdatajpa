package org.practice.springjersey.exception;

import lombok.Data;

@Data
public class ApiServiceException extends RuntimeException {

    private String message;
    private String sourceService;
    private int responseCode;

    public ApiServiceException(int responseCode, String sourceService, String message){
        super(message);
        this.sourceService = sourceService;
        this.message = message;
        this.responseCode = responseCode;
    }

}

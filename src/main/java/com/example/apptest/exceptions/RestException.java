package com.example.apptest.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Data
public class RestException extends BusinessException {

    private HttpStatus status = HttpStatus.BAD_REQUEST;

    private ErrorTypeEnum errorType;

    private String message;


    private RestException(ErrorTypeEnum errorTypeEnum) {
        this.errorType = errorTypeEnum;
    }

    private RestException(ErrorTypeEnum errorTypeEnum, String message) {
        this.errorType = errorTypeEnum;
        this.message = message;
    }

    private RestException(ErrorTypeEnum errorTypeEnum, HttpStatus status) {
        this.errorType = errorTypeEnum;
        this.status = status;
    }

    public static RestException restThrow(ErrorTypeEnum errorTypeEnum) {
        return new RestException(errorTypeEnum);
    }

    private RestException(String resourceName, String fieldName, Object fieldValue) {
        message = String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue);
        this.status = HttpStatus.NOT_FOUND;
    }

    public static RestException restThrow(ErrorTypeEnum errorTypeEnum, String message) {
        return new RestException(errorTypeEnum, message);
    }

    public static RestException restThrow(String message) {
        return new RestException(null, message);
    }

    public static RestException restThrow(ErrorTypeEnum errorTypeEnum, HttpStatus status) {
        return new RestException(errorTypeEnum, status);
    }

    public static RestException restThrow(String resourceName, String fieldName, Object fieldValue) {
        return new RestException(resourceName, fieldName, fieldValue);
    }
}

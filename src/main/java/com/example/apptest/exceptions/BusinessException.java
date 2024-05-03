package com.example.apptest.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public abstract class BusinessException extends RuntimeException {

    public abstract ErrorTypeEnum getErrorType();

}
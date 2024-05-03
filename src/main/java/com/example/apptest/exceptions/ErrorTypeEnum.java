package com.example.apptest.exceptions;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorTypeEnum {

    SOMETHING_ERROR(-10),
    USER_NOT_AUTHENTICATED(-20),
    ID_MUST_NOT_BE_ABSENT_REQUEST_BODY(-30),
    ID_MUST_BE_ABSENT_REQUEST_BODY(-40),
    ID_MUST_BE_EQUALS_WITH_REQUEST_BODY(-50),
    ID_MISS_MATCH_IN_PATH_VARIABLE(-60),
    EMPLOYEE_NOT_FOUND(-70),
    ;

    private final Integer code;
}

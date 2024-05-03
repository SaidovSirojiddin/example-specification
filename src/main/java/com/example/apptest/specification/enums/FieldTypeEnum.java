package com.example.apptest.specification.enums;

import com.fasterxml.jackson.databind.JavaType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

import static com.example.apptest.specification.RestConstants.typeFactory;


@Getter
@RequiredArgsConstructor
public enum FieldTypeEnum {

    DROPDOWN(typeFactory.constructType(UUID.class)),
    TEXT(typeFactory.constructType(String.class)),
    CHECKBOX(typeFactory.constructType(Boolean.class)),
    NUMBER(typeFactory.constructType(BigDecimal.class)),
    LOCAL_DATE(typeFactory.constructType(LocalDate.class)),
    LOCAL_DATE_TIME(typeFactory.constructType(LocalDateTime.class)),
    ENUM_DROPDOWN(typeFactory.constructType(Enum.class)),
    ;

    private final JavaType javaType;

    public static Map<Class<?>, FieldTypeEnum> typeEnumMap = Map.of(
            UUID.class, DROPDOWN,
            String.class, TEXT,
            Boolean.class, CHECKBOX,
            BigDecimal.class, NUMBER,
            LocalDate.class, LOCAL_DATE,
            LocalDateTime.class, LOCAL_DATE_TIME,
            Enum.class, ENUM_DROPDOWN
    );
}

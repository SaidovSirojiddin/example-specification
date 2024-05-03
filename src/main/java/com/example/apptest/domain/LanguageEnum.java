package com.example.apptest.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Getter
@RequiredArgsConstructor
public enum LanguageEnum implements Serializable {
    UZ_LAT("uzLat"),
    UZ_CR("uzCr"),
    RU("ru"),
    EN("en");

    private final String value;

}
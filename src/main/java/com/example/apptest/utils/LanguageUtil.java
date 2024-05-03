package com.example.apptest.utils;


import com.example.apptest.domain.LanguageEnum;
import com.example.apptest.domain.LocalaziableData;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;
import java.util.function.Function;

public class LanguageUtil {
    public static Function<LocalaziableData, String> getMessageFunction(LanguageEnum lang) {
        if (lang == null)
            return LocalaziableData::getUzLat;
        return switch (lang) {
            case UZ_LAT -> LocalaziableData::getUzLat;
            case UZ_CR -> LocalaziableData::getUzCr;
            case EN -> LocalaziableData::getEn;
            default -> LocalaziableData::getRu;
        };
    }

    public static Function<LocalaziableData, String> getMessageFunction() {
        LanguageEnum lang = getLanguageEnumFromLocale();
        if (lang == null)
            return LocalaziableData::getUzLat;
        return switch (lang) {
            case UZ_LAT -> LocalaziableData::getUzLat;
            case UZ_CR -> LocalaziableData::getUzCr;
            case EN -> LocalaziableData::getEn;
            default -> LocalaziableData::getRu;
        };
    }

    public static LanguageEnum getLanguageEnumFromLocale() {
        String lang = LocaleContextHolder.getLocale().getLanguage();
        if (lang == null)
            return LanguageEnum.UZ_LAT;
        return switch (lang.toLowerCase(Locale.ROOT)) {
            case "uzlat" -> LanguageEnum.UZ_LAT;
            case "uzcr" -> LanguageEnum.UZ_CR;
            case "en" -> LanguageEnum.EN;
            default -> LanguageEnum.RU;
        };
    }

    private LanguageUtil() {
    }

    public static String getLocale() {
        return LocaleContextHolder.getLocale().getLanguage();
    }
}

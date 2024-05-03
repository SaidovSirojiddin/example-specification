package com.example.apptest.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateDeserializer extends JsonDeserializer<LocalDate> {

    private final DateTimeFormatter formatter;

    public LocalDateDeserializer(String pattern) {
        this.formatter = DateTimeFormatter.ofPattern(pattern);
    }


    @Override
    public LocalDate deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String value = p.getValueAsString();
        return (value != null) ? LocalDate.parse(value, formatter) : null;
    }
}
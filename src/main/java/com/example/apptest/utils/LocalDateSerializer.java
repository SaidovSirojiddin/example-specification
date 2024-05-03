package com.example.apptest.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateSerializer extends JsonSerializer<LocalDate> {

    private final DateTimeFormatter formatter;

    public LocalDateSerializer(String pattern) {
        this.formatter = DateTimeFormatter.ofPattern(pattern);
    }


    @Override
    public void serialize(LocalDate localDate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (localDate != null) {
            String format = formatter.format(localDate);
            jsonGenerator.writeObject(format);
        }
    }
}
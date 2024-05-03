package com.example.apptest.config;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.apptest.utils.LocalDateDeserializer;
import com.example.apptest.utils.LocalDateSerializer;
import com.example.apptest.utils.LocalDateTimeDeserializer;
import com.example.apptest.utils.LocalDateTimeSerializer;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Configuration
public class JacksonConfig {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        String dateTimeFormat = "dd.MM.yyyy HH:mm:ss";
        String dateFormat = "dd.MM.yyyy";
        return builder -> builder
                .simpleDateFormat(dateTimeFormat)
                .serializerByType(LocalDateTime.class, new LocalDateTimeSerializer(dateTimeFormat))
                .deserializerByType(LocalDateTime.class, new LocalDateTimeDeserializer(dateTimeFormat))
                .serializerByType(LocalDate.class, new LocalDateSerializer(dateFormat))
                .deserializerByType(LocalDate.class, new LocalDateDeserializer(dateFormat));

    }
}

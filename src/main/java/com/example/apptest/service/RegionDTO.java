package com.example.apptest.service;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import com.example.apptest.domain.LocalaziableData;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegionDTO implements Serializable {

    private UUID id;

    @NotNull
    private String code;

    @NotNull
    private LocalaziableData localaziableName;

    @NotNull
    private Long sortOrder;

    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    private LocalDateTime updatedAt;
}

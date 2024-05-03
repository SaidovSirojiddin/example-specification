package com.example.apptest.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DepartmentDTO implements Serializable {

    private UUID id;

    private String code;

    @NotNull
    private Long sortOrder;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private DepartmentDTO parent;

}

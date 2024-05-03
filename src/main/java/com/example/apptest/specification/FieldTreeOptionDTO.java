package com.example.apptest.specification;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.apptest.domain.LocalaziableData;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor

public class FieldTreeOptionDTO {

    private UUID id;

    private LocalaziableData localaziableName;

    private String colorCode;

    private UUID parentId;

    private List<FieldTreeOptionDTO> children;
}

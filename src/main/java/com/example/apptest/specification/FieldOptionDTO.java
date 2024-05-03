package com.example.apptest.specification;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.apptest.domain.LocalaziableData;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FieldOptionDTO {

    private UUID id;

    private LocalaziableData localaziableName;

    private String name;

    private String colorCode;

    public static List<FieldOptionDTO> enumsToFieldOptions(Enum<?>[] enums) {
        return Arrays.stream(enums).map(anEnum -> FieldOptionDTO.builder()
                        .name(anEnum.name())
                        .build())
                .collect(Collectors.toList());
    }
}

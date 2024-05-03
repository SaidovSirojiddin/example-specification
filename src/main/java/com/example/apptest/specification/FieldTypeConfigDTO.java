package com.example.apptest.specification;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class FieldTypeConfigDTO {

    private List<FieldOptionDTO> options;

    private List<FieldTreeOptionDTO> treeOptions;

}

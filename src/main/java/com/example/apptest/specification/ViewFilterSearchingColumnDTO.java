package com.example.apptest.specification;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.example.apptest.specification.enums.FieldTypeEnum;

@Data
@EqualsAndHashCode(of = "columnName")
@AllArgsConstructor
@NoArgsConstructor

public class ViewFilterSearchingColumnDTO {

    @NotNull(message = "COLUMN_TYPE_REQUIRED")
    private FieldTypeEnum columnType;

    @NotBlank
    private String columnName;
}

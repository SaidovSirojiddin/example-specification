package com.example.apptest.specification;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.apptest.specification.enums.DateCompareOperatorTypeEnum;
import com.example.apptest.specification.enums.DateFilterTypeEnum;

import java.time.LocalDateTime;
import java.util.UUID;

@JsonInclude(Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor

public class FilterFieldValueDTO {

    private DateFilterTypeEnum dateFilterType;

    private DateCompareOperatorTypeEnum dateCompareOperatorType;

    private LocalDateTime starDate;//12.12.1990 00:00:00

    private LocalDateTime endDate;

    private Integer dateXValue = 0;

    private String minValue;

    private String maxValue;

    private String searchingValue = "";

    private UUID[] optionsSelected;
}

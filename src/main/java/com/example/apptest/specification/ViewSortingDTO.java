package com.example.apptest.specification;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ViewSortingDTO implements Comparable<ViewSortingDTO> {

    @NotBlank(message = "FIELD_REQUIRED")
    private String field;

    private Double orderIndex;

    @NotNull(message = "DIRECTION_REQUIRED")
    private Sort.Direction direction;

    @Override
    public int compareTo(ViewSortingDTO other) {
        return Double.compare(orderIndex, other.orderIndex);
    }
}

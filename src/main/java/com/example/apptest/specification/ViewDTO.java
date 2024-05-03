package com.example.apptest.specification;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.TreeSet;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ViewDTO {

    public static final String INPUT_MUST_BE_POSITIVE_OR_ZERO_ERROR = "10007";
    public static final String INPUT_MUST_BE_POSITIVE = "10008";

    @Valid
    private TreeSet<ViewSortingDTO> sorting;

    @Valid
    private ViewFilterDTO viewFilter;

    @Builder.Default
    @PositiveOrZero(message = INPUT_MUST_BE_POSITIVE_OR_ZERO_ERROR)
    private int page = 0;

    @Builder.Default
    @Positive(message = INPUT_MUST_BE_POSITIVE)
    private int size = 10;

    private TreeSet<ViewColumnDTO> columns;

    @JsonIgnore
    public Sort getReadySorting(List<ViewSortingDTO> sorting) {
        if (CollectionUtils.isEmpty(sorting))
            return Sort.by(Sort.Direction.DESC, "createdAt");

        return Sort.by(getOrders(sorting));
    }

    @JsonIgnore
    private List<Order> getOrders(List<ViewSortingDTO> sorting) {
        return sorting
                .stream()
                .map(viewSortingDTO -> new Order(
                        viewSortingDTO.getDirection(),
                        viewSortingDTO.getField()))
                .toList();
    }


}

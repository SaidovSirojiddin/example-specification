package com.example.apptest.specification;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

public record SortDescriptor(String field, Double orderIndex, Direction direction) {

    @JsonIgnore
    public Order getOrder() {
        return new Order(getDirection(), field);
    }

    @JsonIgnore
    private Direction getDirection() {
        return direction;
    }
}
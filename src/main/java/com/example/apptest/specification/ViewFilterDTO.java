package com.example.apptest.specification;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.apptest.specification.enums.FilterOperatorEnum;

import java.util.List;
import java.util.Set;

@JsonInclude(Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor

public class ViewFilterDTO {

    private FilterOperatorEnum filterOperator;

    private String search;

    @Valid
    private Set<ViewFilterSearchingColumnDTO> searchingColumns;

    @Valid
    private List<FilterFieldDTO> filterFields;

    @SuppressWarnings("unchecked")
    public <E> List<Predicate> getSearchingPredicate(Root<E> genRoot,
                                                     CriteriaBuilder builder,
                                                     SingularAttribute<E, String>... attributes) {
        return QueryUtilsPredicate.search(genRoot, builder, search, attributes);
    }

}

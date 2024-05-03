package com.example.apptest.specification;

import jakarta.persistence.criteria.*;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.apptest.specification.enums.CompareOperatorTypeEnum;
import com.example.apptest.specification.enums.FieldTypeEnum;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

import static com.example.apptest.specification.enums.CompareOperatorTypeEnum.*;
import static com.example.apptest.specification.enums.FieldTypeEnum.*;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class FilterFieldDTO {

    @NotNull(message = "COMPARE_OPERATOR_TYPE_REQUIRED")
    private CompareOperatorTypeEnum compareOperatorType;

    private Double orderIndex;

    @NotBlank(message = "FIELD_REQUIRED")
    private String field;

    @NotNull(message = "FIELD_TYPE_REQUIRED")
    private FieldTypeEnum fieldType;

    private FilterFieldValueDTO value;


    public <E> Predicate getLocalDateTimePredicate(Root<E> genRoot,
                                                   CriteriaBuilder builder,
                                                   String singularAttributeColumn) {
        if (EQ.equals(getCompareOperatorType()))
            return QueryUtilsPredicate.equals(root -> root.get(singularAttributeColumn), genRoot, builder, getValue().getStarDate());
        if (NOT.equals(getCompareOperatorType()))
            return QueryUtilsPredicate.notEquals(root -> root.get(singularAttributeColumn), genRoot, builder, getValue().getStarDate());
        if (GT.equals(getCompareOperatorType()))
            return QueryUtilsPredicate.gt(root -> root.get(singularAttributeColumn), genRoot, builder, getValue().getStarDate());
        if (GTE.equals(getCompareOperatorType()))
            return QueryUtilsPredicate.gte(root -> root.get(singularAttributeColumn), genRoot, builder, getValue().getStarDate());
        if (LT.equals(getCompareOperatorType()))
            return QueryUtilsPredicate.lt(root -> root.get(singularAttributeColumn), genRoot, builder, getValue().getStarDate());
        if (LTE.equals(getCompareOperatorType()))
            return QueryUtilsPredicate.lte(root -> root.get(singularAttributeColumn), genRoot, builder, getValue().getStarDate());
        if (RA.equals(getCompareOperatorType()))
            return QueryUtilsPredicate.between(root -> root.get(singularAttributeColumn), genRoot, builder, getValue().getStarDate(), getValue().getEndDate());
        if (IS_SET.equals(getCompareOperatorType()))
            return QueryUtilsPredicate.isNotNull(root -> root.get(singularAttributeColumn), genRoot, builder);
        if (IS_NOT_SET.equals(getCompareOperatorType()))
            return QueryUtilsPredicate.isNull(root -> root.get(singularAttributeColumn), genRoot, builder);
        return builder.conjunction();

    }

    public <E> Predicate getLocalDatePredicate(Root<E> genRoot,
                                               CriteriaBuilder builder,
                                               SingularAttribute<E, LocalDate> singularAttributeColumn) {
        if (EQ.equals(getCompareOperatorType()))
            return QueryUtilsPredicate.equals(root -> root.get(singularAttributeColumn), genRoot, builder, getValue().getStarDate().toLocalDate());
        if (NOT.equals(getCompareOperatorType()))
            return QueryUtilsPredicate.notEquals(root -> root.get(singularAttributeColumn), genRoot, builder, getValue().getStarDate().toLocalDate());
        if (GT.equals(getCompareOperatorType()))
            return QueryUtilsPredicate.gt(root -> root.get(singularAttributeColumn), genRoot, builder, getValue().getStarDate().toLocalDate());
        if (GTE.equals(getCompareOperatorType()))
            return QueryUtilsPredicate.gte(root -> root.get(singularAttributeColumn), genRoot, builder, getValue().getStarDate().toLocalDate());
        if (LT.equals(getCompareOperatorType()))
            return QueryUtilsPredicate.lt(root -> root.get(singularAttributeColumn), genRoot, builder, getValue().getStarDate().toLocalDate());
        if (LTE.equals(getCompareOperatorType()))
            return QueryUtilsPredicate.lte(root -> root.get(singularAttributeColumn), genRoot, builder, getValue().getStarDate().toLocalDate());
        if (RA.equals(getCompareOperatorType()))
            return QueryUtilsPredicate.between(root -> root.get(singularAttributeColumn), genRoot, builder, getValue().getStarDate().toLocalDate(), getValue().getEndDate().toLocalDate());
        if (IS_SET.equals(getCompareOperatorType()))
            return QueryUtilsPredicate.isNotNull(root -> root.get(singularAttributeColumn), genRoot, builder);
        if (IS_NOT_SET.equals(getCompareOperatorType()))
            return QueryUtilsPredicate.isNull(root -> root.get(singularAttributeColumn), genRoot, builder);
        return builder.conjunction();

    }

    public <E> Predicate getLocalDatePredicate(Root<E> genRoot,
                                               CriteriaBuilder builder,
                                               String singularAttributeColumn) {
        if (EQ.equals(getCompareOperatorType()))
            return QueryUtilsPredicate.equals(root -> root.get(singularAttributeColumn), genRoot, builder, getValue().getStarDate().toLocalDate());
        if (NOT.equals(getCompareOperatorType()))
            return QueryUtilsPredicate.notEquals(root -> root.get(singularAttributeColumn), genRoot, builder, getValue().getStarDate().toLocalDate());
        if (GT.equals(getCompareOperatorType()))
            return QueryUtilsPredicate.gt(root -> root.get(singularAttributeColumn), genRoot, builder, getValue().getStarDate().toLocalDate());
        if (GTE.equals(getCompareOperatorType()))
            return QueryUtilsPredicate.gte(root -> root.get(singularAttributeColumn), genRoot, builder, getValue().getStarDate().toLocalDate());
        if (LT.equals(getCompareOperatorType()))
            return QueryUtilsPredicate.lt(root -> root.get(singularAttributeColumn), genRoot, builder, getValue().getStarDate().toLocalDate());
        if (LTE.equals(getCompareOperatorType()))
            return QueryUtilsPredicate.lte(root -> root.get(singularAttributeColumn), genRoot, builder, getValue().getStarDate().toLocalDate());
        if (RA.equals(getCompareOperatorType()))
            return QueryUtilsPredicate.between(root -> root.get(singularAttributeColumn), genRoot, builder, getValue().getStarDate().toLocalDate(), getValue().getEndDate().toLocalDate());
        if (IS_SET.equals(getCompareOperatorType()))
            return QueryUtilsPredicate.isNotNull(root -> root.get(singularAttributeColumn), genRoot, builder);
        if (IS_NOT_SET.equals(getCompareOperatorType()))
            return QueryUtilsPredicate.isNull(root -> root.get(singularAttributeColumn), genRoot, builder);
        return builder.conjunction();

    }

    public <T> Predicate getNumberPredicate(Root<T> genRoot,
                                            CriteriaBuilder builder,
                                            String singularAttributeColumn) {
        BigDecimal minValue = value.getMinValue() != null ? new BigDecimal(value.getMinValue()) : null;
        BigDecimal maxValue = value.getMaxValue() != null ? new BigDecimal(value.getMaxValue()) : null;
        if (EQ.equals(getCompareOperatorType()))
            return QueryUtilsPredicate.equals(root -> root.get(singularAttributeColumn), genRoot, builder, minValue);
        if (NOT.equals(getCompareOperatorType()))
            return QueryUtilsPredicate.notEquals(root -> root.get(singularAttributeColumn), genRoot, builder, minValue);
        if (GT.equals(getCompareOperatorType()))
            return QueryUtilsPredicate.gt(root -> root.get(singularAttributeColumn), genRoot, builder, minValue);
        if (GTE.equals(getCompareOperatorType()))
            return QueryUtilsPredicate.gte(root -> root.get(singularAttributeColumn), genRoot, builder, minValue);
        if (LT.equals(getCompareOperatorType()))
            return QueryUtilsPredicate.lt(root -> root.get(singularAttributeColumn), genRoot, builder, minValue);
        if (LTE.equals(getCompareOperatorType()))
            return QueryUtilsPredicate.lte(root -> root.get(singularAttributeColumn), genRoot, builder, minValue);
        if (RA.equals(getCompareOperatorType()))
            return QueryUtilsPredicate.between(root -> root.get(singularAttributeColumn), genRoot, builder, minValue, maxValue);
        if (IS_SET.equals(getCompareOperatorType()))
            return QueryUtilsPredicate.isNotNull(root -> root.get(singularAttributeColumn), genRoot, builder);
        if (IS_NOT_SET.equals(getCompareOperatorType()))
            return QueryUtilsPredicate.isNull(root -> root.get(singularAttributeColumn), genRoot, builder);
        return builder.conjunction();
    }

    public <E> Predicate getBooleanPredicate(Root<E> genRoot,
                                             CriteriaBuilder builder,
                                             String singularAttributeColumn) {
        if (EQ.equals(getCompareOperatorType()))
            return QueryUtilsPredicate.isTrue(root -> root.get(singularAttributeColumn), genRoot, builder);
        else if (NOT.equals(getCompareOperatorType()))
            return QueryUtilsPredicate.isFalse(root -> root.get(singularAttributeColumn), genRoot, builder);
        return builder.conjunction();

    }

    public <E> Predicate getStringPredicate(Root<E> genRoot,
                                            CriteriaBuilder builder,
                                            String singularAttributeColumn) {
        if (EQ.equals(getCompareOperatorType()))
            return QueryUtilsPredicate.like(root -> root.get(singularAttributeColumn), genRoot, builder, value.getSearchingValue());
        else if (NOT.equals(getCompareOperatorType()))
            return QueryUtilsPredicate.notLike(root -> root.get(singularAttributeColumn), genRoot, builder, value.getSearchingValue());
        else if (IS_SET.equals(getCompareOperatorType()))
            return QueryUtilsPredicate.isNotNull(root -> root.get(singularAttributeColumn), genRoot, builder);
        else if (IS_NOT_SET.equals(getCompareOperatorType()))
            return QueryUtilsPredicate.isNull(root -> root.get(singularAttributeColumn), genRoot, builder);
        return builder.conjunction();

    }

    public <E> Predicate getStringPredicate(Root<E> genRoot,
                                            CriteriaBuilder builder,
                                            SingularAttribute<E, String> singularAttributeColumn) {
        if (EQ.equals(getCompareOperatorType()))
            return QueryUtilsPredicate.like(root -> root.get(singularAttributeColumn), genRoot, builder, value.getSearchingValue());
        else if (NOT.equals(getCompareOperatorType()))
            return QueryUtilsPredicate.notLike(root -> root.get(singularAttributeColumn), genRoot, builder, value.getSearchingValue());
        else if (IS_SET.equals(getCompareOperatorType()))
            return QueryUtilsPredicate.isNotNull(root -> root.get(singularAttributeColumn), genRoot, builder);
        else if (IS_NOT_SET.equals(getCompareOperatorType()))
            return QueryUtilsPredicate.isNull(root -> root.get(singularAttributeColumn), genRoot, builder);
        return builder.conjunction();

    }

    public <E, X extends Enum<X>> Predicate getEnumPredicate(Root<E> genRoot,
                                                             CriteriaBuilder builder,
                                                             SingularAttribute<E, X> singularAttributeColumn, Class<X> enumClass) {
        if (EQ.equals(getCompareOperatorType()))
            return QueryUtilsPredicate.equals(root -> root.get(singularAttributeColumn), genRoot, builder, Enum.valueOf(enumClass, value.getSearchingValue()));
        else if (NOT.equals(getCompareOperatorType()))
            return QueryUtilsPredicate.notEquals(root -> root.get(singularAttributeColumn), genRoot, builder, Enum.valueOf(enumClass, value.getSearchingValue()));
        else if (IS_SET.equals(getCompareOperatorType()))
            return QueryUtilsPredicate.isNotNull(root -> root.get(singularAttributeColumn), genRoot, builder);
        else if (IS_NOT_SET.equals(getCompareOperatorType()))
            return QueryUtilsPredicate.isNull(root -> root.get(singularAttributeColumn), genRoot, builder);
        return builder.conjunction();

    }

    public <E> Predicate getDropdownJoinPredicate(Root<E> genRoot,
                                                  CriteriaBuilder builder,
                                                  Map<SingularAttribute<?, ?>, JoinType> joinAttributeColumnsMap,
                                                  SingularAttribute<?, ?> finalAttributeColumn) {
        if (joinAttributeColumnsMap.isEmpty())
            return builder.conjunction();


        Join<?, ?> currentJoin = null;

        SingularAttribute<?, ?> initialAttributeColumn = null;

        for (Map.Entry<SingularAttribute<?, ?>, JoinType> singularAttributeJoinTypeEntry : joinAttributeColumnsMap.entrySet()) {
            if (currentJoin == null) {
                initialAttributeColumn = singularAttributeJoinTypeEntry.getKey();
                currentJoin = genRoot.join(singularAttributeJoinTypeEntry.getKey().getName(), singularAttributeJoinTypeEntry.getValue());
            } else
                currentJoin = currentJoin.join(singularAttributeJoinTypeEntry.getKey().getName(), singularAttributeJoinTypeEntry.getValue());
        }

        Join<?, ?> finalCurrentJoin = currentJoin;
        Function<Root<E>, ? extends Expression<UUID>> function = root -> finalCurrentJoin.get(finalAttributeColumn.getName());
        UUID[] optionsSelected = getValue().getOptionsSelected();

        SingularAttribute<?, ?> finalInitialAttributeColumn = initialAttributeColumn;

        if (EQ.equals(compareOperatorType)) {
            if (optionsSelected == null || optionsSelected.length == 0)
                return builder.conjunction();

            return QueryUtilsPredicate.equals(function, genRoot, builder, optionsSelected[0]);
        } else if (NOT.equals(compareOperatorType)) {
            if (optionsSelected == null || optionsSelected.length == 0)
                return builder.conjunction();

            return QueryUtilsPredicate.notEquals(function, genRoot, builder, optionsSelected[0]);
        } else if (IS_SET.equals(compareOperatorType)) {
            return QueryUtilsPredicate.isNotNull(root -> root.get(finalInitialAttributeColumn.getName()), genRoot, builder);
        } else if (IS_NOT_SET.equals(compareOperatorType))
            return QueryUtilsPredicate.isNull(root -> root.get(finalInitialAttributeColumn.getName()), genRoot, builder);
        return builder.conjunction();

    }

    @SuppressWarnings("unchecked")
    public static <E> Map<String, Function<FilterFieldDTO, Predicate>> generateFunctionByFieldType(Root<E> root,
                                                                                                   CriteriaBuilder criteriaBuilder,
                                                                                                   Map<String, SingularAttribute<?, ?>> singularAttributeMap) {

        Map<String, Function<FilterFieldDTO, Predicate>> predicateMap = new HashMap<>();

        for (Map.Entry<String, SingularAttribute<?, ?>> stringSingularAttributeEntry : singularAttributeMap.entrySet()) {
            Class<?> bindableJavaType = stringSingularAttributeEntry.getValue().getBindableJavaType();

            FieldTypeEnum fieldTypeEnum = FieldTypeEnum.typeEnumMap.get(bindableJavaType);
            if (TEXT.equals(fieldTypeEnum))
                predicateMap.put(stringSingularAttributeEntry.getValue().getName(),
                        filterFieldDTO -> filterFieldDTO.getStringPredicate(root, criteriaBuilder, stringSingularAttributeEntry.getValue().getName()));
            else if (CHECKBOX.equals(fieldTypeEnum))
                predicateMap.put(stringSingularAttributeEntry.getValue().getName(),
                        filterFieldDTO -> filterFieldDTO.getBooleanPredicate(root, criteriaBuilder, stringSingularAttributeEntry.getValue().getName()));
            else if (LOCAL_DATE.equals(fieldTypeEnum))
                predicateMap.put(stringSingularAttributeEntry.getValue().getName(),
                        filterFieldDTO -> filterFieldDTO.getLocalDatePredicate(root, criteriaBuilder, stringSingularAttributeEntry.getValue().getName()));
            else if (LOCAL_DATE_TIME.equals(fieldTypeEnum))
                predicateMap.put(stringSingularAttributeEntry.getValue().getName(),
                        filterFieldDTO -> filterFieldDTO.getLocalDateTimePredicate(root, criteriaBuilder, stringSingularAttributeEntry.getValue().getName()));
            else if (!ENUM_DROPDOWN.equals(fieldTypeEnum) && bindableJavaType == Number.class) {
                predicateMap.put(stringSingularAttributeEntry.getValue().getName(),
                        filterFieldDTO -> filterFieldDTO.getNumberPredicate(root, criteriaBuilder, stringSingularAttributeEntry.getValue().getName()));
            }
        }
        return predicateMap;
    }
}

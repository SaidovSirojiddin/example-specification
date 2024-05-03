package com.example.apptest.repository;


import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.metamodel.Attribute;
import jakarta.persistence.metamodel.SingularAttribute;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import com.example.apptest.domain.*;
import com.example.apptest.specification.FilterFieldDTO;
import com.example.apptest.specification.ViewDTO;
import com.example.apptest.specification.ViewFilterDTO;
import com.example.apptest.specification.ViewSortingDTO;
import com.example.apptest.specification.enums.FilterOperatorEnum;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
public class EmployeeSpecificationRepository {

    private final EmployeeRepository employeeRepository;

    public Page<Employee> getEmployees(ViewDTO viewDTO) {

        Pageable pageable = PageRequest.of(0, 10);
        if (viewDTO != null && viewDTO.getSorting() != null) {
            Set<String> columnNames = allColumns().stream().map(Attribute::getName).collect(Collectors.toSet());

            List<ViewSortingDTO> sortingColumns = viewDTO.getSorting().stream().filter(viewSortingDTO ->
                    columnNames.contains(viewSortingDTO.getField())).toList();

            pageable = PageRequest.of(viewDTO.getPage(), viewDTO.getSize(), viewDTO.getReadySorting(sortingColumns));
        }

        return employeeRepository.findAll(prepareQueryFilter(viewDTO), pageable);
    }

    private Specification<Employee> prepareQueryFilter(ViewDTO viewDTO) {

        Specification<Employee> specification = Specification.where(null);

        if (viewDTO == null)
            return specification;

        return (root, query, criteriaBuilder) -> {

            List<Predicate> filterPredicates = new ArrayList<>();

            List<Predicate> searchPredicates = new ArrayList<>();

            if (Objects.nonNull(viewDTO.getViewFilter())) {
                if (!CollectionUtils.isEmpty(viewDTO.getViewFilter().getFilterFields()))
                    filterPredicates = filterPredicates(root, criteriaBuilder, viewDTO.getViewFilter().getFilterFields());
                if (!CollectionUtils.isEmpty(viewDTO.getViewFilter().getSearchingColumns()))
                    searchPredicates = searchPredicates(root, criteriaBuilder, viewDTO.getViewFilter());

            }

            if (filterPredicates.isEmpty() && searchPredicates.isEmpty())
                return criteriaBuilder.conjunction();

            Predicate filterPredicate;

            if (FilterOperatorEnum.AND.equals(viewDTO.getViewFilter().getFilterOperator()))
                filterPredicate = criteriaBuilder.and(filterPredicates.toArray(new Predicate[]{}));
            else
                filterPredicate = criteriaBuilder.or(filterPredicates.toArray(new Predicate[]{}));

            Predicate searchPredicate = criteriaBuilder.or(searchPredicates.toArray(new Predicate[]{}));

            return criteriaBuilder.and(filterPredicate, searchPredicate);
        };

    }

    @SuppressWarnings("unchecked")
    private List<Predicate> searchPredicates(Root<Employee> root,
                                             CriteriaBuilder criteriaBuilder,
                                             ViewFilterDTO viewFilter) {

        Set<SingularAttribute<Employee, String>> singularAttributeColumns = stringColumns();

        SingularAttribute<Employee, String>[] array = viewFilter.getSearchingColumns()
                .stream()
                .map(viewFilterSearchingColumnDTO -> {
                    Optional<SingularAttribute<Employee, String>> first = singularAttributeColumns.stream().filter(employeeStringSingularAttribute -> employeeStringSingularAttribute.getName().equals(viewFilterSearchingColumnDTO.getColumnName())).findFirst();
                    return first.orElse(null);
                })
                .filter(Objects::nonNull)
                .toArray(SingularAttribute[]::new);

        return viewFilter.getSearchingPredicate(root, criteriaBuilder, array);
    }

    private List<Predicate> filterPredicates(Root<Employee> root,
                                             CriteriaBuilder criteriaBuilder,
                                             List<FilterFieldDTO> filterFields) {

        Map<String, Function<FilterFieldDTO, Predicate>> predicateMap =
                FilterFieldDTO.generateFunctionByFieldType(root, criteriaBuilder, allColumnsMap());

        addMapFilterEnums(root, criteriaBuilder, predicateMap);

        addMapFilterDropdowns(root, criteriaBuilder, predicateMap);

        predicateMap.put(AbstractAuditingEntity_.CREATED_AT, filterFieldDTO -> filterFieldDTO.getLocalDateTimePredicate(root, criteriaBuilder, AbstractAuditingEntity_.CREATED_AT));

        return filterFields.stream()
                .map(filterFieldDTO -> predicateMap.getOrDefault(filterFieldDTO.getField(), f -> null).apply(filterFieldDTO))
                .filter(Objects::nonNull)
                .toList();
    }

    private static void addMapFilterDropdowns(Root<Employee> root, CriteriaBuilder criteriaBuilder, Map<String, Function<FilterFieldDTO, Predicate>> predicateMap) {


        Map<SingularAttribute<?, ?>, JoinType> joinAttributeColumnsMapForRegion = new LinkedHashMap<>();
        joinAttributeColumnsMapForRegion.put(Employee_.registeredDistrict, JoinType.INNER);
        joinAttributeColumnsMapForRegion.put(District_.region, JoinType.INNER);

        predicateMap.put(Employee.REGION, filterFieldDTO ->
                filterFieldDTO.getDropdownJoinPredicate(
                        root,
                        criteriaBuilder,
                        joinAttributeColumnsMapForRegion,
                        AbstractUUIDEntity_.id
                ));


        predicateMap.put(Employee_.REGISTERED_DISTRICT, filterFieldDTO ->
                filterFieldDTO.getDropdownJoinPredicate(
                        root,
                        criteriaBuilder,
                        Map.of(Employee_.registeredDistrict, JoinType.INNER),
                        AbstractUUIDEntity_.id
                ));


        predicateMap.put(Employee_.POSITION, filterFieldDTO ->
                filterFieldDTO.getDropdownJoinPredicate(
                        root,
                        criteriaBuilder,
                        Map.of(Employee_.position, JoinType.LEFT),
                        AbstractUUIDEntity_.id
                ));

        Map<SingularAttribute<?, ?>, JoinType> joinAttributeColumnsMapForDepartment = new LinkedHashMap<>();
        joinAttributeColumnsMapForDepartment.put(Employee_.position, JoinType.INNER);
        joinAttributeColumnsMapForDepartment.put(Position_.department, JoinType.INNER);

        predicateMap.put(Employee.DEPARTMENT, filterFieldDTO ->
                filterFieldDTO.getDropdownJoinPredicate(
                        root,
                        criteriaBuilder,
                        joinAttributeColumnsMapForDepartment,
                        AbstractUUIDEntity_.id
                ));
    }

    private static void addMapFilterEnums(Root<Employee> root, CriteriaBuilder criteriaBuilder, Map<String, Function<FilterFieldDTO, Predicate>> predicateMap) {
        predicateMap.put(Employee_.STATUS, filterFieldDTO ->
                filterFieldDTO.getEnumPredicate(root, criteriaBuilder, Employee_.status, EmployeeStatusEnum.class));
        predicateMap.put(Employee_.GENDER, filterFieldDTO ->
                filterFieldDTO.getEnumPredicate(root, criteriaBuilder, Employee_.gender, GenderEnum.class));

    }


    public static Set<SingularAttribute<Employee, String>> stringColumns() {
        return Set.of(
                Employee_.firstNameLat,
                Employee_.lastNameLat,
                Employee_.middleNameLat,
                Employee_.firstNameCrl,
                Employee_.lastNameCrl,
                Employee_.middleNameCrl,
                Employee_.tin
        );
    }

    public static Set<SingularAttribute<Employee, ?>> allColumns() {
        return Set.of(

        );
    }

    public static Map<String, SingularAttribute<?, ?>> allColumnsMap() {
        Map<String, SingularAttribute<?, ?>> map = new HashMap<>();

        map.put(Employee_.MIDDLE_NAME_CRL, Employee_.middleNameCrl);
        map.put(Employee_.MIDDLE_NAME_LAT, Employee_.middleNameLat);
        map.put(Employee_.GENDER, Employee_.gender);
        map.put(Employee_.FIRST_NAME_CRL, Employee_.firstNameCrl);
        map.put(Employee_.BASE_SALARY, Employee_.baseSalary);
        map.put(Employee_.LAST_NAME_CRL, Employee_.lastNameCrl);
        map.put(Employee_.BIRTH_DATE, Employee_.birthDate);
        map.put(Employee_.FIRST_NAME_LAT, Employee_.firstNameLat);
        map.put(Employee_.LAST_NAME_LAT, Employee_.lastNameLat);
        map.put(Employee_.REGISTERED_DISTRICT, Employee_.registeredDistrict);
        map.put(Employee_.TIN, Employee_.tin);
        map.put(Employee_.DEPUTY_OF_NATION, Employee_.deputyOfNation);
        map.put(Employee_.POSITION, Employee_.position);
        map.put(Employee_.STATUS, Employee_.status);
        return map;
    }


}

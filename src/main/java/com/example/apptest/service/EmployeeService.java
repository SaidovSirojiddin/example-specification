package com.example.apptest.service;

import jakarta.persistence.metamodel.SingularAttribute;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.apptest.domain.Employee;
import com.example.apptest.domain.EmployeeStatusEnum;
import com.example.apptest.domain.Employee_;
import com.example.apptest.domain.GenderEnum;
import com.example.apptest.repository.EmployeeSpecificationRepository;
import com.example.apptest.specification.FieldOptionDTO;
import com.example.apptest.specification.FieldTypeConfigDTO;
import com.example.apptest.specification.ViewColumnDTO;
import com.example.apptest.specification.ViewDTO;
import com.example.apptest.specification.enums.FieldTypeEnum;

import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Service
@Transactional
@Log4j2
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeMapper employeeMapper;
    private final EmployeeSpecificationRepository employeeSpecificationRepository;


    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public ApiResult<Page<EmployeeDTO>> findAll(ViewDTO viewDTO) {

        log.debug("Request to get all Employees body: {}", viewDTO);

        return ApiResult.successResponse(employeeSpecificationRepository.getEmployees(viewDTO).map(employeeMapper::toDto));

    }

    public ApiResult<ViewDTO> getViewDTO() {
        ViewDTO viewDTO = ViewDTO.builder()
                .columns(getColumns())
                .build();
        return ApiResult.successResponse(viewDTO);
    }

    private TreeSet<ViewColumnDTO> getColumns() {
        Set<SingularAttribute<Employee, ?>> allColumns = EmployeeSpecificationRepository.allColumns();

        Set<String> laterUsingColumns = Set.of(
                Employee_.STATUS,
                Employee_.GENDER
        );

        TreeSet<ViewColumnDTO> viewColumnDTOS = allColumns.stream()
                .filter(singularAttribute -> !laterUsingColumns.contains(singularAttribute.getName()))
                .map(column ->
                        ViewColumnDTO.builder()
                                .name(column.getName())
                                .type(FieldTypeEnum.typeEnumMap.get(column.getBindableJavaType()))
                                .build())
                .collect(Collectors.toCollection(TreeSet::new));


        viewColumnDTOS.add(ViewColumnDTO.builder()
                .name(Employee_.GENDER)
                .type(FieldTypeEnum.ENUM_DROPDOWN)
                .typeConfig(FieldTypeConfigDTO.builder()
                        .options(FieldOptionDTO.enumsToFieldOptions(GenderEnum.values()))
                        .build())
                .build());

        viewColumnDTOS.add(ViewColumnDTO.builder()
                .name(Employee.DEPARTMENT)
                .type(FieldTypeEnum.TEXT)
                .build());

        viewColumnDTOS.add(ViewColumnDTO.builder()
                .name(Employee.REGION)
                .type(FieldTypeEnum.TEXT)
                .build());

        viewColumnDTOS.add(ViewColumnDTO.builder()
                .name(Employee_.STATUS)
                .type(FieldTypeEnum.ENUM_DROPDOWN)
                .typeConfig(FieldTypeConfigDTO.builder()
                        .options(FieldOptionDTO.enumsToFieldOptions(EmployeeStatusEnum.values()))
                        .build())
                .build());

        return viewColumnDTOS;
    }
}
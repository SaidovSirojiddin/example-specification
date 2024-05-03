package com.example.apptest.service;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import com.example.apptest.domain.Department;
import com.example.apptest.domain.Employee;


@Mapper(componentModel = "spring")
public interface EmployeeMapper extends EntityMapper<EmployeeDTO, Employee> {

    Employee toEntity(EmployeeDTO dto);

    EmployeeDTO toDto(Employee s);

    @Named("toDtoForOtherService")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "firstNameLat", source = "firstNameLat")
    @Mapping(target = "lastNameLat", source = "lastNameLat")
    @Mapping(target = "middleNameLat", source = "middleNameLat")
    @Mapping(target = "firstNameCrl", source = "firstNameCrl")
    @Mapping(target = "lastNameCrl", source = "lastNameCrl")
    EmployeeDTO toDtoForOtherService(Employee s);

    @Named("toDepartmentDTO")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(source = "id", target = "id")
    @Mapping(source = "parent", target = "parent", qualifiedByName = "toDepartmentDTO")
    DepartmentDTO toDepartmentDTO(Department department);

    @Named("toDepartment")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(source = "id", target = "id")
    @Mapping(target = "code", source = "code")
    @Mapping(source = "sortOrder", target = "sortOrder")
    DepartmentDTO toDtoDepartmentId(Department department);



}

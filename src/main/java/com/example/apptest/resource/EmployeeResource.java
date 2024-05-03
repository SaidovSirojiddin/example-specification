package com.example.apptest.resource;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.apptest.service.ApiResult;
import com.example.apptest.service.BaseResponseDTO;
import com.example.apptest.service.EmployeeDTO;
import com.example.apptest.service.EmployeeService;
import com.example.apptest.specification.ViewDTO;


@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
@Log4j2
public final class EmployeeResource {

    private final EmployeeService employeeService;

    /**
     * {@code POST  /employees} : get all the employees.
     *
     * @param viewDTO the ViewDTO
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of employees in body.
     */
    @PostMapping("/list")
    public BaseResponseDTO<ApiResult<Page<EmployeeDTO>>> getAllEmployees(@RequestBody(required = false) ViewDTO viewDTO) {
        log.debug("REST request to get a page of Employees: {}", viewDTO);
        ApiResult<Page<EmployeeDTO>> result = employeeService.findAll(viewDTO);
        return BaseResponseDTO.ok(result);
    }

    @GetMapping("/view")
    public BaseResponseDTO<ApiResult<ViewDTO>> getViewDTO() {
        ApiResult<ViewDTO> result = employeeService.getViewDTO();
        return BaseResponseDTO.ok(result);
    }
}

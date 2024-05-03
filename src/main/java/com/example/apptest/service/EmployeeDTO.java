package com.example.apptest.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;
import com.example.apptest.domain.EmployeeStatusEnum;
import com.example.apptest.domain.GenderEnum;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeDTO implements Serializable {

    private UUID id;

    private UUID actualProcessId;

    private UUID userId;

    private String username;

    @NotNull
    private String firstNameLat;

    @NotNull
    private String lastNameLat;

    @NotNull
    private String middleNameLat;

    private String firstNameCrl;

    private String lastNameCrl;

    private String middleNameCrl;

    @NotNull(message = "TIN must not be null")
    private String tin;

    @Past
    private LocalDate birthDate;

    private String pinfl;

    private String passportSerial;

    private String passportNumber;

    private String passportIssuedBy;

    private GenderEnum gender;

    private Boolean deputyOfNation;

    @NotNull
    private RegionDTO registeredRegion;

    @NotNull
    private DistrictDTO registeredDistrict;

    private BigDecimal baseSalary;

    private BigDecimal salarySupplement;

    private BigDecimal netSalary;

    private Boolean fixedTermedContract;

    private EmployeeStatusEnum status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}

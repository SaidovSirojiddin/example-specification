package com.example.apptest.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import static com.example.apptest.domain.EmployeeStatusEnum.NOT_ACCEPTED;


/**
 * A Employee.
 */
@Entity
@Table(name = "employee")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@SQLDelete(sql = "UPDATE employee SET deleted = true WHERE id = ?")
@SQLRestriction(value = "deleted = false or deleted is null")
public class Employee extends AbstractUUIDEntity implements Serializable {

    @NotNull
    @Column(name = "first_name_lat", nullable = false)
    private String firstNameLat;

    @NotNull
    @Column(name = "last_name_lat", nullable = false)
    private String lastNameLat;

    @Column(name = "middle_name_lat")
    private String middleNameLat;

    @Column(name = "first_name_crl")
    private String firstNameCrl;

    @Column(name = "last_name_crl")
    private String lastNameCrl;

    @Column(name = "middle_name_crl")
    private String middleNameCrl;

    @Column(name = "tin")
    private String tin;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private GenderEnum gender;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private EmployeeStatusEnum status = NOT_ACCEPTED;

    @Column(name = "deputy_of_nation")
    private Boolean deputyOfNation;

    @ManyToOne
    @JoinColumn(name = "registered_district_id")
    private District registeredDistrict;

    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;

    @Column(name = "base_salary")
    private BigDecimal baseSalary;

    @Transient
    public static final String DEPARTMENT = "department";

    @Transient
    public static final String REGION = "region";
}

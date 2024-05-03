package com.example.apptest.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;

/**
 * A District.
 */
@Entity
@Table(name = "district")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@SQLDelete(sql = "UPDATE district SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false or deleted is null")
public class District extends AbstractUUIDEntity implements Serializable {

    @NotNull
    @Column(name = "code", nullable = false)
    private String code;

    @NotNull
    @Column(name = "localaziable_name", nullable = false)
    @JdbcTypeCode(value = SqlTypes.JSON)
    private LocalaziableData localaziableName;

    @NotNull
    @Column(name = "sort_order", nullable = false)
    private Long sortOrder;

    @ManyToOne(optional = false)
    @NotNull
    private Region region;
}

package com.example.apptest.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;

/**
 * A Position.
 */
@Entity
@Table(name = "position")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@SQLDelete(sql = "UPDATE position SET deleted = true WHERE id = ?")
@SQLRestriction(value = "deleted = false or deleted is null")
public class Position extends AbstractUUIDEntity implements Serializable {

    @NotNull
    @Column(name = "localaziable_name", nullable = false)
    @JdbcTypeCode(value = SqlTypes.JSON)
    private LocalaziableData localaziableName;

    @NotNull
    @Column(name = "sort_order", nullable = false)
    private Long sortOrder;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
}

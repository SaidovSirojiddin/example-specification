package com.example.apptest.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.io.Serializable;

/**
 * A Department.
 */
@Entity
@Table(name = "department")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@SQLDelete(sql = "UPDATE department SET deleted = true WHERE id = ?")
@SQLRestriction(value = "deleted = false or deleted is null")
public class Department extends AbstractUUIDEntity implements Serializable {

    @NotNull
    @Column(name = "sort_order", nullable = false)
    private Long sortOrder;

    @Column(name = "code")
    private String code;

    @ManyToOne(fetch = FetchType.LAZY)
    private Department parent;

    @ManyToOne
    private Employee curator;
}

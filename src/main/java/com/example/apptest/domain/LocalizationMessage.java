package com.example.apptest.domain;

import com.example.apptest.exceptions.ErrorTypeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "localization_message")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
@SQLDelete(sql = "UPDATE localization_message SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false or deleted is null")
public class LocalizationMessage extends AbstractUUIDEntity {

    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private ErrorTypeEnum errorType;

    @Column(nullable = false, name = "code")
    private Integer code;

    @Column(nullable = false, name = "localaziable_data")
    @JdbcTypeCode(SqlTypes.JSON)
    private LocalaziableData localaziableData;
}
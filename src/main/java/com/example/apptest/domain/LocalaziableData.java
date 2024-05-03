package com.example.apptest.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LocalaziableData implements Serializable {

    private String uzLat;

    private String uzCr;

    private String ru;

    private String en;


    public static List<String> getSingleMessages(List<LocalaziableData> localaziableEntities, Function<LocalaziableData, String> function) {
        if (localaziableEntities != null)
            return localaziableEntities
                    .stream()
                    .map(localizedMessage -> getSingleMessage(localizedMessage, function))
                    .filter(Objects::nonNull)
                    .toList();

        return Collections.emptyList();
    }

    public static String getSingleMessage(LocalaziableData localaziableEntity, Function<LocalaziableData, String> function) {
        if (localaziableEntity != null) {
            return function.apply(localaziableEntity);
        }
        return null;
    }
}

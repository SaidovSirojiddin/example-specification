package com.example.apptest.specification;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import com.example.apptest.specification.enums.FieldTypeEnum;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@EqualsAndHashCode(of = "name")
@AllArgsConstructor
@NoArgsConstructor

public class ViewColumnDTO implements Comparable<ViewColumnDTO> {

    private String name;

    private boolean pinned;

    private boolean hidden;

    private Double orderIndex;

    private Double pinnedOrderIndex;

    private FieldTypeEnum type;

    private FieldTypeConfigDTO typeConfig;

    private boolean enabled;

    private boolean filterable;

    private boolean searchable;

    private boolean sortable;

    private boolean root;

    private Boolean showColumn;

    private Boolean searchingColumn;

    private boolean moveColumn;

    @Override
    public int compareTo(ViewColumnDTO other) {
        return name.compareTo(other.name);
    }


}


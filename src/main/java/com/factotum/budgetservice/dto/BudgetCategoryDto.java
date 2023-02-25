package com.factotum.budgetservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BudgetCategoryDto {

    @NotNull
    @JsonProperty("id")
    private UUID id;

    @JsonProperty("type")
    private String typeName;

    @JsonProperty("name")
    private String name;

    @JsonProperty("budgetItems")
    private List<BudgetItemDto> budgetItems = new LinkedList<>();

    public BudgetCategoryDto(UUID id, String typeName, String name) {
        this.id = id;
        this.typeName = typeName;
        this.name = name;
    }

}

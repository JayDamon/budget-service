package com.factotum.rin.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BudgetCategoryDto {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("type")
    private String typeName;

    @JsonProperty("name")
    private String name;

    @JsonProperty("budgetItems")
    private Set<BudgetItemDto> budgetItems;

    public BudgetCategoryDto() {
    }

    public BudgetCategoryDto(Integer id, String typeName, String name) {
        this.id = id;
        this.typeName = typeName;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<BudgetItemDto> getBudgetItems() {
        return budgetItems;
    }

    public void setBudgetItems(Set<BudgetItemDto> budgetItems) {
        this.budgetItems = budgetItems;
    }
}

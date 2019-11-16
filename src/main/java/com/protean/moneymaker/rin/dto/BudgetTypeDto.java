package com.protean.moneymaker.rin.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashSet;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BudgetTypeDto {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("type")
    private String type;

    @JsonProperty("budgetCategories")
    private Set<BudgetCategoryDto> budgetCategories = new HashSet<>();

    public BudgetTypeDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<BudgetCategoryDto> getBudgetCategories() {
        return budgetCategories;
    }

    public void setBudgetCategories(Set<BudgetCategoryDto> budgetCategories) {
        this.budgetCategories = budgetCategories;
    }

}

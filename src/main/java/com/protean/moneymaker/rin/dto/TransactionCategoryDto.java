package com.protean.moneymaker.rin.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionCategoryDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("subCategory")
    private BudgetSubCategoryDto budgetSubCategory;

    public TransactionCategoryDto() {
    }

    public TransactionCategoryDto(Long id, String name, BudgetSubCategoryDto budgetSubCategory) {
        this.id = id;
        this.name = name;
        this.budgetSubCategory = budgetSubCategory;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BudgetSubCategoryDto getBudgetSubCategory() {
        return budgetSubCategory;
    }

    public void setBudgetSubCategory(BudgetSubCategoryDto budgetSubCategory) {
        this.budgetSubCategory = budgetSubCategory;
    }
}

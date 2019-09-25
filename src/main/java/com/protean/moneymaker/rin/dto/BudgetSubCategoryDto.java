package com.protean.moneymaker.rin.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BudgetSubCategoryDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    public BudgetSubCategoryDto() {
    }

    public BudgetSubCategoryDto(Long id, String name, String transactionTypeName) {
        this.id = id;
        this.name = name;
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

}

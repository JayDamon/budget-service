package com.protean.moneymaker.rin.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BudgetItemDto {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("category")
    private String categoryName;

    @JsonProperty("name")
    private String name;

    public BudgetItemDto() {
    }

    public BudgetItemDto(Integer id, String categoryName, String name) {
        this.id = id;
        this.categoryName = categoryName;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

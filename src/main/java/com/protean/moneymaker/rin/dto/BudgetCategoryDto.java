package com.protean.moneymaker.rin.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BudgetCategoryDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("type")
    private String typeName;

    @JsonProperty("name")
    private String name;

    public BudgetCategoryDto() {
    }

    public BudgetCategoryDto(Long id, String typeName, String name) {
        this.id = id;
        this.typeName = typeName;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
}

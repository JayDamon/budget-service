package com.factotum.rin.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BudgetTypeDto {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("type")
    private String type;

    @JsonProperty("budgetCategories")
    private List<BudgetCategoryDto> budgetCategories = new LinkedList<>();

}
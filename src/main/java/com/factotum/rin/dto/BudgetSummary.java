package com.factotum.rin.dto;

import com.factotum.rin.enumeration.BudgetType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BudgetSummary {

    private String category;

    private Integer categoryId;

    private BudgetType budgetType;

    private BigDecimal planned;

    public BudgetSummary(String category, Integer categoryId, BudgetType budgetType, Double planned) {
        this.category = category;
        this.categoryId = categoryId;
        this.budgetType = budgetType;
        this.planned = BigDecimal.valueOf(planned);
    }
}


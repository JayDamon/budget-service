package com.factotum.budgetservice.dto;

import com.factotum.budgetservice.enumeration.BudgetType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BudgetSummary {

    private String category;

    private UUID categoryId;

    private BudgetType budgetType;

    private BigDecimal planned;
}


package com.factotum.budgetservice.dto;


import com.factotum.budgetservice.enumeration.BudgetType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class TransactionTotal {

    @JsonProperty("budgetType")
    private BudgetType budgetType;

    @JsonProperty("total")
    private BigDecimal total;

}

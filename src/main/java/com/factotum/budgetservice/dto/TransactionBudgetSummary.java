package com.factotum.budgetservice.dto;

import com.factotum.budgetservice.enumeration.BudgetType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionBudgetSummary {

    @JsonProperty("budgetType")
    private BudgetType budgetType;

    @JsonProperty("category")
    private String category;

    @JsonProperty("month")
    private Integer month;

    @JsonProperty("monthText")
    private String monthText;

    @JsonProperty("year")
    private Integer year;

    @JsonProperty("planned")
    private BigDecimal planned;

    @JsonProperty("actual")
    private BigDecimal actual;

    @JsonProperty("expected")
    private boolean expected;

    public TransactionBudgetSummary(
            BudgetType budgetType,
            String category,
            Integer month,
            Integer year,
            Double planned,
            BigDecimal actual,
            boolean expected) {

        this.budgetType = budgetType;
        this.category = category;
        this.month = month;
        this.monthText = LocalDateTime.now().withMonth(month).format(DateTimeFormatter.ofPattern("MMMM"));
        this.year = year;
        this.planned = BigDecimal.valueOf(planned);
        this.actual = actual;
        this.expected = expected;
    }

}

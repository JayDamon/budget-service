package com.factotum.rin.dto;

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

    @JsonProperty("transactionType")
    private String transactionType;

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
            String transactionType, String category, Integer month,
            Integer year, Double planned, BigDecimal actual, boolean expected) {

        this.transactionType = transactionType;
        this.category = category;
        this.month = month;
        this.monthText = LocalDateTime.now().withMonth(month).format(DateTimeFormatter.ofPattern("MMMM"));
        this.year = year;
        this.planned = BigDecimal.valueOf(planned);
        this.actual = actual;
        this.expected = expected;
    }

}

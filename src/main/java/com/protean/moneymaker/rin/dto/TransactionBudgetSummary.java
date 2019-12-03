package com.protean.moneymaker.rin.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.StringJoiner;

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

    public TransactionBudgetSummary(
            String transactionType, String category, Integer month,
            Integer year, BigDecimal planned, BigDecimal actual, boolean expected) {

        this.transactionType = transactionType;
        this.category = category;
        this.month = month;
        this.monthText = LocalDateTime.now().withMonth(month).format(DateTimeFormatter.ofPattern("MMMM"));
        this.year = year;
        this.planned = planned;
        this.actual = actual;
        this.expected = expected;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public String getMonthText() {
        return monthText;
    }

    public void setMonthText(String monthText) {
        this.monthText = monthText;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public BigDecimal getPlanned() {
        return planned;
    }

    public void setPlanned(BigDecimal planned) {
        this.planned = planned;
    }

    public BigDecimal getActual() {
        return actual;
    }

    public void setActual(BigDecimal actual) {
        this.actual = actual;
    }

    public boolean isExpected() {
        return expected;
    }

    public void setExpected(boolean expected) {
        this.expected = expected;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TransactionBudgetSummary.class.getSimpleName() + "[", "]")
                .add("transactionType='" + transactionType + "'")
                .add("category='" + category + "'")
                .add("month=" + month)
                .add("monthText='" + monthText + "'")
                .add("year=" + year)
                .add("planned=" + planned)
                .add("actual=" + actual)
                .add("expected=" + expected)
                .toString();
    }
}

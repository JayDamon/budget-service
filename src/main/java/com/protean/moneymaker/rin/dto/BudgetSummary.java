package com.protean.moneymaker.rin.dto;

import java.math.BigDecimal;
import java.util.StringJoiner;

public class BudgetSummary {

    private String category;

    private Integer categoryId;

    private String transactionType;

    private Integer transactionTypeId;

    private BigDecimal planned;

    public BudgetSummary(
            String category, Integer categoryId, String transactionType, Integer transactionTypeId, Double planned) {
        this.category = category;
        this.categoryId = categoryId;
        this.transactionType = transactionType;
        this.transactionTypeId = transactionTypeId;
        this.planned = BigDecimal.valueOf(planned);
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Integer getTransactionTypeId() {
        return transactionTypeId;
    }

    public void setTransactionTypeId(Integer transactionTypeId) {
        this.transactionTypeId = transactionTypeId;
    }

    public BigDecimal getPlanned() {
        return planned;
    }

    public void setPlanned(BigDecimal planned) {
        this.planned = planned;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", BudgetSummary.class.getSimpleName() + "[", "]")
                .add("category='" + category + "'")
                .add("planned=" + planned)
                .toString();
    }
}

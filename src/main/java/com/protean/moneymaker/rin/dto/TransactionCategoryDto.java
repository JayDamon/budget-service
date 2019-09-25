package com.protean.moneymaker.rin.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionCategoryDto {

    @JsonProperty("id")
    private Long transactionCategoryId;

    @JsonProperty("name")
    private String transactionCategoryName;

    @JsonProperty("subCategory")
    private BudgetSubCategoryDto subCategory;

    public TransactionCategoryDto() {
    }

    public TransactionCategoryDto(Long transactionCategoryId, String transactionCategoryName, BudgetSubCategoryDto subCategory) {
        this.transactionCategoryId = transactionCategoryId;
        this.transactionCategoryName = transactionCategoryName;
        this.subCategory = subCategory;
    }

    public Long getTransactionCategoryId() {
        return transactionCategoryId;
    }

    public void setTransactionCategoryId(Long transactionCategoryId) {
        this.transactionCategoryId = transactionCategoryId;
    }

    public String getTransactionCategoryName() {
        return transactionCategoryName;
    }

    public void setTransactionCategoryName(String transactionCategoryName) {
        this.transactionCategoryName = transactionCategoryName;
    }

    public BudgetSubCategoryDto getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(BudgetSubCategoryDto subCategory) {
        this.subCategory = subCategory;
    }
}

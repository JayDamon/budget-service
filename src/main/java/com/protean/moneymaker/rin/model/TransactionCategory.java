package com.protean.moneymaker.rin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

/**
 * Sub categories of transactions, tied to transaction category
 */
@Entity
@Table(name = "transaction_category")
public class TransactionCategory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_category_id")
    private Long transactionCategoryId;

    @Column(name = "category_name")
    private String transactionCategory;

    @ManyToOne
    @JoinColumn(name = "budget_sub_category_id", nullable = false)
    private BudgetSubCategory budgetSubCategory;

    public TransactionCategory() {
    }

    public TransactionCategory(String transactionCategory, BudgetSubCategory budgetSubCategory) {
        this.transactionCategory = transactionCategory;
        this.budgetSubCategory = budgetSubCategory;
    }

    public Long getTransactionCategoryId() {
        return transactionCategoryId;
    }

    public void setTransactionCategoryId(Long transactionCategoryId) {
        this.transactionCategoryId = transactionCategoryId;
    }

    public String getTransactionCategory() {
        return transactionCategory;
    }

    public void setTransactionCategory(String transactionCategory) {
        this.transactionCategory = transactionCategory;
    }

    public BudgetSubCategory getBudgetSubCategory() {
        return budgetSubCategory;
    }

    public void setBudgetSubCategory(BudgetSubCategory budgetSubCategory) {
        this.budgetSubCategory = budgetSubCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionCategory that = (TransactionCategory) o;
        return Objects.equals(transactionCategoryId, that.transactionCategoryId) &&
                Objects.equals(transactionCategory, that.transactionCategory) &&
                Objects.equals(budgetSubCategory, that.budgetSubCategory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionCategoryId, transactionCategory, budgetSubCategory.getBudgetCategoryId());
    }

    @Override
    public String toString() {
        return "TransactionCategory{" +
                "transactionCategoryId=" + transactionCategoryId +
                ", transactionCategory='" + transactionCategory + '\'' +
                ", budgetSubCategoryid=" + budgetSubCategory.getBudgetCategoryId() +
                '}';
    }
}

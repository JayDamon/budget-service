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
    private Long subCategoryId;

    @Column(name = "category_name")
    private String transactionCategory;

    @ManyToOne
    @JoinColumn(name = "budget_category_id", nullable = false)
    private BudgetCategory budgetCategory;

    public TransactionCategory() {
    }

    public TransactionCategory(String transactionCategory, BudgetCategory budgetCategory) {
        this.transactionCategory = transactionCategory;
        this.budgetCategory = budgetCategory;
    }

    public Long getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(Long subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public String getTransactionCategory() {
        return transactionCategory;
    }

    public void setTransactionCategory(String transactionCategory) {
        this.transactionCategory = transactionCategory;
    }

    public BudgetCategory getBudgetCategory() {
        return budgetCategory;
    }

    public void setBudgetCategory(BudgetCategory budgetCategory) {
        this.budgetCategory = budgetCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionCategory that = (TransactionCategory) o;
        return Objects.equals(subCategoryId, that.subCategoryId) &&
                Objects.equals(transactionCategory, that.transactionCategory) &&
                Objects.equals(budgetCategory, that.budgetCategory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subCategoryId, transactionCategory, budgetCategory);
    }

    @Override
    public String toString() {
        return "TransactionCategory{" +
                "subCategoryId=" + subCategoryId +
                ", transactionCategory='" + transactionCategory + '\'' +
                ", budgetCategory=" + budgetCategory +
                '}';
    }
}

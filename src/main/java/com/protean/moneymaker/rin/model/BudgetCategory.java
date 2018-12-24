package com.protean.moneymaker.rin.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * i.e. Primary transaction category
 */
@Entity
@Table(name = "budget_category")
public class BudgetCategory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "budget_category_id")
    private Long budgetCategoryId;

    @Column(name = "category_name")
    private String category;

    @OneToOne
    @JoinColumn(name = "transaction_type_id")
    private TransactionType transactionType;

    @OneToMany(mappedBy = "budgetCategory", cascade = CascadeType.ALL)
    private Set<TransactionCategory> transactionCategories;

    public BudgetCategory() {
    }

    public BudgetCategory(String category, TransactionType transactionType, Set<TransactionCategory> transactionCategories) {
        this.category = category;
        this.transactionType = transactionType;
        this.transactionCategories = transactionCategories;
    }

    public Long getBudgetCategoryId() {
        return budgetCategoryId;
    }

    public void setBudgetCategoryId(Long budgetCategoryId) {
        this.budgetCategoryId = budgetCategoryId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Set<TransactionCategory> getTransactionCategories() {
        return transactionCategories;
    }

    public void setTransactionCategories(Set<TransactionCategory> transactionCategories) {
        this.transactionCategories = transactionCategories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BudgetCategory that = (BudgetCategory) o;
        return Objects.equals(budgetCategoryId, that.budgetCategoryId) &&
                Objects.equals(category, that.category) &&
                Objects.equals(transactionType, that.transactionType) &&
                Objects.equals(transactionCategories, that.transactionCategories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(budgetCategoryId, category, transactionType, transactionCategories);
    }

    @Override
    public String toString() {
        return "BudgetCategory{" +
                "budgetCategoryId=" + budgetCategoryId +
                ", category='" + category + '\'' +
                ", transactionType=" + transactionType +
                ", transactionCategories=" + transactionCategories +
                '}';
    }
}

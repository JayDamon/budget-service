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
    private Long id;

    @Column(name = "category_name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "budget_sub_category_id", nullable = false)
    private BudgetSubCategory budgetSubCategory;

    public TransactionCategory() {
    }

    public TransactionCategory(String name, BudgetSubCategory budgetSubCategory) {
        this.name = name;
        this.budgetSubCategory = budgetSubCategory;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(budgetSubCategory, that.budgetSubCategory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, budgetSubCategory.getId());
    }

    @Override
    public String toString() {
        return "TransactionCategory{" +
                "transactionCategoryId=" + id +
                ", transactionCategory='" + name + '\'' +
                ", budgetSubCategoryid=" + budgetSubCategory.getId() +
                '}';
    }
}

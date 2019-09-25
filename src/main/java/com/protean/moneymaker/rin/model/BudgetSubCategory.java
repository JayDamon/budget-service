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
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * i.e. Primary transaction category // TODO fix this comment
 */
@Entity
@Table(name = "budget_sub_category")
public class BudgetSubCategory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "budget_sub_category_id")
    private Long id;

    @Column(name = "sub_category_name")
    private String name;

    @OneToOne
    @JoinColumn(name = "transaction_type_id")
    private TransactionType transactionType;

    @OneToMany(mappedBy = "budgetSubCategory", cascade = CascadeType.ALL)
    private Set<TransactionCategory> transactionCategories = new HashSet<>();

    public BudgetSubCategory() {
    }

    public BudgetSubCategory(
            String name, TransactionType transactionType,
            Set<TransactionCategory> transactionCategories) {

        this.name = name;
        this.transactionType = transactionType;
        this.transactionCategories = transactionCategories;
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
        BudgetSubCategory that = (BudgetSubCategory) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(transactionType, that.transactionType) &&
                Objects.equals(transactionCategories, that.transactionCategories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, transactionType, transactionCategories);
    }

    @Override
    public String toString() {
        return "BudgetSubCategory{" +
                "budgetCategoryId=" + id +
                ", category='" + name + '\'' +
                ", transactionType=" + transactionType +
                ", transactionCategories=" + transactionCategories +
                '}';
    }
}

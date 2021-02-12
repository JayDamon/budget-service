package com.protean.moneymaker.rin.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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

    @OneToMany(mappedBy = "budgetSubCategory", cascade = CascadeType.ALL)
    private Set<TransactionCategory> transactionCategories = new HashSet<>();

    public BudgetSubCategory() {
    }

    public BudgetSubCategory(
            String name, TransactionType transactionType,
            Set<TransactionCategory> transactionCategories) {

        this.name = name;
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

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return transactionCategories != null ? transactionCategories.equals(that.transactionCategories) : that.transactionCategories == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BudgetSubCategory{" +
                "budgetCategoryId=" + id +
                ", category='" + name + '\'' +
                ", transactionCategories=" + transactionCategories +
                '}';
    }
}

package com.protean.moneymaker.rin.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "budget_category")
public class BudgetCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "budget_category_id")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "budget_category_type_id")
    private BudgetCategoryType type;

    @OneToOne
    @JoinColumn(name = "budget_category_name_id")
    private BudgetCategoryName name;

    @OneToMany(mappedBy = "budgetCategory", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private Set<BudgetItem> budgetItems = new LinkedHashSet<>();

    // TODO I removed BudgetSubCategory, but it had a direct link to TransactionCategory, so i may want that back if it is possible to link them up
    public BudgetCategory() {
    }

    public BudgetCategory(BudgetCategoryType type, BudgetCategoryName name) {
        this.type = type;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BudgetCategoryType getType() {
        return type;
    }

    public void setType(BudgetCategoryType type) {
        this.type = type;
    }

    public BudgetCategoryName getName() {
        return name;
    }

    public void setName(BudgetCategoryName name) {
        this.name = name;
    }

    public Set<BudgetItem> getBudgetItems() {
        return budgetItems;
    }

    public void setBudgetItems(Set<BudgetItem> budgetItems) {
        this.budgetItems = budgetItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BudgetCategory that = (BudgetCategory) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(type, that.type) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, name);
    }

    @Override
    public String toString() {
        return "BudgetCategory{" +
                "budgetCategoryId=" + id +
                ", budgetCategoryType=" + type +
                ", budgetCategoryName=" + name +
                '}';
    }
}

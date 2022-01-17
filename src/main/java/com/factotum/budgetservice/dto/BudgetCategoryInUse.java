package com.factotum.budgetservice.dto;

import java.util.StringJoiner;

public class BudgetCategoryInUse {

    private String budgetCategoryName;

    private String budgetCategoryType;

    public BudgetCategoryInUse(String budgetCategoryName, String budgetCategoryType) {
        this.budgetCategoryName = budgetCategoryName;
        this.budgetCategoryType = budgetCategoryType;
    }

    public String getBudgetCategoryName() {
        return budgetCategoryName;
    }

    public void setBudgetCategoryName(String budgetCategoryName) {
        this.budgetCategoryName = budgetCategoryName;
    }

    public String getBudgetCategoryType() {
        return budgetCategoryType;
    }

    public void setBudgetCategoryType(String budgetCategoryType) {
        this.budgetCategoryType = budgetCategoryType;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", BudgetCategoryInUse.class.getSimpleName() + "[", "]")
                .add("budgetCategoryName='" + budgetCategoryName + "'")
                .add("budgetCategoryType='" + budgetCategoryType + "'")
                .toString();
    }

}

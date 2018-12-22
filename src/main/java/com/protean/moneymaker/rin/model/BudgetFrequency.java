package com.protean.moneymaker.rin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Join table for how often the budget is applied
 */
@Entity
@Table(name = "budget_frequency")
public class BudgetFrequency implements Serializable {

    @Id
    @Column(name = "budget_id")
    private Long budgetId;

    @OneToOne
    @JoinColumn(name = "frequency_type_id")
    private FrequencyType frequencyType;

    @Column(name = "frequency")
    private Integer frequency;

}

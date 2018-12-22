package com.protean.moneymaker.rin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Join table for recurring transaction frequency
 */
@Entity
@Table(name = "recurring_transaction_frequency")
public class RecurringTransactionFrequency implements Serializable {

    @Id
    @Column(name = "recurring_transaction_id")
    private Integer recurringTransactionId;

    @OneToOne
    @JoinColumn(name = "frequency_type_id")
    private FrequencyType frequencyType;

    @Column(name = "frequency")
    private Integer frequency;

}

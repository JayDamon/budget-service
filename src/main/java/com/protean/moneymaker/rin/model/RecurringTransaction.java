package com.protean.moneymaker.rin.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Recurring transactions that are applied on a defined time period that are not bills
 * These may be better defined as allocations
 */
@Entity
@Table(name = "recurring_transaction")
public class RecurringTransaction extends UserAuditable implements Serializable {

    @Id
    @Column(name = "recurring_transaction_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    // TODO do i need this?
//    @OneToOne
//    @JoinColumn(name = "account_id")
//    private Account account;

    // TODO do i need this?
//    @OneToOne
//    @JoinColumn(name = "budget_id")
//    private Budget budget;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "frequency_id")
    private FrequencyType frequencyType;

    @OneToOne
    @JoinColumn(name = "occurrence_id")
    private Occurrence occurrence;

    @OneToOne
    @JoinColumn(name = "transaction_type_id")
    private TransactionType transactionType;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "amount")
    private BigDecimal amount;

}

package com.protean.moneymaker.rin.model;

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

/**
 * Bank account
 */
@Entity
@Table(name = "account")
public class Account extends UserAuditable implements Serializable {

    @Id
    @Column(name = "account_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "account_type_id", nullable = false)
    private AccountType account;

    @Column(name = "starting_balance", nullable = false)
    private BigDecimal startingBalance;

    @Column(name = "current_balance")
    private BigDecimal currentBalance;

    @OneToOne
    @JoinColumn(name = "account_classification_id")
    private AccountClassification accountClassification;

    @Column(name = "is_primary_account")
    private Boolean isPrimaryAccount;

    @Column(name = "is_in_cash_flow")
    private Boolean isInCashFlow;

}

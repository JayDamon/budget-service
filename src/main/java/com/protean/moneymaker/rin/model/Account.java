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
import java.util.Objects;

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

    @Column(name = "name")
    private String name;

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

    public Account() {
    }

    public Account(
            String name, AccountType account, BigDecimal startingBalance,
            BigDecimal currentBalance, AccountClassification accountClassification,
            Boolean isPrimaryAccount, Boolean isInCashFlow) {

        this.name = name;
        this.account = account;
        this.startingBalance = startingBalance;
        this.currentBalance = currentBalance;
        this.accountClassification = accountClassification;
        this.isPrimaryAccount = isPrimaryAccount;
        this.isInCashFlow = isInCashFlow;
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

    public AccountType getAccount() {
        return account;
    }

    public void setAccount(AccountType account) {
        this.account = account;
    }

    public BigDecimal getStartingBalance() {
        return startingBalance;
    }

    public void setStartingBalance(BigDecimal startingBalance) {
        this.startingBalance = startingBalance;
    }

    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(BigDecimal currentBalance) {
        this.currentBalance = currentBalance;
    }

    public AccountClassification getAccountClassification() {
        return accountClassification;
    }

    public void setAccountClassification(AccountClassification accountClassification) {
        this.accountClassification = accountClassification;
    }

    public Boolean getPrimaryAccount() {
        return isPrimaryAccount;
    }

    public void setPrimaryAccount(Boolean primaryAccount) {
        isPrimaryAccount = primaryAccount;
    }

    public Boolean getInCashFlow() {
        return isInCashFlow;
    }

    public void setInCashFlow(Boolean inCashFlow) {
        isInCashFlow = inCashFlow;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account1 = (Account) o;
        return Objects.equals(id, account1.id) &&
                Objects.equals(name, account1.name) &&
                Objects.equals(account, account1.account) &&
                Objects.equals(startingBalance, account1.startingBalance) &&
                Objects.equals(currentBalance, account1.currentBalance) &&
                Objects.equals(accountClassification, account1.accountClassification) &&
                Objects.equals(isPrimaryAccount, account1.isPrimaryAccount) &&
                Objects.equals(isInCashFlow, account1.isInCashFlow);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, account, startingBalance, currentBalance, accountClassification, isPrimaryAccount, isInCashFlow);
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + id +
                ", name='" + name + '\'' +
                ", account=" + account +
                ", startingBalance=" + startingBalance +
                ", currentBalance=" + currentBalance +
                ", accountClassification=" + accountClassification +
                ", isPrimaryAccount=" + isPrimaryAccount +
                ", isInCashFlow=" + isInCashFlow +
                '}';
    }
}

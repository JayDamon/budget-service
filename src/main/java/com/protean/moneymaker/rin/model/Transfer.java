package com.protean.moneymaker.rin.model;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

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

// TODO going to need to delete transfers if associated accounts and transactions are deleted, perhaps by prompt?
/**
 * Transfer from one user account to another
 */
@Entity
@Table(name = "transfer")
public class Transfer extends UserAuditable implements Serializable {

    @Id
    @Column(name = "transfer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "transfer_date")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime date;

    @OneToOne
    @JoinColumn(name = "from_account_id")
    private Account fromAccount;

    @OneToOne
    @JoinColumn(name = "to_account_id")
    private Account toAccount;

    @Column(name = "amount")
    private BigDecimal amount;

    @OneToOne
    @JoinColumn(name = "from_transaction_id")
    private Transaction fromTransaction;

    @OneToOne
    @JoinColumn(name = "to_transaction_id")
    private Transaction toTransaction;

    public Transfer() {
    }

    public Transfer(DateTime date, Account fromAccount, Account toAccount, BigDecimal amount, Transaction fromTransaction, Transaction toTransaction) {
        this.date = date;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
        this.fromTransaction = fromTransaction;
        this.toTransaction = toTransaction;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }

    public Account getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(Account fromAccount) {
        this.fromAccount = fromAccount;
    }

    public Account getToAccount() {
        return toAccount;
    }

    public void setToAccount(Account toAccount) {
        this.toAccount = toAccount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Transaction getFromTransaction() {
        return fromTransaction;
    }

    public void setFromTransaction(Transaction fromTransaction) {
        this.fromTransaction = fromTransaction;
    }

    public Transaction getToTransaction() {
        return toTransaction;
    }

    public void setToTransaction(Transaction toTransaction) {
        this.toTransaction = toTransaction;
    }

    @Override
    public String toString() {
        return "Transfer{" +
                "transferId=" + id +
                ", date=" + date +
                ", fromAccount=" + fromAccount +
                ", toAccount=" + toAccount +
                ", amount=" + amount +
                ", fromTransaction=" + fromTransaction +
                ", toTransaction=" + toTransaction +
                '}';
    }
}

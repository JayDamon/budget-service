package com.protean.moneymaker.rin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

/**
 * Income, expense // TODO this should be an enum
 */
@Entity
@Table(name = "transaction_type")
public class TransactionType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_type_id")
    private Integer transactionTypeId;

    @Column(name = "transaction_type")
    private String name;

    public TransactionType() {
    }

    public TransactionType(String name) {
        this.name = name;
    }

    public Integer getTransactionTypeId() {
        return transactionTypeId;
    }

    public void setTransactionTypeId(Integer transactionTypeId) {
        this.transactionTypeId = transactionTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionType that = (TransactionType) o;
        return Objects.equals(transactionTypeId, that.transactionTypeId) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionTypeId, name);
    }

    @Override
    public String toString() {
        return "TransactionType{" +
                "transactionTypeId=" + transactionTypeId +
                ", name='" + name + '\'' +
                '}';
    }
}

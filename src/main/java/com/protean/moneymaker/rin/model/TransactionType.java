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
    private Integer id;

    @Column(name = "transaction_type")
    private String transactionTypeName;

    public TransactionType() {
    }

    public TransactionType(String transactionTypeName) {
        this.transactionTypeName = transactionTypeName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTransactionTypeName() {
        return transactionTypeName;
    }

    public void setTransactionTypeName(String name) {
        this.transactionTypeName = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionType that = (TransactionType) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(transactionTypeName, that.transactionTypeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, transactionTypeName);
    }

    @Override
    public String toString() {
        return "TransactionType{" +
                "transactionTypeId=" + id +
                ", name='" + transactionTypeName + '\'' +
                '}';
    }
}

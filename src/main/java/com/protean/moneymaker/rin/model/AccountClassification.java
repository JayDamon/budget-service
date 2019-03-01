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
 * i.e. asset, liability, capital // TODO this may need to change, this may not be what was originally intended
 */
@Entity
@Table(name = "account_classification")
public class AccountClassification implements Serializable {

    @Id
    @Column(name = "account_classification_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer accountClassificationId;

    @Column(name = "classification")
    private String classification;

    public AccountClassification() {
    }

    public AccountClassification(String classification) {
        this.classification = classification;
    }

    public Integer getAccountClassificationId() {
        return accountClassificationId;
    }

    public void setAccountClassificationId(Integer accountClassificationId) {
        this.accountClassificationId = accountClassificationId;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountClassification that = (AccountClassification) o;
        return Objects.equals(accountClassificationId, that.accountClassificationId) &&
                Objects.equals(classification, that.classification);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountClassificationId, classification);
    }

    @Override
    public String toString() {
        return "AccountClassification{" +
                "accountClassificationId=" + accountClassificationId +
                ", classification='" + classification + '\'' +
                '}';
    }
}

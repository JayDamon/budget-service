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
 * i.e. checking, savings, money market
 */
@Entity
@Table(name = "account_type")
public class AccountType implements Serializable {

    @Id
    @Column(name = "account_type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer accountTypeid;

    @Column(name = "full_account_type")
    private String fullType;

    @Column(name = "short_account_type")
    private String shortType;

    public AccountType() {
    }

    public AccountType(String fullType, String shortType) {
        this.fullType = fullType;
        this.shortType = shortType;
    }

    public Integer getAccountTypeid() {
        return accountTypeid;
    }

    public void setAccountTypeid(Integer accountTypeid) {
        this.accountTypeid = accountTypeid;
    }

    public String getFullType() {
        return fullType;
    }

    public void setFullType(String fullType) {
        this.fullType = fullType;
    }

    public String getShortType() {
        return shortType;
    }

    public void setShortType(String shortType) {
        this.shortType = shortType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountType that = (AccountType) o;
        return Objects.equals(accountTypeid, that.accountTypeid) &&
                Objects.equals(fullType, that.fullType) &&
                Objects.equals(shortType, that.shortType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountTypeid, fullType, shortType);
    }

    @Override
    public String toString() {
        return "AccountType{" +
                "accountTypeid=" + accountTypeid +
                ", fullType='" + fullType + '\'' +
                ", shortType='" + shortType + '\'' +
                '}';
    }
}

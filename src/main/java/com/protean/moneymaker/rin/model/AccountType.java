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
    private Integer id;

    @Column(name = "full_account_type")
    private String fullName;

    @Column(name = "short_account_type")
    private String shortName;

    public AccountType() {
    }

    public AccountType(String fullName, String shortName) {
        this.fullName = fullName;
        this.shortName = shortName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullType) {
        this.fullName = fullType;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortType) {
        this.shortName = shortType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountType that = (AccountType) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(fullName, that.fullName) &&
                Objects.equals(shortName, that.shortName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, shortName);
    }

    @Override
    public String toString() {
        return "AccountType{" +
                "accountTypeid=" + id +
                ", fullType='" + fullName + '\'' +
                ", shortType='" + shortName + '\'' +
                '}';
    }
}

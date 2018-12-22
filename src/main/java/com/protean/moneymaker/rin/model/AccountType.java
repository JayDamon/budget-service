package com.protean.moneymaker.rin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * i.e. checking, savins, money market
 */
@Entity
@Table(name = "account_type")
public class AccountType implements Serializable {

    @Id
    @Column(name = "account_type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "full_account_type")
    private String fullType;

    @Column(name = "short_account_type")
    private String shortType;

}

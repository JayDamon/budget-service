package com.protean.moneymaker.rin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * i.e. asset, liability, capital // TODO this may need to change, this may not be what was originally intended
 */
@Entity
@Table(name = "account_classification")
public class AccountClassification implements Serializable {

    @Id
    @Column(name = "account_classification_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "classification")
    private String classification;

}

package com.protean.moneymaker.rin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

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
    private String type;

}

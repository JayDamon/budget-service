package com.protean.moneymaker.rin.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Embeddable
public class TransactionCategoryId implements Serializable {

    @Column(name = "transaction_category_id")
    private Long transactionCategoryId;

    @OneToOne
    @JoinColumn(name = "transaction_type_id")
    private TransactionType transactionType;

}

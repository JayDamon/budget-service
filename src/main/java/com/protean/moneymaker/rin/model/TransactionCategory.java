package com.protean.moneymaker.rin.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * i.e. Primary transaction category // TODO should this have a one to many with sub category?
 */
@Entity
@Table(name = "transaction_category")
public class TransactionCategory implements Serializable {

    @EmbeddedId
    private TransactionCategoryId id = new TransactionCategoryId();

    @Column(name = "category")
    private String category;

}

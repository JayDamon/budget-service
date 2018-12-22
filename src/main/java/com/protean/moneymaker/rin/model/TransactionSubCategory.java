package com.protean.moneymaker.rin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Sub categories of transactions, tied to transaction category
 */
@Entity
@Table(name = "transaction_sub_category")
public class TransactionSubCategory implements Serializable {

    @Id
    @Column(name = "transaction_sub_category_id")
    private Long subCategoryId;

    @Column(name = "transaction_sub_category")
    private String subCategory;

}

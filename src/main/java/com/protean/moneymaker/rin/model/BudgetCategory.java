package com.protean.moneymaker.rin.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;

/**
 * i.e. Primary transaction category // TODO should this have a one to many with sub category?
 */
@Entity
@Table(name = "transaction_category")
public class TransactionCategory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_category_id")
    private Long transactionCategoryId;

    @Column(name = "category")
    private String category;

    @OneToOne
    @JoinColumn(name = "transaction_type_id")
    private TransactionType transactionType;

    @OneToMany(mappedBy = "transactionCategory", cascade = CascadeType.ALL)
    private Set<TransactionSubCategory> subCategories;

}

package com.protean.moneymaker.rin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

// TODO going to need to delete transfers if associated accounts and transactions are deleted, perhaps by prompt?
/**
 * Transfer from one user account to another
 */
@Entity
@Table(name = "transfer")
public class Transfer extends UserAuditable implements Serializable {

    @Id
    @Column(name = "transfer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "transfer_date")
    private Date date;

    @OneToOne
    @JoinColumn(name = "from_account_id")
    private Account fromAccount;

    @OneToOne
    @JoinColumn(name = "to_account_id")
    private Account toAccount;

    @Column(name = "amount")
    private BigDecimal amount;

    @OneToOne
    @JoinColumn(name = "from_transaction_id")
    private Transaction fromTransaction;

    @OneToOne
    @JoinColumn(name = "to_transaction_id")
    private Transaction toTransaction;

}

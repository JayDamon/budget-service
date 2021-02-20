package com.factotum.rin.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

// TODO going to need to delete transfers if associated accounts and transactions are deleted, perhaps by prompt?

/**
 * Transfer from one user account to another
 */
// TODO: Maybe move to oaka?
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transfer")
public class Transfer implements Serializable {

    @Id
    @Column(name = "transfer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "transfer_date")
    private ZonedDateTime date;

    @Column(name = "from_account_id")
    private Long fromAccount;

    @Column(name = "to_account_id")
    private Long toAccount;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "from_transaction_id")
    private Long fromTransaction;

    @Column(name = "to_transaction_id")
    private Long toTransaction;

}

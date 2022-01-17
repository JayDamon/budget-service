package com.factotum.budgetservice.model;

import com.factotum.budgetservice.component.TenantEntityListener;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

/**
 * User defined budgets
 */
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "budget")
@EntityListeners(TenantEntityListener.class)
public class Budget implements TenantEntity, Serializable {

    @Id
    @Column(name = "budget_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "budget_category_id")
    private BudgetCategory budgetCategory;

    @Column(name = "budget_item_name")
    private String name;

    @Column(name = "start_date")
    private ZonedDateTime startDate;

    @Column(name = "end_date")
    private ZonedDateTime endDate;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "frequency_type_id")
    private FrequencyType frequencyType;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "in_use")
    private Boolean inUse;

    @Column(name = "transaction_type_id")
    private Integer transactionTypeId;

    @Column(name = "tenant_id")
    private String tenantId;

}

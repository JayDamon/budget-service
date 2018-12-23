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

/**
 * user defi;ned goals
 */
@Entity
@Table(name = "goal")
public class Goal extends UserAuditable implements Serializable {

    @Id
    @Column(name = "goal_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "priority")
    private Integer priority;  // TODO should it be unique?

    @OneToOne
    @JoinColumn(name = "goal_type_id")
    private GoalType goalType;

    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "amount")
    private BigDecimal amount;

}

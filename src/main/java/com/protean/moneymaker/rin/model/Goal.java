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
import java.util.Objects;

/**
 * user defi;ned goals
 */
@Entity
@Table(name = "goal")
public class Goal extends UserAuditable implements Serializable {

    @Id
    @Column(name = "goal_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long goalId;

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

    public Goal() {
    }

    public Goal(String name, Integer priority, GoalType goalType, Account account, Date startDate, Date endDate, BigDecimal amount) {
        this.name = name;
        this.priority = priority;
        this.goalType = goalType;
        this.account = account;
        this.startDate = startDate;
        this.endDate = endDate;
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Goal goal = (Goal) o;
        return Objects.equals(goalId, goal.goalId) &&
                Objects.equals(name, goal.name) &&
                Objects.equals(priority, goal.priority) &&
                Objects.equals(goalType, goal.goalType) &&
                Objects.equals(account, goal.account) &&
                Objects.equals(startDate, goal.startDate) &&
                Objects.equals(endDate, goal.endDate) &&
                Objects.equals(amount, goal.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(goalId, name, priority, goalType, account, startDate, endDate, amount);
    }

    @Override
    public String toString() {
        return "Goal{" +
                "goalId=" + goalId +
                ", name='" + name + '\'' +
                ", priority=" + priority +
                ", goalType=" + goalType +
                ", account=" + account +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", amount=" + amount +
                '}';
    }
}

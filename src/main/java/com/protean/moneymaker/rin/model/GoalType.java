package com.protean.moneymaker.rin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

/**
 * save, pay // TODO should be an enum
 */
@Entity
@Table(name = "goal_type")
public class GoalType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "goal_type_id")
    private Integer goalTypeId;

    @Column(name = "goal_type")
    private String type;

    public GoalType() {
    }

    public GoalType(String type) {
        this.type = type;
    }

    public Integer getGoalTypeId() {
        return goalTypeId;
    }

    public void setGoalTypeId(Integer goalTypeId) {
        this.goalTypeId = goalTypeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GoalType goalType = (GoalType) o;
        return Objects.equals(goalTypeId, goalType.goalTypeId) &&
                Objects.equals(type, goalType.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(goalTypeId, type);
    }

    @Override
    public String toString() {
        return "GoalType{" +
                "goalTypeId=" + goalTypeId +
                ", type='" + type + '\'' +
                '}';
    }
}

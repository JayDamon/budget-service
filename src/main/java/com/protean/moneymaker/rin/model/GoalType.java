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
    private Integer id;

    @Column(name = "goal_type")
    private String type;

    public GoalType() {
    }

    public GoalType(String type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        return Objects.equals(id, goalType.id) &&
                Objects.equals(type, goalType.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type);
    }

    @Override
    public String toString() {
        return "GoalType{" +
                "goalTypeId=" + id +
                ", type='" + type + '\'' +
                '}';
    }
}

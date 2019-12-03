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
 * Weekly, Monthly, Annual, Bi-Weekly, Hourly
 */
@Entity
@Table(name = "frequency_type")
public class FrequencyType implements Serializable {

    @Id
    @Column(name = "frequency_type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "frequency_type")
    private String name;

    // Factor used to multiply amount by to normalize to a monthly value
    @Column(name = "month_factor")
    private Double monthFactor;

    public FrequencyType() {
    }

    public FrequencyType(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getMonthFactor() {
        return monthFactor;
    }

    public void setMonthFactor(Double monthFactor) {
        this.monthFactor = monthFactor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FrequencyType that = (FrequencyType) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "FrequencyType{" +
                "frequencyTypeId=" + id +
                ", frequency='" + name + '\'' +
                '}';
    }
}

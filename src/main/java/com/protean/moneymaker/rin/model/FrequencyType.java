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
 * Week, month, year // TODO should probably be an enum
 */
@Entity
@Table(name = "frequency_type")
public class FrequencyType implements Serializable {

    @Id
    @Column(name = "frequency_type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer frequencyTypeId;

    @Column(name = "frequency_type")
    private String frequency;

    public FrequencyType() {
    }

    public FrequencyType(String frequency) {
        this.frequency = frequency;
    }

    public Integer getFrequencyTypeId() {
        return frequencyTypeId;
    }

    public void setFrequencyTypeId(Integer frequencyTypeId) {
        this.frequencyTypeId = frequencyTypeId;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FrequencyType that = (FrequencyType) o;
        return Objects.equals(frequencyTypeId, that.frequencyTypeId) &&
                Objects.equals(frequency, that.frequency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frequencyTypeId, frequency);
    }

    @Override
    public String toString() {
        return "FrequencyType{" +
                "frequencyTypeId=" + frequencyTypeId +
                ", frequency='" + frequency + '\'' +
                '}';
    }
}

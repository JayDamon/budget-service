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
 * When in the period is the item applied. i.e. Specific Date, First of Month, Last of Month
 */
@Entity
@Table(name = "occurrence")
public class Occurrence implements Serializable {

    @Id
    @Column(name = "occurrence_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "occurrence")
    private String occurrence;

    public Occurrence() {
    }

    public Occurrence(String occurrence) {
        this.occurrence = occurrence;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOccurrence() {
        return occurrence;
    }

    public void setOccurrence(String occurrence) {
        this.occurrence = occurrence;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Occurrence that = (Occurrence) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(occurrence, that.occurrence);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, occurrence);
    }

    @Override
    public String toString() {
        return "Occurrence{" +
                "id=" + id +
                ", occurrence='" + occurrence + '\'' +
                '}';
    }
}

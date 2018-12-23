package com.protean.moneymaker.rin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Week, month, year // TODO should probably be an enum
 */
@Entity
@Table(name = "frequency_type")
public class FrequencyType implements Serializable {

    @Id
    @Column(name = "frequency_type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "frequency_type")
    private String frequency;

}

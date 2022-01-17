package com.factotum.budgetservice.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Weekly, Monthly, Annual, Bi-Weekly, Hourly
 */
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "frequency_type")
public class FrequencyType implements Serializable {

    @Id
    @Column(name = "frequency_type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "frequency_type")
    private String frequencyTypeName;

    // Factor used to multiply amount by to normalize to a monthly value
    @Column(name = "month_factor")
    private Double monthFactor;

}

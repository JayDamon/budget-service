package com.factotum.budgetservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * Weekly, Monthly, Annual, Bi-Weekly, Hourly
 */
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@Builder
@AllArgsConstructor
@Entity
@Table(name = "frequency_type")
public class FrequencyType implements Serializable {

    @Id
    @Column(name = "frequency_type_id", columnDefinition = "uuid DEFAULT uuid_generate_v4()")
    private UUID id;

    @Column(name = "frequency_type")
    private String frequencyTypeName;

    // Factor used to multiply amount by to normalize to a monthly value
    @Column(name = "month_factor")
    private BigDecimal monthFactor;

}

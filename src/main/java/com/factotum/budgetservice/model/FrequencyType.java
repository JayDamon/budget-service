package com.factotum.budgetservice.model;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
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
    @Type(type = "pg-uuid")
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(name = "frequency_type")
    private String frequencyTypeName;

    // Factor used to multiply amount by to normalize to a monthly value
    @Column(name = "month_factor")
    private BigDecimal monthFactor;

}

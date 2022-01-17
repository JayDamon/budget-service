package com.factotum.budgetservice.model;

import lombok.*;

import javax.persistence.*;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "budget_category_type")
public class BudgetCategoryType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "budget_category_type_id")
    private Integer id;

    @Column(name = "budget_category_type")
    private String name;

    @Column(name = "display_order")
    private Integer order;

}

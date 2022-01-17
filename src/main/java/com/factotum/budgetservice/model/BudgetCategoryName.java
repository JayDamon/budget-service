package com.factotum.budgetservice.model;

import lombok.*;

import javax.persistence.*;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "budget_category_name")
public class BudgetCategoryName {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "budget_category_name_id")
    private Integer id;

    @Column(name = "category_name")
    private String name;

    @Column(name = "display_order")
    private Integer order;

}

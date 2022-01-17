package com.factotum.budgetservice.model;

import com.factotum.budgetservice.enumeration.BudgetType;
import lombok.*;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "budget_category")
public class BudgetCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "budget_category_id")
    private Integer id;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "budget_type")
    private BudgetType budgetType;

    @EqualsAndHashCode.Exclude
    @OneToOne
    @JoinColumn(name = "budget_category_type_id")
    private BudgetCategoryType type;

    @EqualsAndHashCode.Exclude
    @OneToOne
    @JoinColumn(name = "budget_category_name_id")
    private BudgetCategoryName name;

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "budgetCategory", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private Set<BudgetItem> budgetItems = new LinkedHashSet<>();

}

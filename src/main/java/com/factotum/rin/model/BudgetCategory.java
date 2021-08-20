package com.factotum.rin.model;

import com.factotum.rin.enumeration.BudgetType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
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

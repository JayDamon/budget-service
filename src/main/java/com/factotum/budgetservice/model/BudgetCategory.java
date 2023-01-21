package com.factotum.budgetservice.model;

import com.factotum.budgetservice.enumeration.BudgetType;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
@Builder
@AllArgsConstructor
@Entity
@Table(name = "budget_category")
public class BudgetCategory {

    @Id
    @Column(name = "budget_category_id", columnDefinition = "uuid DEFAULT uuid_generate_v4()")
    @Type(type = "pg-uuid")
    @GeneratedValue(generator = "UUID")
    private UUID id;

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
    @ToString.Exclude
    private Set<BudgetItem> budgetItems = new LinkedHashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        BudgetCategory that = (BudgetCategory) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

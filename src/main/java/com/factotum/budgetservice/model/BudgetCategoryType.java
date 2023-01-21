package com.factotum.budgetservice.model;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
@Builder
@AllArgsConstructor
@Entity
@Table(name = "budget_category_type")
public class BudgetCategoryType {

    @Id
    @Column(name = "budget_category_type_id", columnDefinition = "uuid DEFAULT uuid_generate_v4()")
    @Type(type = "pg-uuid")
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(name = "budget_category_type")
    private String name;

    @Column(name = "display_order")
    private Integer order;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        BudgetCategoryType that = (BudgetCategoryType) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

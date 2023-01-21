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
@Table(name = "budget_category_name")
public class BudgetCategoryName {

    @Id
    @Column(name = "budget_category_name_id", columnDefinition = "uuid DEFAULT uuid_generate_v4()")
    @Type(type = "pg-uuid")
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(name = "category_name")
    private String name;

    @Column(name = "display_order")
    private Integer order;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        BudgetCategoryName that = (BudgetCategoryName) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

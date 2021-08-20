package com.factotum.rin.repository;

import com.factotum.rin.model.BudgetCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

@Repository
public interface BudgetCategoryRepository extends JpaRepository<BudgetCategory, Integer> {

    @Query("SELECT bc FROM BudgetCategory bc " +
            "INNER JOIN bc.type t " +
            "INNER JOIN bc.name n " +
            "ORDER BY t.order, n.order")
    List<BudgetCategory> findAllByOrderByTypeOrderAndNameOrder();

}

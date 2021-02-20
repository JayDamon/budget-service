package com.factotum.rin.repository;

import com.factotum.rin.dto.BudgetCategoryInUse;
import com.factotum.rin.dto.BudgetSummary;
import com.factotum.rin.model.Budget;
import com.factotum.rin.model.BudgetCategoryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {

    Set<Budget> findBudgetsByInUseTrue();

    Set<Budget> findBudgetsByInUseFalse();

    @Query(value = "SELECT DISTINCT new com.factotum.rin.dto.BudgetCategoryInUse(" +
            "n.name, t.name) FROM Budget b " +
            "INNER JOIN b.budgetCategory bc " +
            "INNER JOIN bc.type t " +
            "INNER JOIN bc.name n ")
    Set<BudgetCategoryInUse> getAllBudgetCategoryInUse();

    @Query(value = "SELECT DISTINCT t FROM Budget b " +
            "INNER JOIN b.budgetCategory bc " +
            "INNER JOIN bc.type t")
    Set<BudgetCategoryType> getAllBudgetCategoryTypesInUse();

    @Query(value = "SELECT " +
            "new com.factotum.rin.dto.BudgetSummary(" +
            "bct.name, " +
            "bct.id, " +
            "b.transactionType, " +
            "SUM(b.amount * f.monthFactor)) " +
            "FROM Budget b " +
            "INNER JOIN b.budgetCategory bc " +
            "INNER JOIN bc.type as bct " +
            "INNER JOIN b.frequencyType f " +
            "WHERE b.startDate <= :startDate AND (b.endDate IS NULL OR b.endDate >= :endDate)" +
            "GROUP BY bct.name, b.transactionType " +
            "ORDER BY bct.name ")
    List<BudgetSummary> getBudgetSummaries(
            @Param("startDate") ZonedDateTime startDate,
            @Param("endDate") ZonedDateTime endDate);

}

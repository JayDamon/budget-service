package com.protean.moneymaker.rin.repository;

import com.protean.moneymaker.rin.dto.BudgetCategoryInUse;
import com.protean.moneymaker.rin.dto.BudgetSummary;
import com.protean.moneymaker.rin.model.Budget;
import com.protean.moneymaker.rin.model.BudgetCategoryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.Set;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {

    Set<Budget> findBudgetsByInUseTrue();

    Set<Budget> findBudgetsByInUseFalse();

    @Query(value = "SELECT DISTINCT new com.protean.mone" +
            "ymaker.rin.dto.BudgetCategoryInUse(" +
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
            "new com.protean.moneymaker.rin.dto.BudgetSummary(" +
                "bct.name, " +
                "bct.id, " +
                "t.name, " +
                "t.id, " +
                "SUM(b.amount * f.monthFactor)) " +
            "FROM Budget b " +
            "INNER JOIN b.budgetCategory bc " +
            "INNER JOIN bc.type as bct " +
            "INNER JOIN b.frequencyType f " +
            "INNER JOIN b.transactionType t " +
            "WHERE b.startDate <= :startDate AND (b.endDate IS NULL OR b.endDate >= :endDate)" +
            "GROUP BY bct.name, t.name " +
            "ORDER BY bct.name ")
    Set<BudgetSummary> getBudgetSummaries(
            @Param("startDate") ZonedDateTime startDate,
            @Param("endDate") ZonedDateTime endDate);

}

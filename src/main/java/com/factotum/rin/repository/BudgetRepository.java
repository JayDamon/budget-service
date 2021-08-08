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
import java.util.Optional;
import java.util.Set;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {

    Optional<Budget> findByIdAndTenantId(long id, String tenantId);

    Set<Budget> findAllByTenantId(String tenantId);

    Set<Budget> findBudgetsByInUseTrueAndTenantId(String tenantId);

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
            "   bct.name, " +
            "   bct.id, " +
            "   b.transactionTypeId, " +
            "   SUM(b.amount * f.monthFactor) " +
            ") " +
            "FROM Budget b " +
            "INNER JOIN b.budgetCategory bc " +
            "INNER JOIN bc.type as bct " +
            "INNER JOIN b.frequencyType f " +
            "WHERE b.startDate <= :startDate AND (b.endDate IS NULL OR b.endDate >= :endDate) " +
            "AND b.tenantId = :tenantId " +
            "GROUP BY bct.name, b.transactionTypeId " +
            "ORDER BY bct.name ")
    List<BudgetSummary> getBudgetSummaries(
            @Param("startDate") ZonedDateTime startDate,
            @Param("endDate") ZonedDateTime endDate,
            @Param("tenantId") String tenantId);

    @Query(value = "SELECT " +
            "new com.factotum.rin.dto.BudgetSummary(" +
            "   bct.name, " +
            "   bct.id, " +
            "   b.transactionTypeId, " +
            "   SUM(b.amount * f.monthFactor) " +
            ") " +
            "FROM Budget b " +
            "INNER JOIN b.budgetCategory bc " +
            "INNER JOIN bc.type as bct " +
            "INNER JOIN b.frequencyType f " +
            "WHERE b.startDate <= :startDate AND (b.endDate IS NULL OR b.endDate >= :endDate) " +
            "GROUP BY bct.name, b.transactionTypeId " +
            "ORDER BY bct.name ")
    List<BudgetSummary> getBudgetSummaries(
            @Param("startDate") ZonedDateTime startDate,
            @Param("endDate") ZonedDateTime endDate);

    @Query(value =
            "SELECT b.id FROM Budget b " +
                    "JOIN b.budgetCategory bc " +
                    "JOIN bc.type t " +
                    "WHERE b.startDate <= :startDate " +
                    "AND (b.endDate IS NULL OR b.endDate >= :endDate) " +
                    "AND t.id = :budgetCategoryTypeId " +
                    "AND b.transactionTypeId = :transactionTypeId " +
                    "AND b.tenantId = :tenantId")
    Set<Long> queryAllBudgetIdsForSummary(
            int transactionTypeId, int budgetCategoryTypeId, ZonedDateTime startDate, ZonedDateTime endDate, String tenantId);

}

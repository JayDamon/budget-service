package com.factotum.budgetservice.repository;

import com.factotum.budgetservice.dto.BudgetCategoryInUse;
import com.factotum.budgetservice.dto.BudgetSummary;
import com.factotum.budgetservice.enumeration.BudgetType;
import com.factotum.budgetservice.model.Budget;
import com.factotum.budgetservice.model.BudgetCategoryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, UUID> {

    Optional<Budget> findByIdAndTenantId(UUID id, String tenantId);

    @Query("SELECT b FROM Budget b " +
            "INNER JOIN b.budgetCategory bc " +
            "INNER JOIN bc.type t " +
            "INNER JOIN bc.name n " +
            "WHERE b.inUse = TRUE " +
            "AND b.tenantId = :tenantId " +
            "ORDER BY t.order, n.order")
    LinkedList<Budget> findBudgetsByInUseTrueAndTenantId(String tenantId);

    @Query(value = "SELECT DISTINCT new com.factotum.budgetservice.dto.BudgetCategoryInUse(" +
            "n.name, t.name) FROM Budget b " +
            "INNER JOIN b.budgetCategory bc " +
            "INNER JOIN bc.type t " +
            "INNER JOIN bc.name n ")
    Set<BudgetCategoryInUse> getAllBudgetCategoryInUse();

    @Query(value = "SELECT DISTINCT t FROM Budget b " +
            "INNER JOIN b.budgetCategory bc " +
            "INNER JOIN bc.type t")
    Set<BudgetCategoryType> getAllBudgetCategoryTypesInUse();

    @Query(value = """
            SELECT 
            new com.factotum.budgetservice.dto.BudgetSummary(
               bct.name, 
               bct.id, 
               bc.budgetType, 
               SUM(b.amount * f.monthFactor)
            ) 
            FROM Budget b 
            INNER JOIN b.budgetCategory bc 
            INNER JOIN bc.type as bct 
            INNER JOIN b.frequencyType f 
            WHERE b.startDate <= :startDate AND (b.endDate IS NULL OR b.endDate >= :endDate) 
            AND b.tenantId = :tenantId 
            GROUP BY bct.name, bct.id, bc.budgetType 
            ORDER BY bct.name 
            """
    )
    List<BudgetSummary> getBudgetSummaries(
            @Param("startDate") ZonedDateTime startDate,
            @Param("endDate") ZonedDateTime endDate,
            @Param("tenantId") String tenantId);

    @Query(value =
            "SELECT b.id FROM Budget b " +
                    "JOIN b.budgetCategory bc " +
                    "JOIN bc.type t " +
                    "WHERE b.startDate <= :startDate " +
                    "AND (b.endDate IS NULL OR b.endDate >= :endDate) " +
                    "AND t.id = :budgetCategoryTypeId " +
                    "AND bc.budgetType = :budgetType " +
                    "AND b.tenantId = :tenantId")
    Set<UUID> queryAllBudgetIdsForSummary(
            BudgetType budgetType, UUID budgetCategoryTypeId, ZonedDateTime startDate, ZonedDateTime endDate, String tenantId);

}

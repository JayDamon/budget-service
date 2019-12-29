package com.protean.moneymaker.rin.repository;

import com.protean.moneymaker.rin.dto.TransactionBudgetSummary;
import com.protean.moneymaker.rin.model.Transaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.Set;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long> {

    Set<Transaction> findAllByOrderByDateDesc();

    Set<Transaction> findAllByDateAfterAndDateBefore(ZonedDateTime startDate, ZonedDateTime after);

    @Query(value = "SELECT " +
            "new com.protean.moneymaker.rin.dto.TransactionBudgetSummary(" +
                "tt.name, bct.name, " +
                "month(t.date), year(t.date), " +
                "SUM(b.amount * f.monthFactor), ABS(SUM(t.amount)), " +
            "CASE " +
            "   WHEN tt.name = 'Income' AND " +
            "       SUM(b.amount * f.monthFactor) > SUM(t.amount) THEN false " +
            "   WHEN tt.name = 'Expense' AND " +
            "       SUM(b.amount * f.monthFactor) < (ABS(SUM(t.amount))) THEN false " +
            "   ELSE true END) " +
            "FROM Transaction As t " +
            "INNER JOIN t.budget as b " +
            "INNER JOIN b.frequencyType f " +
            "INNER JOIN b.budgetCategory bc " +
            "INNER JOIN bc.type as bct " +
            "INNER JOIN t.transactionType as tt " +
            "WHERE month(t.date) = :month AND year(t.date) = :year " +
            "   AND bct.id = :typeId AND tt.id = :tTypeId " +
            "GROUP BY month(t.date), year(t.date),  bct.name, tt.name " +
            "ORDER BY year(t.date), month(t.date), tt.name DESC, bct.name")
    Optional<TransactionBudgetSummary> getBudgetSummaries(
            @Param("year") int year,
            @Param("month") int month,
            @Param("typeId") int budgetCategoryTypeId,
            @Param("tTypeId") int transactionTypeId);

}

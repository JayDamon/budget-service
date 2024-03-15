package com.factotum.budgetservice.http;

import com.factotum.budgetservice.dto.TransactionTotal;
import com.factotum.budgetservice.enumeration.BudgetType;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;
import java.util.UUID;

@FeignClient(name = "moneymaker-transaction-service", path = "/v1/transactions")
public interface TransactionService {

    @GetMapping("/total")
    TransactionTotal getTransactionTotal(
            @RequestParam(name = "year") int year,
            @RequestParam(name = "month") int month,
            @RequestParam(name = "budgetType") BudgetType budgetType,
            @RequestParam(name = "budgetIds") Set<UUID> budgetIds);

}

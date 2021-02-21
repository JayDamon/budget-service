package com.factotum.rin.http;

import com.factotum.rin.dto.TransactionTotal;
import feign.QueryMap;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

@FeignClient(name = "oaka")
@RequestMapping("/v1/transactions")
public interface TransactionService {

    @GetMapping("/total")
    TransactionTotal getTransactionTotal(
            @RequestParam(name = "year") int year,
            @RequestParam(name = "month") int month,
            @RequestParam(name = "transactionTypeId") int transactionTypeId,
            @RequestParam(name = "budgetIds") Set<Long> budgetIds);

}

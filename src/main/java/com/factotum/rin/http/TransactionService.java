package com.factotum.rin.http;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "oaka")
@RequestMapping("/v1/transactions")
public interface TransactionService {
}

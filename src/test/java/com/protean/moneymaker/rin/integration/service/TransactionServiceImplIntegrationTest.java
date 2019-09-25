package com.protean.moneymaker.rin.integration.service;

import com.protean.moneymaker.rin.dto.TransactionDto;
import com.protean.moneymaker.rin.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;


@SpringBootTest
@ActiveProfiles("test")
class TransactionServiceImplIntegrationTest {

    @Autowired private TransactionService transactionService;

    @Test
    void getAllTransactionDtos() {

        // Arrange


        // Act
        Set<TransactionDto> dtos = transactionService.getAllTransactionDtos();

        // Assert
        assertThat(dtos, hasSize(837));


//
//        assertNotNull(dtos);
//        assertTrue(dtos.size() > 0);
//        assertEquals(837, dtos.size());
////        assertEquals(847, dtos.size());
//        TransactionDto dto = dtos.get(0);
//        dtos.forEach(System.out::println);
    }
}
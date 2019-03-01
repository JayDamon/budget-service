package com.protean.moneymaker.rin.service;

import com.protean.moneymaker.rin.bootstrap.LoadTestData;
import com.protean.moneymaker.rin.dto.TransactionDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class TransactionServiceImplTest {

    @Autowired
    TransactionService transactionService;

    @Test
    public void getAllTransactionDtos() {
        List<TransactionDto> dtos = transactionService.getAllTransactionDtos();
        assertNotNull(dtos);
        assertTrue(dtos.size() > 0);
        assertEquals(847, dtos.size());
        TransactionDto dto = dtos.get(0);
        assertEquals("02-19-2017", dto.getFormattedDate());
    }
}
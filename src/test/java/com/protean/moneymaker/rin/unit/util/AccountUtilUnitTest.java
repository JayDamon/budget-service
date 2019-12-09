package com.protean.moneymaker.rin.unit.util;

import com.protean.moneymaker.rin.dto.AccountClassificationDto;
import com.protean.moneymaker.rin.dto.AccountDto;
import com.protean.moneymaker.rin.dto.AccountTypeDto;
import com.protean.moneymaker.rin.model.Account;
import com.protean.moneymaker.rin.model.AccountClassification;
import com.protean.moneymaker.rin.model.AccountType;
import com.protean.moneymaker.rin.util.AccountUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyIterable;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AccountUtilUnitTest {

    private Account account;
    private AccountDto accountDto;

    @BeforeEach
    void setUp() {

        AccountType accountType = new AccountType("TestAccountType", "sht");
        accountType.setId(1);

        AccountClassification accountClassification = new AccountClassification("TestAccountClassification");
        accountClassification.setId(2);

        this.account = new Account("TestName", accountType, BigDecimal.valueOf(25.23),
                BigDecimal.valueOf(50.22), accountClassification, true, false);
        this.account.setId(3L);


        AccountTypeDto accountTypeDto = new AccountTypeDto(2, "Savings", "Savings");

        AccountClassificationDto classificationDto = new AccountClassificationDto(2, "Liability");

        this.accountDto = new AccountDto(
                1L, "NewName", accountTypeDto, BigDecimal.valueOf(500.01),
                BigDecimal.valueOf(200), classificationDto, false, false);
    }

    @Test
    void mapEntityCollectionToDtoCollection_givenValidAccounts_ThenFullyMapToDto() {

        // Act
        Collection<AccountDto> dtos = AccountUtil.mapEntityCollectionToDtoCollection(Collections.singletonList(this.account));

        // Assert
        for (AccountDto dto : dtos) {
            assertThat(dto.getId(), is(equalTo(3L)));
            assertThat(dto.getName(), is(equalTo("TestName")));
            assertThat(dto.getStartingBalance(), is(equalTo(BigDecimal.valueOf(25.23))));
            assertThat(dto.getCurrentbalance(), is(equalTo(BigDecimal.valueOf(50.22))));
            assertThat(dto.getPrimary(), is(true));
            assertThat(dto.getInCashFlow(), is(false));

            assertThat(dto.getType().getId(), is(equalTo(1)));
            assertThat(dto.getType().getFullName(), is(equalTo("TestAccountType")));
            assertThat(dto.getType().getShortName(), is(equalTo("sht")));

            assertThat(dto.getClassification().getId(), is(equalTo(2)));
            assertThat(dto.getClassification().getName(), is(equalTo("TestAccountClassification")));
        }

    }

    @Test
    void mapEntityCollectionToDtoCollection_GivenAccountsNull_ThenThrowIllegalArgumentException() {

        assertThrows(IllegalArgumentException.class,
                () -> AccountUtil.mapEntityCollectionToDtoCollection(null));

    }

    @Test
    void mapEntityToDto_givenValidAccount_ThenFullyMapToDto() {

        // Act
        AccountDto dto = AccountUtil.mapEntityToDto(this.account);

        // Assert
        assertThat(dto.getId(), is(equalTo(3L)));
        assertThat(dto.getName(), is(equalTo("TestName")));
        assertThat(dto.getStartingBalance(), is(equalTo(BigDecimal.valueOf(25.23))));
        assertThat(dto.getCurrentbalance(), is(equalTo(BigDecimal.valueOf(50.22))));
        assertThat(dto.getPrimary(), is(true));
        assertThat(dto.getInCashFlow(), is(false));

        assertThat(dto.getType().getId(), is(equalTo(1)));
        assertThat(dto.getType().getFullName(), is(equalTo("TestAccountType")));
        assertThat(dto.getType().getShortName(), is(equalTo("sht")));

        assertThat(dto.getClassification().getId(), is(equalTo(2)));
        assertThat(dto.getClassification().getName(), is(equalTo("TestAccountClassification")));

    }

    @Test
    void mapEntityToDto_GivenAccountIsNull_ThenThrowIllegalArgumentException() {

        assertThrows(IllegalArgumentException.class,
                () -> AccountUtil.mapEntityToDto(null));

    }

    @Test
    void mapDtoToEntityOnlyNonNull_GivenValidDto_ThenMapOnlyProvidedFields() {

        this.accountDto.setClassification(null);
        this.accountDto.setName(null);

        Account a = AccountUtil.mapDtoToEntityOnlyNonNull(this.accountDto);

        assertThat(a.getId(), is(equalTo(this.accountDto.getId())));
        assertThat(a.getName(), is(nullValue()));
        assertThat(a.getStartingBalance(), is(equalTo(this.accountDto.getStartingBalance())));
        assertThat(a.getCurrentBalance(), is(nullValue()));
        assertThat(a.getPrimaryAccount(), is(equalTo(this.accountDto.getPrimary())));
        assertThat(a.getInCashFlow(), is(equalTo(this.accountDto.getInCashFlow())));
        assertThat(a.getAccountType().getId(), is(equalTo(this.accountDto.getType().getId())));
        assertThat(a.getAccountType().getFullName(), is(equalTo(this.accountDto.getType().getFullName())));
        assertThat(a.getAccountType().getShortName(), is(equalTo(this.accountDto.getType().getShortName())));
        assertThat(a.getAccountClassification(), is(nullValue()));

    }

    @Test
    void mapDtoToEntityOnlyNonNull_GivenAccountProvided_ThenMapOnlyProvidedFields() {

        this.accountDto.setClassification(null);
        this.accountDto.setName(null);

        Account a = AccountUtil.mapDtoToEntityOnlyNonNull(this.accountDto, this.account);

        assertThat(a.getId(), is(equalTo(this.accountDto.getId())));
        assertThat(a.getName(), is(equalTo(this.account.getName())));
        assertThat(a.getStartingBalance(), is(equalTo(this.accountDto.getStartingBalance())));
        assertThat(a.getCurrentBalance(), is(equalTo(this.account.getCurrentBalance())));
        assertThat(a.getPrimaryAccount(), is(equalTo(this.accountDto.getPrimary())));
        assertThat(a.getInCashFlow(), is(equalTo(this.accountDto.getInCashFlow())));
        assertThat(a.getAccountType().getId(), is(equalTo(this.accountDto.getType().getId())));
        assertThat(a.getAccountType().getFullName(), is(equalTo(this.accountDto.getType().getFullName())));
        assertThat(a.getAccountType().getShortName(), is(equalTo(this.accountDto.getType().getShortName())));
        assertThat(a.getAccountClassification().getId(), is(equalTo(this.account.getAccountClassification().getId())));
        assertThat(a.getAccountClassification().getName(), is(equalTo(this.account.getAccountClassification().getName())));

    }

    @Test
    void mapDtoToEntityOnlyNonNull_GivenDtoNull_ThenThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> AccountUtil.mapDtoToEntityOnlyNonNull(null));
    }

    @Test
    void mapDtoToEntityOnlyNonNull_GivenDtoNullAndAccountProvided_ThenThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> AccountUtil.mapDtoToEntityOnlyNonNull(null, this.account));
    }

    @Test
    void mapDtoToEntityOnlyNonNull_GivenAccountNull_ThenThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> AccountUtil.mapDtoToEntityOnlyNonNull(this.accountDto, null));
    }

    @Test
    void mapAccountTypesToDtos_GivenValidTypes_ThenMapToDtos() {

        AccountType type = new AccountType("FullName", "ShortName");
        type.setId(1);

        Collection<AccountTypeDto> types = AccountUtil.mapAccountTypesToDtos(Collections.singletonList(type));

        for (AccountTypeDto dto : types) {
            assertThat(dto.getId(), is(equalTo(1)));
            assertThat(dto.getShortName(), is(equalTo("ShortName")));
            assertThat(dto.getFullName(), is(equalTo("FullName")));
        }

    }

    @Test
    void mapAccountTypesToDtos_GivenNullCollectionProvided_ThenThrowIllegalArgumentException() {

        assertThrows(IllegalArgumentException.class, () -> AccountUtil.mapAccountTypesToDtos(null));

    }

    @Test
    void mapAccountTypesToDtos_GivenEmptyCollectionProvided_ThenReturnEmptyCollection() {

        assertThat(AccountUtil.mapAccountTypesToDtos(new ArrayList<>()), is(emptyIterable()));

    }

    @Test
    void mapAccountClassificationsToDtos_GivenValidClassifications_ThenMapToDtos() {

        AccountClassification classification = new AccountClassification("TestName");
        classification.setId(1);

        Collection<AccountClassificationDto> classifications = AccountUtil.mapAccountClassificationsToDtos(Collections.singletonList(classification));

        for (AccountClassificationDto dto : classifications) {
            assertThat(dto.getId(), is(equalTo(1)));
            assertThat(dto.getName(), is(equalTo("TestName")));
        }

    }

    @Test
    void mapAccountClassificationsToDtos_GivenNullCollectionProvided_ThenThrowIllegalArgumentException() {

        assertThrows(IllegalArgumentException.class, () -> AccountUtil.mapAccountClassificationsToDtos(null));

    }

    @Test
    void mapAccountClassificationsToDtos_GivenEmptyCollectionProvided_ThenReturnEmptyCollection() {

        assertThat(AccountUtil.mapAccountClassificationsToDtos(new ArrayList<>()), is(emptyIterable()));

    }


}

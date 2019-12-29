package com.protean.moneymaker.rin.util;

import com.protean.moneymaker.rin.dto.AccountClassificationDto;
import com.protean.moneymaker.rin.dto.AccountDto;
import com.protean.moneymaker.rin.dto.AccountTypeDto;
import com.protean.moneymaker.rin.model.Account;
import com.protean.moneymaker.rin.model.AccountClassification;
import com.protean.moneymaker.rin.model.AccountType;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.Provider;
import org.modelmapper.TypeMap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AccountUtil {

    private static ModelMapper mapper = new ModelMapper();

    public static Collection<AccountDto> mapEntityCollectionToDtoCollection(Collection<Account> accounts) {

        if (accounts == null) {
            throw new IllegalArgumentException("Valid collection must be provided");
        }

        List<AccountDto> dtos = new ArrayList<>();

        for (Account a : accounts) {
            dtos.add(mapEntityToDto(a));
        }

        return dtos;
    }

    public static AccountDto mapEntityToDto(Account account) {
        return mapper.map(account, AccountDto.class);
    }

    public static Account mapDtoToEntityOnlyNonNull(AccountDto accountDto) {

        return mapDtoToEntityOnlyNonNull(accountDto, new Account());
    }

    public static Account mapDtoToEntityOnlyNonNull(AccountDto accountDto, Account account) {

        if (accountDto == null) {
            throw new IllegalArgumentException("Provided dto must not be null");
        }
        if (account == null) {
            throw new IllegalArgumentException("Provided account must not be null");
        }

        ModelMapper mapper = new ModelMapper();

        mapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());

        Provider<Account> accountProvider = req -> account;

        TypeMap<AccountDto, Account> typeMap = mapper.createTypeMap(AccountDto.class, Account.class);
        typeMap.setProvider(accountProvider);
        typeMap.addMappings(mapping -> mapping.skip(Account::setCurrentBalance));

//        typeMap.addMappings(mapping -> mapping.with(accountProvider).map())

        return typeMap.map(accountDto);
    }

    public static Collection<AccountTypeDto> mapAccountTypesToDtos(Collection<AccountType> types) {

        if (types == null) {
            throw new IllegalArgumentException("Non null collection must be provided");
        }

        Collection<AccountTypeDto> dtos = new ArrayList<>();

        for (AccountType t : types) {
            dtos.add(mapper.map(t, AccountTypeDto.class));
        }

        return dtos;
    }

    public static Collection<AccountClassificationDto> mapAccountClassificationsToDtos(Collection<AccountClassification> classifications) {

        if (classifications == null) {
            throw new IllegalArgumentException("Non null collection must be provided");
        }

        Collection<AccountClassificationDto> dtos = new ArrayList<>();

        for (AccountClassification c  : classifications) {
            dtos.add(mapper.map(c, AccountClassificationDto.class));
        }

        return dtos;
    }
}

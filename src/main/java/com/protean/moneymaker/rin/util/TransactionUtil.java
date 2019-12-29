package com.protean.moneymaker.rin.util;

import com.protean.moneymaker.rin.dto.TransactionCategoryDto;
import com.protean.moneymaker.rin.model.TransactionCategory;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TransactionUtil {

    private static ModelMapper mapper = new ModelMapper();

    private TransactionUtil() {}

    public static Collection<TransactionCategoryDto> mapEntityListToDtoCollection(Collection<TransactionCategory> categories) {

        if (categories == null) {
            throw new IllegalArgumentException("Categories must not be null");
        }

        List<TransactionCategoryDto> dtos = new ArrayList<>();

        for (TransactionCategory cat : categories) {
            dtos.add(mapEntityToDto(cat));
        }

        return dtos;
    }

    public static TransactionCategoryDto mapEntityToDto(TransactionCategory category) {
        return mapper.map(category, TransactionCategoryDto.class);
    }

}

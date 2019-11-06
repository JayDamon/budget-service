package com.protean.moneymaker.rin.util;

import com.protean.moneymaker.rin.dto.FrequencyTypeDto;
import com.protean.moneymaker.rin.model.FrequencyType;
import org.modelmapper.ModelMapper;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class FrequencyUtil {

    private static ModelMapper modelMapper = new ModelMapper();

    private FrequencyUtil() {
    }

    public static Set<FrequencyTypeDto> convertFrequencyTypesToDto(
            Collection<FrequencyType> types) {

        if (types == null) {
            throw new IllegalArgumentException("Frequency Types must not be null");
        }

        Set<FrequencyTypeDto> dtos = new HashSet<>();
        for (FrequencyType type : types) {
            dtos.add(modelMapper.map(type, FrequencyTypeDto.class));
        }

        return dtos;
    }
}


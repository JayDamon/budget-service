package com.factotum.rin.util;

import com.factotum.rin.dto.FrequencyTypeDto;
import com.factotum.rin.model.FrequencyType;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.oneOf;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FrequencyUtilUT {

    //    convertFrequencyTypesToDto
    @Test
    void convertFrequencyTypesToDto_GivenValidCollectionProvided_ThenReturnSetOfDtos() {

        // Arrange
        FrequencyType frequencyType = new FrequencyType();
        frequencyType.setId(1);
        frequencyType.setFrequencyTypeName("Name");

        FrequencyType frequencyTypeTwo = new FrequencyType();
        frequencyTypeTwo.setId(2);
        frequencyTypeTwo.setFrequencyTypeName("NameTwo");

        Set<FrequencyType> frequencyTypes = new HashSet<>();
        frequencyTypes.add(frequencyType);
        frequencyTypes.add(frequencyTypeTwo);

        // Act
        Set<FrequencyTypeDto> frequencyTypeDtos = FrequencyUtil.convertFrequencyTypesToDto(frequencyTypes);

        // Assert
        assertThat(frequencyTypeDtos, hasSize(2));

        for (FrequencyTypeDto dto : frequencyTypeDtos) {
            assertThat(dto.getId(), is(oneOf(1, 2)));
            assertThat(dto.getName(), is(oneOf("Name", "NameTwo")));
        }

    }

    @Test
    void convertFrequencyTypesToDto_GivenCollectionIsNull_ThenThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> FrequencyUtil.convertFrequencyTypesToDto(null));
    }

}

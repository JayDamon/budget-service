package com.protean.moneymaker.rin.unit.util;

import com.protean.moneymaker.rin.dto.FrequencyTypeDto;
import com.protean.moneymaker.rin.model.FrequencyType;
import com.protean.moneymaker.rin.util.FrequencyUtil;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.oneOf;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FrequencyUtilUnitTest {

//    convertFrequencyTypesToDto
    @Test
    void convertFrequencyTypesToDto_GivenValidCollectionProvided_ThenReturnSetOfDtos() {

        // Arrange
        FrequencyType frequencyType = new FrequencyType();
        frequencyType.setId(1);
        frequencyType.setName("Name");

        FrequencyType frequencyTypeTwo = new FrequencyType();
        frequencyTypeTwo.setId(2);
        frequencyTypeTwo.setName("NameTwo");

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

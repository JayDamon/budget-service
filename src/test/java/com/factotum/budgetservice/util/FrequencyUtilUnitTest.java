package com.factotum.budgetservice.util;

import com.factotum.budgetservice.dto.FrequencyTypeDto;
import com.factotum.budgetservice.model.FrequencyType;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

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
        UUID frequencyTypeIdOne = UUID.fromString("daf7d54a-1cea-4e1d-818a-bfde558870d7");
        UUID frequencyTypeIdTwo = UUID.fromString("f5b475fe-3de6-4df1-8eaa-dba249450b58");

        FrequencyType frequencyType = new FrequencyType();
        frequencyType.setId(frequencyTypeIdOne);
        frequencyType.setFrequencyTypeName("Name");

        FrequencyType frequencyTypeTwo = new FrequencyType();
        frequencyTypeTwo.setId(frequencyTypeIdTwo);
        frequencyTypeTwo.setFrequencyTypeName("NameTwo");

        Set<FrequencyType> frequencyTypes = new HashSet<>();
        frequencyTypes.add(frequencyType);
        frequencyTypes.add(frequencyTypeTwo);

        // Act
        Set<FrequencyTypeDto> frequencyTypeDtos = FrequencyUtil.convertFrequencyTypesToDto(frequencyTypes);

        // Assert
        assertThat(frequencyTypeDtos, hasSize(2));

        for (FrequencyTypeDto dto : frequencyTypeDtos) {
            assertThat(dto.getId(), is(oneOf(frequencyTypeIdOne, frequencyTypeIdTwo)));
            assertThat(dto.getName(), is(oneOf("Name", "NameTwo")));
        }

    }

    @Test
    void convertFrequencyTypesToDto_GivenCollectionIsNull_ThenThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> FrequencyUtil.convertFrequencyTypesToDto(null));
    }

}

package com.protean.moneymaker.rin.unit.service;

import com.protean.moneymaker.rin.model.FrequencyType;
import com.protean.moneymaker.rin.repository.FrequencyTypeRepository;
import com.protean.moneymaker.rin.service.FrequencyService;
import com.protean.moneymaker.rin.service.FrequencyServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyIterable;
import static org.hamcrest.Matchers.oneOf;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FrequencyServiceImplUnitTest {

    private FrequencyService frequencyService;
    @Mock private FrequencyTypeRepository frequencyTypeRepository;

    @BeforeEach
    void setUp() {
        this.frequencyService = new FrequencyServiceImpl(frequencyTypeRepository);
    }


    @Test
    void getAllFrequencyTypes_GivenFrequencyTypesExist_ThenReturnAllTypes() {

        // Arrange
        FrequencyType frequencyType = new FrequencyType();
        frequencyType.setId(1);
        frequencyType.setName("Name");

        FrequencyType frequencyTypeTwo = new FrequencyType();
        frequencyTypeTwo.setId(2);
        frequencyTypeTwo.setName("NameTwo");

        List<FrequencyType> frequencyTypes = new ArrayList<>();
        frequencyTypes.add(frequencyType);
        frequencyTypes.add(frequencyTypeTwo);

        when(frequencyTypeRepository.findAll()).thenReturn(frequencyTypes);

        // Act
        List<FrequencyType> types = frequencyService.getAllFrequencyTypes();

        // Assert
        assertThat(types, hasSize(2));

        for (FrequencyType type : types) {
            assertThat(type.getId(), is(oneOf(1, 2)));
            assertThat(type.getName(), is(oneOf("Name", "NameTwo")));
        }

    }

    @Test
    void getAllFrequencyTypes_GivenFrequencyTypesDoNotExist_ThenReturnEmptyList() {

        // Arrange
        when(frequencyTypeRepository.findAll()).thenReturn(Collections.emptyList());

        // Act
        List<FrequencyType> types = frequencyService.getAllFrequencyTypes();

        // Assert
        assertThat(types, is(emptyIterable()));
    }

}

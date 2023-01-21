package com.factotum.budgetservice.service;

import com.factotum.budgetservice.model.FrequencyType;
import com.factotum.budgetservice.repository.FrequencyTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.NoResultException;
import java.util.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FrequencyServiceImplUnitTest {

    private FrequencyService frequencyService;
    @Mock
    private FrequencyTypeRepository frequencyTypeRepository;

    private static final UUID frequencyTypeIdOne = UUID.randomUUID();
    private static final UUID frequencyTypeIdTwo = UUID.randomUUID();

    @BeforeEach
    void setUp() {
        this.frequencyService = new FrequencyServiceImpl(frequencyTypeRepository);
    }


    @Test
    void getAllFrequencyTypes_GivenFrequencyTypesExist_ThenReturnAllTypes() {

        // Arrange
        FrequencyType frequencyType = createFrequencyType(frequencyTypeIdOne, "Name");

        FrequencyType frequencyTypeTwo = createFrequencyType(frequencyTypeIdTwo, "NameTwo");

        List<FrequencyType> frequencyTypes = new ArrayList<>();
        frequencyTypes.add(frequencyType);
        frequencyTypes.add(frequencyTypeTwo);

        when(frequencyTypeRepository.findAll()).thenReturn(frequencyTypes);

        // Act
        List<FrequencyType> types = frequencyService.getAllFrequencyTypes();

        // Assert
        assertThat(types, hasSize(2));

        for (FrequencyType type : types) {
            assertThat(type.getId(), is(oneOf(frequencyTypeIdOne, frequencyTypeIdTwo)));
            assertThat(type.getFrequencyTypeName(), is(oneOf("Name", "NameTwo")));
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

    //    getFrequencyTypeById
    @Test
    void getFrequencyTypeById_GivenFrequencyTypeExists_ThenReturnFrequencyType() {

        // Arrange
        FrequencyType frequencyType = createFrequencyType(frequencyTypeIdOne, "testName");
        when(frequencyTypeRepository.findById(eq(frequencyTypeIdOne))).thenReturn(Optional.of(frequencyType));

        // Act
        FrequencyType retrievedFrequencyType = frequencyService.getFrequencyTypeById(frequencyTypeIdOne);

        // Assert
        assertThat(retrievedFrequencyType, is(not(nullValue())));
        assertThat(retrievedFrequencyType.getFrequencyTypeName(), is(equalTo("testName")));
        assertThat(retrievedFrequencyType.getId(), is(equalTo(frequencyTypeIdOne)));
    }

    @Test
    void getFrequencyTypeById_GivenIdDoesNotExist_ThenThrowNoResultFoundException() {

        when(frequencyTypeRepository.findById(eq(frequencyTypeIdTwo))).thenReturn(Optional.empty());

        assertThrows(NoResultException.class, () -> frequencyService.getFrequencyTypeById(frequencyTypeIdTwo));

    }

    private FrequencyType createFrequencyType(UUID id, String name) {
        FrequencyType frequencyType = new FrequencyType();
        frequencyType.setId(id);
        frequencyType.setFrequencyTypeName(name);
        return frequencyType;
    }

}

package com.factotum.budgetservice.service;

import com.factotum.budgetservice.model.FrequencyType;

import java.util.List;
import java.util.UUID;

public interface FrequencyService {

    List<FrequencyType> getAllFrequencyTypes();

    FrequencyType getFrequencyTypeById(UUID id);

}

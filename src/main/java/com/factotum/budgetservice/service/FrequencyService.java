package com.factotum.budgetservice.service;

import com.factotum.budgetservice.model.FrequencyType;

import java.util.List;

public interface FrequencyService {

    List<FrequencyType> getAllFrequencyTypes();

    FrequencyType getFrequencyTypeById(int id);

}

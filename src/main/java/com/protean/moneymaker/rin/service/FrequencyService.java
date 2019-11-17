package com.protean.moneymaker.rin.service;

import com.protean.moneymaker.rin.model.FrequencyType;

import java.util.List;

public interface FrequencyService {

    List<FrequencyType> getAllFrequencyTypes();

    FrequencyType getFrequencyTypeById(int id);

}

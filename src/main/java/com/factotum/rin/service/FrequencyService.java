package com.factotum.rin.service;

import com.factotum.rin.model.FrequencyType;

import java.util.List;

public interface FrequencyService {

    List<FrequencyType> getAllFrequencyTypes();

    FrequencyType getFrequencyTypeById(int id);

}

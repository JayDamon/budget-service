package com.protean.moneymaker.rin.service;

import com.protean.moneymaker.rin.model.FrequencyType;
import com.protean.moneymaker.rin.repository.FrequencyTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FrequencyServiceImpl implements FrequencyService {

    private FrequencyTypeRepository frequencyTypeRepository;

    public FrequencyServiceImpl(FrequencyTypeRepository frequencyTypeRepository) {
        this.frequencyTypeRepository = frequencyTypeRepository;
    }

    @Override
    public List<FrequencyType> getAllFrequencyTypes() {
        return frequencyTypeRepository.findAll();
    }

}

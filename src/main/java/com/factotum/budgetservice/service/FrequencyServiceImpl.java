package com.factotum.budgetservice.service;

import com.factotum.budgetservice.model.FrequencyType;
import com.factotum.budgetservice.repository.FrequencyTypeRepository;
import jakarta.persistence.NoResultException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FrequencyServiceImpl implements FrequencyService {

    private final FrequencyTypeRepository frequencyTypeRepository;

    public FrequencyServiceImpl(FrequencyTypeRepository frequencyTypeRepository) {
        this.frequencyTypeRepository = frequencyTypeRepository;
    }

    @Override
    public List<FrequencyType> getAllFrequencyTypes() {
        return frequencyTypeRepository.findAll();
    }

    @Override
    public FrequencyType getFrequencyTypeById(UUID id) {

        return frequencyTypeRepository.findById(id).orElseThrow(
                () -> new NoResultException("No frequency type with id <" + id + "> was found."));

    }

}

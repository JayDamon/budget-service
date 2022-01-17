package com.factotum.budgetservice.service;

import com.factotum.budgetservice.model.FrequencyType;
import com.factotum.budgetservice.repository.FrequencyTypeRepository;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.List;

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
    public FrequencyType getFrequencyTypeById(int id) {

        return frequencyTypeRepository.findById(id).orElseThrow(
                () -> new NoResultException("No frequency type with id <" + id + "> was found."));

    }

}

package com.factotum.budgetservice.repository;

import com.factotum.budgetservice.model.FrequencyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FrequencyTypeRepository extends JpaRepository<FrequencyType, UUID> {
}

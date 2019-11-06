package com.protean.moneymaker.rin.repository;

import com.protean.moneymaker.rin.model.FrequencyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FrequencyTypeRepository extends JpaRepository<FrequencyType, Integer> {
}

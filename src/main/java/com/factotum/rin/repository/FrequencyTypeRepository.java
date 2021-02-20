package com.factotum.rin.repository;

import com.factotum.rin.model.FrequencyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FrequencyTypeRepository extends JpaRepository<FrequencyType, Integer> {
}

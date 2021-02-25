package com.factotum.rin.repository;

import com.factotum.rin.model.GoalType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoalTypeRepository extends CrudRepository<GoalType, Integer> {
}

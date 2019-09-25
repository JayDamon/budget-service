package com.protean.moneymaker.rin.repository;

import com.protean.moneymaker.rin.model.GoalType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoalTypeRepository extends CrudRepository<GoalType, Integer> {
}

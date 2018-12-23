package com.protean.moneymaker.rin.repository;

import com.protean.moneymaker.rin.model.Goal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface GoalRepository extends CrudRepository<Goal, Long> {
}

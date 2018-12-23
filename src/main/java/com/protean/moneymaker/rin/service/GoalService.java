package com.protean.moneymaker.rin.service;

import com.protean.moneymaker.rin.model.Goal;
import com.protean.moneymaker.rin.model.GoalType;

import java.util.List;

public interface GoalService {

    List<GoalType> getGoalTypes(); // TODO uses GoalTypeRepository

    List<Goal> getAllGoals();

    Goal saveGoal(Goal goal);

    List<Goal> saveGoals(List<Goal> goals);

    void deleteGoal(Goal id);

    void deleteGoals(List<Goal> ids);

}

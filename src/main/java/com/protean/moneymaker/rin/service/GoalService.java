package com.protean.moneymaker.rin.service;

import com.protean.moneymaker.rin.model.Goal;

import java.util.List;

public interface GoalService {

    List<String> findGoalTypes(); // TODO probably need goal type service

    List<Goal> findAllGoals();

    Goal saveGoal(Goal goal);

    List<Goal> saveGoals(List<Goal> goals);

}

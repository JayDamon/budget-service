package com.protean.moneymaker.rin.service;

import com.protean.moneymaker.rin.model.Goal;
import com.protean.moneymaker.rin.model.GoalType;
import com.protean.moneymaker.rin.repository.GoalRepository;
import com.protean.moneymaker.rin.repository.GoalTypeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GoalServiceImpl implements GoalService {

    private GoalRepository goalRepository;
    private GoalTypeRepository goalTypeRepository;

    public GoalServiceImpl(GoalRepository goalRepository, GoalTypeRepository goalTypeRepository) {
        this.goalRepository = goalRepository;
        this.goalTypeRepository = goalTypeRepository;
    }

    @Override
    public List<GoalType> getGoalTypes() {

        List<GoalType> goalTypes = new ArrayList<>();
        goalTypeRepository.findAll().forEach(goalTypes::add);

        return goalTypes;
    }

    @Override
    public List<Goal> getAllGoals() {

        List<Goal> goals = new ArrayList<>();
        goalRepository.findAll().forEach(goals::add);

        return goals;
    }

    @Override
    public Goal saveGoal(Goal goal) {
        return goalRepository.save(goal);
    }

    @Override
    public List<Goal> saveGoals(List<Goal> goals) {

        List<Goal> savedGoals = new ArrayList<>();
        goalRepository.saveAll(goals).forEach(savedGoals::add);

        return savedGoals;
    }

    @Override
    public void deleteGoal(Goal goal) {
        goalRepository.delete(goal);
    }

    @Override
    public void deleteGoals(List<Goal> goals) {
        goalRepository.deleteAll(goals);
    }
}

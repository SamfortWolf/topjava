package ru.javawebinar.topjava.DAO;


import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealMemory;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentSkipListMap;

public class MealsDAOImpl implements MealsDAO {

    @Override
    public void addMeal(Meal meal) {
        MealMemory.addMeal(meal);
    }

    @Override
    public ConcurrentSkipListMap<Integer, MealTo> getAllMeals() {
        return MealsUtil.getMealsToMap(MealMemory.getMemoryMap(), MealsUtil.getDefaultCaloriesPerDay());
    }

    @Override
    public void updateMeal(int id, LocalDateTime ldt, String description, int calories) {
        MealMemory.setMeal(id, ldt, description, calories);
    }

    @Override
    public void deleteMealById(int id) {
        MealMemory.deleteMeal(id);
    }

    @Override
    public Meal getMealById(int mealId) {
        return MealMemory.getMeal(mealId);
    }
}


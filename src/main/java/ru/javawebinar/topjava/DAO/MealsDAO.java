package ru.javawebinar.topjava.DAO;


import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;

import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentSkipListMap;

public interface MealsDAO {
    void addMeal(Meal meal);
    ConcurrentSkipListMap<Integer, MealTo> getAllMeals();
    void updateMeal(int id, LocalDateTime ldt, String description, int calories);
    void deleteMealById(int id);
    Meal getMealById(int mealId);
}

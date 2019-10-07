package ru.javawebinar.topjava.DAO;


import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class MealsDAOImpl implements MealsDAO {

    @Override
    public void addMeal(Meal meal) {
        MealsUtil.addMeal(meal);
    }

    @Override
    public ArrayList<MealTo> getAllMeals() {
        return MealsUtil.getMealsTo();
    }

    @Override
    public void updateMeal(int id, LocalDateTime ldt, String description, int calories) {
        MealsUtil.setMeal(id, ldt, description, calories);
    }

    @Override
    public void deleteMealById(int id) {
        MealsUtil.deleteMeal(id);
    }

    @Override
    public Meal getMealById(int mealId) {
        return MealsUtil.getMeal(mealId);
    }
}


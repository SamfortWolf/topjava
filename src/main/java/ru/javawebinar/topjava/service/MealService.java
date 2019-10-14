package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.List;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.util.ValidationUtil.*;

import ru.javawebinar.topjava.util.exception.NotFoundException;

@Service
public class MealService {

    private MealRepository repository;

    @Autowired
    public MealService(MealRepository repository) {
        this.repository = repository;
    }

    public List<MealTo> getAll(int userId) {
        List<MealTo> meals = MealsUtil.getTos(repository.getAll(), MealsUtil.DEFAULT_CALORIES_PER_DAY)
                .stream()
                .filter(mealTo -> mealTo.getUserId() == userId)
                .collect(Collectors.toList());
        return meals;
    }

    public Meal create(Meal meal, int userId) {
        if (meal.isNew()) {
            meal.setUserId(userId);
        }
        return repository.save(meal);
    }

    public Meal get(int mealId, int userId) {
        Meal m = checkNotFoundWithId(repository.get(mealId), mealId);
        if (m.getUserId() != userId) {
            throw new NotFoundException("It's not your entity with mealId=");
        } else {
            return m;
        }
    }

    public void delete(int mealId, int userId) {
        get(mealId, userId);
        repository.delete(mealId);
    }

    public void update(Meal updatedMeal, int mealId, int userId) {
        Meal originalMeal = get(mealId, userId);
        updatedMeal.setId(originalMeal.getId());
        updatedMeal.setUserId(userId);
        repository.save(updatedMeal);

    }
}
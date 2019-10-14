package ru.javawebinar.topjava.web.meal;

import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealTo;

import java.util.List;

@Controller
public class MealRestController extends AbstractMealController {
    @Override
    public List<MealTo> getAll() {
        return super.getAll();
    }

    @Override
    public Meal create(Meal meal) {
        return super.create(meal);
    }

    @Override
    public Meal get(int mealId) {
        return super.get(mealId);
    }

    @Override
    public void delete(int mealId) {
        super.delete(mealId);
    }

    @Override
    public void update(Meal meal, int mealId) {
        super.update(meal, mealId);
    }
}
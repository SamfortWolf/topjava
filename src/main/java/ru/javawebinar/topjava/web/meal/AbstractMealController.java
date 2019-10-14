package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.javawebinar.topjava.web.SecurityUtil.authUserId;

public abstract class AbstractMealController {
    private static final Logger log = getLogger(AbstractMealController.class);

    @Autowired
    private MealService service;

    public List<MealTo> getAll() {
        log.info("getAll");
        return service.getAll(authUserId());
    }

    public Meal create(Meal meal) {
        log.info("create {}", meal);
        return service.create(meal, authUserId());
    }

    public Meal get(int mealId) {
        log.info("get {}", mealId);
        return service.get(mealId, authUserId());
    }

    public void delete(int mealId) {
        log.info("delete {}", mealId);
        service.delete(mealId, authUserId());
    }

    public void update(Meal meal, int mealId) {
        log.info("update {} with MealId={}", meal, mealId);
        service.update(meal, mealId, authUserId());
    }
}

package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.util.List;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.getLogger;

import static ru.javawebinar.topjava.util.ValidationUtil.*;

public abstract class AbstractMealController {
    private static final Logger log = getLogger(AbstractMealController.class);

    @Autowired
    private MealService service;

    public List<MealTo> getAll() {
        log.info("getAll");
        return service.getAll()
                .stream()
                .filter(mealTo -> mealTo.getUserId() == SecurityUtil.authUserId())
                .collect(Collectors.toList());
    }

    public Meal get(int id) {
        log.info("get {}", id);
        Meal m = service.get(id);
        if (m.getUserId() != SecurityUtil.authUserId()) {
            throw new NotFoundException("It's not your entity with id=" + id);
        } else {
            return m;
        }
    }

    public Meal create(Meal meal) {
        log.info("create {}", meal);
        checkNew(meal);
        meal.setUserId(SecurityUtil.authUserId());
        return service.create(meal);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        if (get(id) != null) {
            service.delete(id);
        } else throw new NotFoundException("It's not your entity with id=" + id);
    }

    public void update(Meal meal, int id) {
        log.info("update {} with UserId={}", meal, id);
        Meal m = get(id);
        if (m.getUserId() != SecurityUtil.authUserId()) {
            throw new NotFoundException("It's not your entity with id=" + id);
        } else {
            meal.setId(m.getId());
            meal.setUserId(SecurityUtil.authUserId());
            service.update(meal);
        }
    }
}

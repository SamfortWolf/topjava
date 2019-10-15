package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.javawebinar.topjava.web.SecurityUtil.*;

public abstract class AbstractMealController {
    private static final Logger log = getLogger(AbstractMealController.class);

    @Autowired
    private MealService service;

    public List<MealTo> getAll() {
        log.info("getAll");
        return service.getAll(authUserId(), authUserCaloriesPerDay());
    }

    public List<MealTo> getAllFiltered(String dateFrom, String dateTo, String timeFrom, String timeTo) {
        log.info("getAllFiltered");
        authUserFilter().setTimeFrom(timeFrom.equals("") ? LocalTime.parse("00:00") : LocalTime.parse(timeFrom));
        authUserFilter().setTimeTo(timeTo.equals("") ? LocalTime.parse("23:59") : LocalTime.parse(timeTo));
        authUserFilter().setDateFrom(dateFrom.equals("") ? LocalDate.parse("1970-01-01") : LocalDate.parse(dateFrom));
        authUserFilter().setDateTo(dateTo.equals("") ? LocalDate.now() : LocalDate.parse(dateTo));
        return service.getAllFiltered(authUserId(), authUserCaloriesPerDay(), authUserFilter());
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

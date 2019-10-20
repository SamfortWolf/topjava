package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static ru.javawebinar.topjava.MealTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})

@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {
    static {
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Test
    public void get() {
        Meal meal = service.get(MEAL_ID, USER_ID);
        assertMatch(meal, MEALS.get(0));
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound () {
        service.get(1,1);
    }

    @Test
    public void delete() {
        service.delete(MEAL_ID, USER_ID);
        assertMatch(service.getAll(USER_ID), MEALS);
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound (){
        service.delete(1,1);
    }

    @Test
    public void getBetweenDates() {
        List <Meal> meals = service.getBetweenDates(LocalDate
                .of(2015,05,30), LocalDate
                .of(2015,05,31), USER_ID);
        assertMatch(meals, MEALS);
    }

    @Test
    public void getAll() {
        List <Meal> meals = service.getAll(USER_ID);
        assertMatch(meals, MEALS);
    }

    @Test
    public void update() {
        Meal updated = new Meal(MEALS.get(0));
        updated.setDescription("updated");
        updated.setCalories(600);
        service.update(updated, USER_ID);
        assertMatch(service.get(MEALS.get(0).getId(), USER_ID), updated);
    }

    @Test(expected = DuplicateKeyException.class)
    public void updateSameDate() {
        Meal updated = new Meal(MEALS.get(0));
        updated.setDescription("updated");
        updated.setCalories(600);
        updated.setDateTime(MEALS.get(1).getDateTime());
        service.update(updated, USER_ID);
        assertMatch(service.get(MEALS.get(0).getId(), USER_ID), updated);
    }

    @Test
    public void create() {
        Meal newMeal = new Meal(LocalDateTime.of(1992,01,15,22,00), "testMeal", 500);
        Meal created = service.create(newMeal,USER_ID);
        newMeal.setId(created.getId());
        assertMatch(service.getAll(USER_ID), MEALS.get(0),MEALS.get(1),MEALS.get(2),MEALS.get(3),
                MEALS.get(4),MEALS.get(5),newMeal);
    }

    @Test (expected = DuplicateKeyException.class)
    public void createWithSameDate() {
        Meal newMeal = new Meal(MEALS.get(0).getDateTime(), "testMeal", 500);
        Meal created = service.create(newMeal, USER_ID);
        newMeal.setId(created.getId());
        assertMatch(service.getAll(USER_ID), MEALS.get(0),MEALS.get(1),MEALS.get(2),MEALS.get(3),
                MEALS.get(4),MEALS.get(5),newMeal);
    }
}
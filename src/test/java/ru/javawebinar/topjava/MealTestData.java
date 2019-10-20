package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;
import static org.assertj.core.api.Assertions.assertThat;

public class MealTestData {
    public static final int MEAL_ID = START_SEQ;
    public static final int USER_ID = START_SEQ;

    public static final List<Meal> MEALS = Arrays.asList(
            new Meal(100000,LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
            new Meal(100001,LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
            new Meal(100002,LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
            new Meal(100003,LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
            new Meal(100004,LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
            new Meal(100005,LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
    );

    public static void assertMatch (Meal actual, Meal expected){
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "id");
    }

    public static void assertMatch (Iterable <Meal> actual, Meal ... expected){
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch (Iterable <Meal> actual, Iterable<Meal>expected){
        assertThat(actual).usingDefaultElementComparator();
    }

}

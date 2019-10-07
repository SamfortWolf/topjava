package ru.javawebinar.topjava.model;

import ru.javawebinar.topjava.util.TimeUtil;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

public class MealTo {

    private AtomicInteger id;

    private static AtomicInteger count;

    private final LocalDateTime dateTime;

    private final String description;

    private final int calories;
//    private final Supplier<Boolean> excess;
//    private final AtomicBoolean excess;
    private final boolean excess;

    public MealTo(AtomicInteger id, LocalDateTime dateTime, String description, int calories, boolean excess) {
        this.id = id;
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.excess = excess;
        this.count = Meal.getCount();
    }

    public String getDateTime() {
        return TimeUtil.toStringDate(dateTime);
    }

    /*public LocalDateTime getDateTime() {
        return dateTime;
    }*/

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public boolean isExcess() {
        return excess;
    }

    public AtomicInteger getId() {
        return id;
    }

    public static AtomicInteger getCount() {
        return count;
    }
//    public Boolean getExcess() {
//        return excess.get();
//    }

    @Override
    public String toString() {
        return "MealTo{" +
                "id=" + id +
                "dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                ", excess=" + excess +
                '}';
    }
}
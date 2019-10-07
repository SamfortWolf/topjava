package ru.javawebinar.topjava.model;

import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.concurrent.atomic.AtomicInteger;

public class Meal {

    private static AtomicInteger count = new AtomicInteger(0);
    private static AtomicInteger idCount = new AtomicInteger(0);

    private AtomicInteger id;

    private final LocalDateTime dateTime;

    private final String description;

    private final int calories;

    public Meal(LocalDateTime dateTime, String description, int calories) {
        this.id = new AtomicInteger(idCount.incrementAndGet());
        count.incrementAndGet();
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }

    public static AtomicInteger getCount() {
        return count;
    }

    public static AtomicInteger getIdCount() {
        return idCount;
    }

    public AtomicInteger getId() {
        return id;
    }

    public void setId(AtomicInteger id) {
        this.id = id;
    }
}

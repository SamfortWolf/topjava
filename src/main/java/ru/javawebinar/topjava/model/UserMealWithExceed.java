package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;

public class UserMealWithExceed {
    private final LocalDateTime dateTime;

    private final String description;

    private final int calories;

    private final boolean exceed;

    public UserMealWithExceed(LocalDateTime dateTime, String description, int calories, boolean exceed) {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.exceed = exceed;
    }

    @Override
    public String toString() {
        return String.format("Date: %d.%02d.%d, Time: %02d:%02d, Description: %s, was eaten %d calories, %b",
                dateTime.getDayOfMonth(),dateTime.getMonthValue(),dateTime.getYear(),dateTime.getHour(), dateTime.getMinute(), description, calories, exceed);
    }
}

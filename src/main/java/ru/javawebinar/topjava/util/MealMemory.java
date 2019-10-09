package ru.javawebinar.topjava.util;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.getLogger;

public class MealMemory {
    private static final Logger log = getLogger(MealsUtil.class);
    private static ConcurrentSkipListMap<Integer, Meal> memoryMap = new ConcurrentSkipListMap<>
            (MealsUtil.getMeals().stream().collect(Collectors.toMap((Meal meal) -> meal.getId().get(), Function.identity())));

    public static ConcurrentSkipListMap<Integer, Meal> getMemoryMap() {
        return memoryMap;
    }

    public static void addMeal(Meal meal) {
        log.info("Before Meals.Util.addMeal, size is: " + memoryMap.size());
        memoryMap.put(meal.getId().get(), meal);
        log.info("After Meals.Util.addMeal, size is: " + memoryMap.size());
    }

    public static Meal getMeal(int id) {
        return memoryMap.get(id);
    }

    public static void setMeal(int id, LocalDateTime ldt, String description, int calories) {
        Meal m = memoryMap.get(id);
        if (m != null) {
            m.setDateTime(ldt);
            m.setDescription(description);
            m.setCalories(calories);
        }
    }

    public static void deleteMeal(int id) {
        memoryMap.remove(id);
    }

    public static int getSize() {
        return memoryMap.size();
    }

}

package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510),
                new UserMeal(LocalDateTime.of(2015, Month.JUNE, 30, 11, 0), "Завтрак", 500)
        );

        System.out.println("Result with 2 foreach cycles:");
        List<UserMealWithExceed> testListCycles = getFilteredWithExceededViaCycles(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        testListCycles.forEach(System.out::println);

        System.out.println("\nResult with STREAM API and 2 iterations:");
        List<UserMealWithExceed> testListStreamAPI = getFilteredWithExceededViaStreamAPI(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        testListStreamAPI.forEach(System.out::println);
    }

    public static List<UserMealWithExceed> getFilteredWithExceededViaCycles(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        List<UserMealWithExceed> newList = new ArrayList<>();
        Map<LocalDate, Integer> sumCalMap = new HashMap<>();
        for (UserMeal um : mealList) {
            sumCalMap.merge(um.getLocalDate(), um.getCalories(), Integer::sum);
        }
        for (UserMeal userMeal : mealList) {
            if (TimeUtil.isBetween(userMeal.getDateTime().toLocalTime(), startTime, endTime)) {
                newList.add(new UserMealWithExceed(userMeal.getDateTime(), userMeal.getDescription(), userMeal.getCalories(),
                        (sumCalMap.get(userMeal.getLocalDate())) > caloriesPerDay));
            }
        }
        return newList;
    }

    public static List<UserMealWithExceed> getFilteredWithExceededViaStreamAPI(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> sumCalMap = mealList.stream().
                collect(Collectors.toMap((UserMeal::getLocalDate), (UserMeal::getCalories), Integer::sum));
        return mealList.stream().
                filter(a -> TimeUtil.isBetween(a.getDateTime().toLocalTime(), startTime, endTime)).
                map(a -> new UserMealWithExceed(a.getDateTime(), a.getDescription(), a.getCalories(), (sumCalMap.get(a.getLocalDate()) > caloriesPerDay))).
                collect(Collectors.toList());
    }
}

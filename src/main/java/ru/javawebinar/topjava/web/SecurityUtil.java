package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.util.Filter;

import static ru.javawebinar.topjava.util.MealsUtil.DEFAULT_CALORIES_PER_DAY;

public class SecurityUtil {

    private static User authUser = new User(1, "TestUser", "email", "password", Role.ROLE_USER);

    private static int authUserId;

    public static int authUserId() {
        return authUserId;//authUser.getId();
    }

    public static void setAuthUserId(int authUserId) {
        SecurityUtil.authUserId = authUserId;
    }

    public static int authUserCaloriesPerDay() {
        return DEFAULT_CALORIES_PER_DAY;
    }


    public static Filter authUserFilter() {
        return authUser.getFilter();
    }
}
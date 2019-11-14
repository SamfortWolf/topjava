package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalDate;
import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalTime;

@Controller
public class JspMealController {
    private static final Logger log = getLogger(JspMealController.class);
    @Autowired
    MealService service;

    @GetMapping("/meals")
    public String getAllFiltered (HttpServletRequest request, Model model){
        if (request.getQueryString()==null){
            model.addAttribute("meals", MealsUtil.getTos(service.getAll(SecurityUtil.authUserId()), SecurityUtil.authUserCaloriesPerDay()));
        }
        else {
            LocalDate startDate = parseLocalDate(request.getParameter("startDate"));
            LocalDate endDate = parseLocalDate(request.getParameter("endDate"));
            LocalTime startTime = parseLocalTime(request.getParameter("startTime"));
            LocalTime endTime = parseLocalTime(request.getParameter("endTime"));
            List<Meal> mealsDateFiltered = service.getBetweenDates(startDate, endDate, SecurityUtil.authUserId());
            model.addAttribute("meals", MealsUtil.getFilteredTos(mealsDateFiltered, SecurityUtil.authUserCaloriesPerDay(), startTime, endTime));
        }
        return "meals";
    }

    @GetMapping("/meals/delete")
    public String deleteById (HttpServletRequest request, Model model){
        int itemId = Integer.parseInt(Objects.requireNonNull(request.getParameter("id")));
        log.info("deleting item with id: "+itemId+" and userId is: "+SecurityUtil.authUserId());
        service.delete(itemId, SecurityUtil.authUserId());
        model.addAttribute("meals", MealsUtil.getTos(service.getAll(SecurityUtil.authUserId()), SecurityUtil.authUserCaloriesPerDay()));
        return "redirect:/meals";
    }

    @GetMapping("/mealForm")
    public String createForm (HttpServletRequest request, Model model){
        String returnString;
        Meal newInstance;
        if (request.getQueryString()==null){
            newInstance = new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000);
            returnString = "mealForm";
        }
        else if (StringUtils.isEmpty(request.getParameter("id")) && request.getQueryString().contains("dateTime") && request.getQueryString().contains("description") && request.getQueryString().contains("calories")) {
            newInstance = new Meal(LocalDateTime.parse(request.getParameter("dateTime")), request.getParameter("description"), Integer.parseInt(request.getParameter("calories")));
            service.create(newInstance, SecurityUtil.authUserId());
            returnString = "redirect:/meals";
        }
        else if (request.getQueryString().contains("id") && request.getQueryString().contains("dateTime") && request.getQueryString().contains("description") && request.getQueryString().contains("calories")){
            returnString="redirect:/meals";
            newInstance = service.get(Integer.parseInt(request.getParameter("id")), SecurityUtil.authUserId());
            newInstance.setDateTime(LocalDateTime.parse(request.getParameter("dateTime")));
            newInstance.setDescription(request.getParameter("description"));
            newInstance.setCalories(Integer.parseInt(request.getParameter("calories")));
            service.update(newInstance, SecurityUtil.authUserId());
        }
        else {
            newInstance = service.get(Integer.parseInt(request.getParameter("id")), SecurityUtil.authUserId());
            returnString = "mealForm";
        }
        model.addAttribute("meal", newInstance);
        return returnString;
    }


}

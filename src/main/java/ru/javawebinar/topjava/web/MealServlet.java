package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.DAO.MealsDAO;
import ru.javawebinar.topjava.DAO.MealsDAOImpl;
import ru.javawebinar.topjava.model.Meal;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private MealsDAO dao;
    private static final String ADD_OR_EDIT = "/create.jsp";
    private static final String LIST_MEALS = "/meals.jsp";

    public MealServlet() {
        super();
        this.dao = new MealsDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String forward;
        String action = req.getParameter("action");
        if (action == null) {
            forward = LIST_MEALS;
            req.setAttribute("meals", dao.getAllMeals());
            req.setAttribute("count", Meal.getCount());
            log.info("redirect to meals.jsp (action==null)");
        } else if (action.equalsIgnoreCase("delete")) {
            int mealId = Integer.parseInt(req.getParameter("mealId"));
            dao.deleteMealById(mealId);
            forward = LIST_MEALS;
            req.setAttribute("meals", dao.getAllMeals());
            req.setAttribute("count", Meal.getCount());
        } else if (action.equalsIgnoreCase("edit")) {
            forward = ADD_OR_EDIT;
            int mealId = Integer.parseInt(req.getParameter("mealId"));
            log.info("Edit mealId is " + mealId);
            Meal meal = dao.getMealById(mealId);
            req.setAttribute("meal", meal);
        } else if (action.equalsIgnoreCase("meals")) {
            forward = LIST_MEALS;
            req.setAttribute("meals", dao.getAllMeals());
            req.setAttribute("count", Meal.getCount());
            log.info("redirect to meals.jsp");
        } else {
            forward = ADD_OR_EDIT;
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher(forward);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        log.info("doPost working");
        String mealId = req.getParameter("id");
        if (mealId == null || mealId.isEmpty()) {
            Meal meal = new Meal(LocalDateTime.parse(req.getParameter("date")), req.getParameter("description"), Integer.parseInt(req.getParameter("calories")));
            dao.addMeal(meal);
            log.info("Servlet - add new Meal to dao");
        } else {
            dao.updateMeal(Integer.parseInt(mealId), LocalDateTime.parse(req.getParameter("date")), req.getParameter("description"), Integer.parseInt(req.getParameter("calories")));
            log.info("Servlet - update Meal to dao");
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher(LIST_MEALS);
        req.setAttribute("meals", dao.getAllMeals());
        req.setAttribute("count", Meal.getCount());
        dispatcher.forward(req, resp);
        log.info("redirect to meals.jsp drom doPost");
    }
}
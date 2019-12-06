package ru.javawebinar.topjava.web.meal;

import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealTo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/ajax/profile/meals")
public class MealUIController extends AbstractMealController {

    @Override
    @GetMapping
    public List<MealTo> getAll (){
        return super.getAll();
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete (@PathVariable int id){
        super.delete(id);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void createOrUpdate (@RequestParam Integer id,
                                @RequestParam LocalDateTime dateTime,
                                @RequestParam String description,
                                @RequestParam Integer calories) {
        Meal m = new Meal(id, dateTime,description, calories);
        if (m.isNew()){
            super.create(m);
        }
    }

    @Override
    @GetMapping("/filter")
    public List<MealTo> getBetween (@RequestParam @Nullable LocalDate startDate,
                                    @RequestParam @Nullable LocalTime startTime,
                                    @RequestParam @Nullable LocalDate endDate,
                                    @RequestParam @Nullable LocalTime endTime) {
        return super.getBetween(startDate, startTime, endDate, endTime);
    }
}

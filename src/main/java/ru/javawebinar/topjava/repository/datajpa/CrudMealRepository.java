package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudMealRepository extends JpaRepository<Meal, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Meal m WHERE m.id=:id AND m.user.id=:user_id")
    int delete (@Param("id") int id,
                @Param("user_id") int user_id);

    @Query ("SELECT m FROM Meal m WHERE m.id=:id AND m.user.id=:user_id")
    Meal getByIdAndUserId (@Param("id") int id,
                           @Param("user_id") int user_id);

    @Query("SELECT m FROM Meal m " +
            "WHERE m.user.id=:user_id AND m.dateTime >= :startDate " +
            "AND m.dateTime < :endDate ORDER BY m.dateTime DESC")
    List<Meal> getBetweenInclusive (@Param("startDate") LocalDateTime startDate,
                                    @Param("endDate") LocalDateTime endDate,
                                    @Param("user_id") int userId);

    /*@Query("SELECT m FROM Meal m WHERE m.user.id=:userId ORDER BY m.dateTime DESC")
    List<Meal> findAllByUserId (@Param("userId") int userId);*/
    List<Meal> findAllByUserIdOrderByDateTimeDesc(Integer userId);



}

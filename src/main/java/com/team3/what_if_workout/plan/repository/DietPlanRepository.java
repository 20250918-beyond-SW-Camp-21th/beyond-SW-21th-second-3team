package com.team3.what_if_workout.plan.repository;

import com.team3.what_if_workout.plan.domain.DietPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
// JPA Repository 옆에있는건 엔티티, 옆에는   pk의 wrapper class
public interface DietPlanRepository extends JpaRepository<DietPlan, Long> {


    List<DietPlan> findByUserIdAndDate(Long userId, LocalDate date);
} // c, r 만 들어있어도ㅇㅇ JpaRepository에 있으니까~~


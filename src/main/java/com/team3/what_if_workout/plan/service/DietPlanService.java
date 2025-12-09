package com.team3.what_if_workout.plan.service;

import com.team3.what_if_workout.plan.domain.DietPlan;
import com.team3.what_if_workout.plan.dto.request.DietPlanRequest;
import com.team3.what_if_workout.plan.repository.DietPlanRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DietPlanService {

    private final DietPlanRepository dietPlanRepository;

    @Transactional
    public void saveDietPlan(Long userId, DietPlanRequest dietPlan) {
        DietPlan dietPlanEntity = dietPlan.toEntity(userId);
        dietPlanRepository.save(dietPlanEntity);
    }


    public List<DietPlan> findTodayDietPlan(Long userId, LocalDate date) {
        return dietPlanRepository.findByUserIdAndDate(userId, date);
    }  //useId, date And,
    //식단 option -> null값을 처리랄 할 수 있음. 있으면넣어주고 없으면 에러
    //null throw (경민님꺼 참고하기)

    @Transactional
    public void updateDietPlan(Long dietPlanId, DietPlanRequest dietPlan) {

        DietPlan origin = dietPlanRepository.findById(dietPlanId)
                .orElseThrow(() -> new IllegalArgumentException("해당 식단이 존재하지 않습니다."));

        origin.update(
                dietPlan.mealTime(),
                dietPlan.foodId(),
                dietPlan.foodName(),
                dietPlan.calories(),
                dietPlan.date()
        );
    }

    @Transactional
    public void deleteDietPlan(Long dietPlanId) {
        dietPlanRepository.deleteById(dietPlanId);
    }
}

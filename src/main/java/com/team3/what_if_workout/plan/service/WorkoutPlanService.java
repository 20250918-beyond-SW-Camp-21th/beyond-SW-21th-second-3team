package com.team3.what_if_workout.plan.service;

import com.team3.what_if_workout.plan.domain.WorkoutPlan;
import com.team3.what_if_workout.plan.dto.request.WorkoutPlanRequest;
import com.team3.what_if_workout.plan.repository.WorkoutPlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WorkoutPlanService {

    private final WorkoutPlanRepository workoutPlanRepository;

    @Transactional //등록
    public void saveWorkoutPlan(Long userId, WorkoutPlanRequest request) {
        WorkoutPlan entity = request.toEntity(userId);
        workoutPlanRepository.save(entity);
    }

    //조회
    public List<WorkoutPlan> findWorkoutPlans(Long userId) {
        return workoutPlanRepository.findByUserId(userId);
    }

    @Transactional //수정
    public void updateWorkoutPlan(Long workoutPlanId, WorkoutPlanRequest request) {

        WorkoutPlan origin = workoutPlanRepository.findById(workoutPlanId)
                .orElseThrow(() -> new IllegalArgumentException("해당 운동 계획이 존재하지 않습니다."));

        origin.update(
                request.dayOfWeek(),
                request.exerciseType(),
                request.category(),
                request.difficulty(),
                request.bodyPart(),
                request.date()

        );
    }

    @Transactional
    public void deleteWorkoutPlan(Long workoutPlanId) {
        workoutPlanRepository.deleteById(workoutPlanId);
    }
}

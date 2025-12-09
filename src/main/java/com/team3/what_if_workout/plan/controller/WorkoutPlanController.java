package com.team3.what_if_workout.plan.controller;

import com.team3.what_if_workout.plan.dto.request.WorkoutPlanRequest;
import com.team3.what_if_workout.plan.service.WorkoutPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/workout")
@RequiredArgsConstructor
public class WorkoutPlanController {

    private final WorkoutPlanService workoutPlanService;


    @PostMapping
    public ResponseEntity<?> createWorkoutPlan(
            @RequestParam Long userId,
            @RequestBody WorkoutPlanRequest request
    ) {
        workoutPlanService.saveWorkoutPlan(userId, request);
        return ResponseEntity.ok("운동 계획 등록을 완료했습니다.");
    } //GET /workout-plans/daily?userId=1&date=2025-12-09 (호출 - 하루치 조회)


    // 예시: /workout?userId=1
    @GetMapping
    public ResponseEntity<?> findWorkoutPlans(

    ) {
        Long userId = 1L;
        return ResponseEntity.ok(
                workoutPlanService.findWorkoutPlans(userId)
        );
    }


    @PatchMapping
    public ResponseEntity<?> updateWorkoutPlan(
            @RequestParam Long workoutPlanId,
            @RequestBody WorkoutPlanRequest request
    ) {
        workoutPlanService.updateWorkoutPlan(workoutPlanId, request);
        return ResponseEntity.ok("운동 계획 수정을 완료했습니다.");
    }

    @DeleteMapping
    public ResponseEntity<?> deleteWorkoutPlan(
            @RequestParam Long workoutPlanId
    ) {
        workoutPlanService.deleteWorkoutPlan(workoutPlanId);
        return ResponseEntity.ok("운동 계획 삭제를 완료했습니다.");
    }
}

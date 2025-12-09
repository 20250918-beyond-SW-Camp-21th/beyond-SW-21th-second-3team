package com.team3.what_if_workout.plan.controller;

import com.team3.what_if_workout.plan.dto.request.DietPlanRequest;
import com.team3.what_if_workout.plan.service.DietPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/diet")
@RequiredArgsConstructor
public class DietPlanController {

    private final DietPlanService dietPlanService;


    @PostMapping("/")
    public ResponseEntity<?> createDietPlan(
            @RequestParam Long userId,
            @RequestBody DietPlanRequest dietPlan) {
        dietPlanService.saveDietPlan(userId, dietPlan);
        return ResponseEntity.ok("식단 등록을 완료했습니다");
    }


    // 예시: /diet?userId=1&date=2025-12-08
    @GetMapping
    public ResponseEntity<?> findTodayDietPlan(
            @RequestParam
            @org.springframework.format.annotation.DateTimeFormat(
                    iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE
            )
            LocalDate date
            )
    {
        Long userId = 1L;
        return ResponseEntity.ok(
                dietPlanService.findTodayDietPlan(userId, date)
        );
    }

    @PatchMapping
    public ResponseEntity<?> updateDietPlan(
            @RequestParam Long dietPlanId,
            @RequestBody DietPlanRequest dietPlanRequest
    ) {
        dietPlanService.updateDietPlan(dietPlanId, dietPlanRequest);
        return ResponseEntity.ok("식단 수정을 완료했습니다.");
    }


    @DeleteMapping
    public ResponseEntity<?> deleteDietPlan(
            @RequestParam Long dietPlanId
    ) {
        dietPlanService.deleteDietPlan(dietPlanId);
        return ResponseEntity.ok("식단 삭제를 완료했습니다");
    }
}

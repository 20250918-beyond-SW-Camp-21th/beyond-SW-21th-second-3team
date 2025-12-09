package com.team3.what_if_workout.plan.dto.request;

import com.team3.what_if_workout.plan.domain.WorkoutPlan;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record WorkoutPlanRequest(

        @NotBlank(message="주 차를 입력해 주세요")
        String dayOfWeek,

        @NotBlank(message="운동 종류를 선택해주세요")
        String exerciseType,

        @NotBlank(message="운동 카테고리를 선택해주세요")
        String category,

        @NotBlank(message="운동 난이도를 선택해주세요")
        String difficulty,

        @NotBlank(message="어떤 부위를 운동하시나요?")
        String bodyPart,

        @NotBlank(message="날짜를 입력해주세요")
        LocalDate date

) {
    public WorkoutPlan toEntity(Long userId) {
        return WorkoutPlan.builder()
                .userId(userId)
                .dayOfWeek(dayOfWeek)
                .exerciseType(exerciseType)
                .category(category)
                .difficulty(difficulty)
                .bodyPart(bodyPart)
                .date(date)
                .build();
    }
}


/*@NotBlank(message = "사진파일타입을 선택해주세요")*/
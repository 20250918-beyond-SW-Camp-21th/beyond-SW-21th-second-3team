package com.team3.what_if_workout.plan.dto.response;

import com.team3.what_if_workout.plan.domain.WorkoutPlan;

import java.time.LocalDate;

public record WorkoutPlanResponse(
        Long workoutPlanId,
        String dayOfWeek,
        String exerciseType,
        String category,
        String difficulty,
        String bodyPart,
        LocalDate date
) {

/*    public static WorkoutPlanResponse from(WorkoutPlan workoutPlan) {
        return new WorkoutPlanResponse(
                workoutPlan.getWorkoutplanId(),
                workoutPlan.getDayOfWeek(),
                workoutPlan.getExerciseType(),
                workoutPlan.getCategory(),
                workoutPlan.getDifficulty(),
                workoutPlan.getBodyPart(),
                workoutPlan.getDate()
        );
    }*/
}

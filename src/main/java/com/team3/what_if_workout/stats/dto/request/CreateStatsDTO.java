package com.team3.what_if_workout.stats.dto.request;

public record CreateStatsDTO(

        Long userId,
        Integer totalDuration,
        Integer totalCalories
) {}

package com.team3.what_if_workout.stats.dto.request;

public record CreateRankingDTO(

        Long userId,
        Integer totalDuration,
        Integer totalCalories
) {}

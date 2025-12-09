package com.team3.what_if_workout.stats.dto.respose;

public record CalorieRankingDto(

        Long userId,
        int calorieRank,
        Integer totalCalories
) {}

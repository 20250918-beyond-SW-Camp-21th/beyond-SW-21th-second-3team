package com.team3.what_if_workout.stats.dto.respose;

public record TimeRankingDto(

        Long userId,
        int timeRank,
        Integer totalDuration
) {}

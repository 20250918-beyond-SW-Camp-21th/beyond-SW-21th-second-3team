package com.team3.what_if_workout.report.dto.request;

public record CreateReportDTO(

        Long userId,
        Integer plannedAmount,
        Integer achievedAmount
) {}
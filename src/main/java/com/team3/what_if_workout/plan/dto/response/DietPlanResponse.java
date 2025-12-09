package com.team3.what_if_workout.plan.dto.response;

public record DietPlanResponse (
    Long dietPlanId,
    String mealTime,
    Long foodId,
    Integer calories,
    String foodName

) {} //스프링 시큐리티 쓰는거니까... userId를 안 넣는게 맞겠죠? cont
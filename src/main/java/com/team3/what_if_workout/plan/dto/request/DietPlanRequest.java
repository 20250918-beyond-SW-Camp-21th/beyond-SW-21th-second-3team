package com.team3.what_if_workout.plan.dto.request;


import com.team3.what_if_workout.plan.domain.DietPlan;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;
import java.time.temporal.WeekFields;

public record DietPlanRequest(

        /*@NotBlank(message = "음식사진을 업로드해주세요")
        String fileUrl,*/

        /*@NotNull(message = "사진파일타입을 선택해주세요")
        FileType fileType,*/ //String file 미나이오로 들어오니까 file여기에 xx

        /*@NotBlank(message = "사진파일타입을 선택해주세요")
        String fileType,*/

       /* @NotNull(message = "음식을 입력해주세요")
        Long foodId,
*/
        /*@NotNull(message = "음식종류를 선택해주세요")
        FoodName foodName,*/

        //날짜 넣기

        @NotBlank(message = "음식 종류를 선택해주세요.")
        String foodName,

        @NotNull(message = "음식 칼로리를 입력해주세요")
        @Positive(message = "칼로리는 0보다 큰 값만 가능합니다.")
        @Min(value = 1, message = "칼로리는 0일 순 없습니다")
        Integer calories,

        /* @NotNull(message = "식사시간을 선택해주세요")
        MealTime mealTime*/

        @NotNull(message = "음식 ID를 입력해주세요")
        Long foodId,

        @NotBlank(message = "식사시간을 선택해주세요")
        String mealTime,

        @NotBlank(message= "날짜를 입력해주세요")
        LocalDate date


) {
    public DietPlan toEntity(Long userId) {
        return DietPlan.builder()
                .userId(userId)
                .mealTime(mealTime)
                .foodId(foodId)
                .foodName(foodName)
                .calories(calories)
                .date(date)
                .build();

    }
}





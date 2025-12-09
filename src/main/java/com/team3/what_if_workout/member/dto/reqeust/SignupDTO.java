package com.team3.what_if_workout.member.dto.reqeust;

import com.team3.what_if_workout.member.domain.Member;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

public record SignupDTO(

        @NotBlank(message = "아이디는 필수사항입니다.")
        String id,

        @NotBlank(message = "비밀번호는 필수사항입니다.")
        String pwd,

        @NotBlank(message = "목표 칼로리는 필수사항입니다.")
        @Size(max=100000, message = "일주일 목표 칼로리가 10만 이상일 수는 없습니다.")
        Integer goalsCalories
) {

    public Member toEntity(){
        return Member.builder()
                .id(id)
                .password(pwd)
                .goalsCalories(goalsCalories)
                .build();
    }
}

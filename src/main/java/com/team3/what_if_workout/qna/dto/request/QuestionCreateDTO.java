package com.team3.what_if_workout.qna.dto.request;

import com.team3.what_if_workout.qna.domain.Question;
import jakarta.validation.constraints.NotBlank;

public record QuestionCreateDTO(
    @NotBlank(message = "질문 제목은 필수사항입니다.")
    String questionTitle,
    @NotBlank(message = "질문 내용은 비워둘 수 없습니다. ")
    String questionContent
){
    public Question toEntity(){
        return Question.builder()
                .questionTitle(questionTitle)
                .questionContent(questionContent)
                .build();
    }


}

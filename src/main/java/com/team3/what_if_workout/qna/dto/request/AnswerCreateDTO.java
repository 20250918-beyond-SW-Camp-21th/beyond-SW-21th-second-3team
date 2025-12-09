package com.team3.what_if_workout.qna.dto.request;

import com.team3.what_if_workout.qna.domain.Answer;
import jakarta.validation.constraints.NotBlank;

public record AnswerCreateDTO(
        @NotBlank(message = "답변 내용을 비워둘 수 없습니다.")
        String answerContent
        ) {
    public Answer toEntity() {
        return Answer.builder()
                .answerContent(answerContent)
                .build();
    }

}

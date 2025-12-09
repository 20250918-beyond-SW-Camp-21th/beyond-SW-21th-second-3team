package com.team3.what_if_workout.qna.dto.response;

import com.team3.what_if_workout.qna.domain.Answer;

public record AnswerResponseDTO(
        Long answerId,
        Long userId,
        Long questionId,
        String answerContent
) {
    public static AnswerResponseDTO fromEntity(Answer answer) {
        return  new AnswerResponseDTO(
                answer.getAnswerId(),
                answer.getUserId(),
                answer.getQuestionId(),
                answer.getAnswerContent()
        );
    }
}
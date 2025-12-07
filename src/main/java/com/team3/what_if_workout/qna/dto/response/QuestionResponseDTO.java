package com.team3.what_if_workout.qna.dto.response;

import com.team3.what_if_workout.qna.domain.Question;

public record QuestionResponseDTO(
        Long questionId,
        Long userId,
        String questionTitle,
        String questionContent
) {
    public static QuestionResponseDTO fromEntity(Question question) {
        return new QuestionResponseDTO(
                question.getQuestionId(),
                question.getUserId(),
                question.getQuestionTitle(),
                question.getQuestionContent()
        );
    }
}

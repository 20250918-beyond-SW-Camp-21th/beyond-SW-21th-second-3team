package com.team3.what_if_workout.qna.service;

import com.team3.what_if_workout.qna.domain.Answer;
import com.team3.what_if_workout.qna.dto.request.AnswerCreateDTO;
import com.team3.what_if_workout.qna.dto.response.AnswerResponseDTO;
import com.team3.what_if_workout.qna.repository.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;

    @Transactional
    public AnswerResponseDTO createAnswer(AnswerCreateDTO dto, Long userId, Long questionId) {
        Answer answer = dto.toEntity();
        answer.assignUserId(userId);
        answer.assignQuestionId(questionId);

        Answer saveAnswer = answerRepository.save(answer);
        return AnswerResponseDTO.fromEntity(saveAnswer);
    }

}

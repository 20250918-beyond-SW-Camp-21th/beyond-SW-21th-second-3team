package com.team3.what_if_workout.qna.service;

import com.team3.what_if_workout.qna.domain.Question;
import com.team3.what_if_workout.qna.dto.request.QuestionCreateDTO;
import com.team3.what_if_workout.qna.dto.response.QuestionResponseDTO;
import com.team3.what_if_workout.qna.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    @Transactional
public QuestionResponseDTO createQuestion(QuestionCreateDTO dto, Long userId) {
        Question question = dto.toEntity();
        question.assignUserId(userId);
        Question saveQuestion = questionRepository.save(question);
        return QuestionResponseDTO.fromEntity(saveQuestion);
    }

}

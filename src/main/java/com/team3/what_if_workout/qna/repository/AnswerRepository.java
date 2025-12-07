package com.team3.what_if_workout.qna.repository;

import com.team3.what_if_workout.qna.domain.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}

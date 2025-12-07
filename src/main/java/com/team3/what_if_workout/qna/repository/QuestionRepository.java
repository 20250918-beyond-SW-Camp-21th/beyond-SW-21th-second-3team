package com.team3.what_if_workout.qna.repository;

import com.team3.what_if_workout.qna.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question,Long> {
}

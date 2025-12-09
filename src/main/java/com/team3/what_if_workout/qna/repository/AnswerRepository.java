package com.team3.what_if_workout.qna.repository;

import com.team3.what_if_workout.qna.domain.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

 //jpa 옆에 있는건 엔티티, 그옆에는 pk의 wrapper class
@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findByAnswerContentContaining(String answerContent);
    List<Answer> findByQuestionId(Long questionId);

 }

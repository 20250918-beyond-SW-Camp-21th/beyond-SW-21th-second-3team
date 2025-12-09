package com.team3.what_if_workout.qna.repository;

import com.team3.what_if_workout.qna.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Long> {
    List<Question> findByQuestionTitleContainingIgnoreCaseOrQuestionContentContainingIgnoreCase(
            String titleKeyword, String contentKeyword);

    List<Question> findQuestionsByUserId(Long userId);


}

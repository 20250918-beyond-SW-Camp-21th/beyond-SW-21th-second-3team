package com.team3.what_if_workout.qna.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerId;

    @Column(nullable = false)
    private String answerContent;

    @Column(nullable = false)
    private Long userId; // Member.userId 참조 (FK)

    @Column(nullable = false)
    private Long questionId; // Question.questionId 참조 (FK)

    public void assignUserId(Long userId) {
        this.userId = userId;
    }

    public void assignQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    @Builder
    public Answer(String answerContent){
        this.answerContent = answerContent;
    }


}

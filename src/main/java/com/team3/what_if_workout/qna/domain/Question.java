package com.team3.what_if_workout.qna.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    @Column(nullable = false)
    private String questionTitle;

    @Column(nullable = false)
    private String questionContent;

    @Column(nullable = false)
    private Long userId; // Member.userId 참조 (FK)

    public void assignUserId(Long userId) {
        this.userId = userId;
    }
    @Builder
    public Question(String questionTitle, String questionContent) {
        this.questionTitle = questionTitle;
        this.questionContent = questionContent;
    }
}

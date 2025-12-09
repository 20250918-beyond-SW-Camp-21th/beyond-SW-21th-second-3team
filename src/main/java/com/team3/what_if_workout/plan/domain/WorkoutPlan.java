package com.team3.what_if_workout.plan.domain;

import com.team3.what_if_workout.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "workout_plan")
public class WorkoutPlan extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long workoutplanId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String dayOfWeek;

    @Column(nullable = false)
    private String exerciseType;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String difficulty;

    @Column(nullable = false)
    private String bodyPart; //userId를 제외한 값은 enum으로 받고, service 후에 enum class로 수정하기.

    @Column(nullable = false)
    private LocalDate date;

    @Builder
    public WorkoutPlan(Long userId, String dayOfWeek, String exerciseType, String category, String difficulty, String bodyPart, LocalDate date) {
        this.userId = userId;
        this.dayOfWeek = dayOfWeek;
        this.exerciseType = exerciseType;
        this.category = category;
        this.difficulty = difficulty;
        this.bodyPart = bodyPart;
        this.date = date;
    }

    public void update(
            String dayOfWeek,
            String exerciseType,
            String category,
            String difficulty,
            String bodyPart,
            LocalDate date
    ) {
        this.dayOfWeek = dayOfWeek;
        this.exerciseType = exerciseType;
        this.category = category;
        this.difficulty = difficulty;
        this.bodyPart = bodyPart;
        this.date = date;

    }


    public void assignworkoutId(Long workoutId) {
        this.workoutplanId = workoutId;
    }
}




package com.team3.what_if_workout.stats.domain;

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
@Table( // 유저별 + 주차별 1개
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_stats_user_week",
                        columnNames = {"userId", "startDate"}
                )
        }
)
public class Stats extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long statsId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    private Integer totalDuration;

    private Integer totalCalories;

    @Builder
    public Stats(Long userId, LocalDate startDate, LocalDate endDate, Integer totalDuration, Integer totalCalories) {
        this.userId = userId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalDuration = totalDuration;
        this.totalCalories = totalCalories;
    }
}

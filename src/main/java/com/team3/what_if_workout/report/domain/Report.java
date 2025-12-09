package com.team3.what_if_workout.report.domain;

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
                        name = "uk_report_user_week",
                        columnNames = {"userId", "startDate"}
                )
        }
)
public class Report extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reportId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    private Integer plannedAmount;

    private Integer achievedAmount;

    private Integer resultValue;

    @Builder
    public Report(Long userId, LocalDate startDate, LocalDate endDate, Integer plannedAmount, Integer achievedAmount, Integer resultValue) {
        this.userId = userId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.plannedAmount = plannedAmount;
        this.achievedAmount = achievedAmount;
        this.resultValue = resultValue;
    }
}

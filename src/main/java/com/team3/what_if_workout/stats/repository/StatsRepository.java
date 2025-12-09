package com.team3.what_if_workout.stats.repository;

import com.team3.what_if_workout.stats.domain.Stats;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface StatsRepository extends JpaRepository<Stats, Long> {

    List<Stats> findByUserId(Long userId);

    List<Stats> findByStartDateOrderByTotalDurationDesc(LocalDate startDate);

    List<Stats> findByStartDateOrderByTotalCaloriesDesc(LocalDate startDate);

    List<Stats> findByStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByTotalDurationDesc(LocalDate startDate, LocalDate endDate);

    List<Stats> findByStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByTotalCaloriesDesc(LocalDate startDate, LocalDate endDate);

    boolean existsByUserIdAndStartDate(Long userId, LocalDate startDate);

    // 테스트용
    Optional<Stats> findTopByUserIdOrderByStartDateDesc(Long userId);
}

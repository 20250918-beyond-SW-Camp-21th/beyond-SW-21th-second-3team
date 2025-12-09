package com.team3.what_if_workout.stats.service;

import com.team3.what_if_workout.stats.domain.Stats;
import com.team3.what_if_workout.stats.dto.respose.CalorieRankingDto;
import com.team3.what_if_workout.stats.dto.respose.TimeRankingDto;
import com.team3.what_if_workout.stats.repository.StatsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatsService {

    private final StatsRepository statsRepository;

    @Transactional
    public void createLastWeekStats(Long userId, Integer totalDuration, Integer totalCalories) {
        LocalDate lastWeekStart = getLastWeekStart();
        LocalDate lastWeekEnd = getLastWeekEnd();

        // 이미 지난 주 랭킹이 있는지 확인
        if (statsRepository.existsByUserIdAndStartDate(userId, lastWeekStart)) {
            throw new IllegalStateException("이미 지난 주 통계가 존재합니다.");
        }

        Stats stats = Stats.builder()
                .userId(userId)
                .startDate(lastWeekStart)
                .endDate(lastWeekEnd)
                .totalDuration(totalDuration)
                .totalCalories(totalCalories)
                .build();

        statsRepository.save(stats);
    }

    public List<Stats> getStatsByUserId(Long userId) {
        return statsRepository.findByUserId(userId);
    }

    public List<TimeRankingDto> getLastWeekTimeRanking() {
        LocalDate lastWeekStart = getLastWeekStart();
        List<Stats> statsList = statsRepository.findByStartDateOrderByTotalDurationDesc(lastWeekStart);

        return buildTimeRanking(statsList);
    }

    public List<CalorieRankingDto> getLastWeekCalorieRanking() {
        LocalDate lastWeekStart = getLastWeekStart();
        List<Stats> statsList = statsRepository.findByStartDateOrderByTotalCaloriesDesc(lastWeekStart);

        return buildCalorieRanking(statsList);
    }

    public List<TimeRankingDto> getTimeRankingByDate(LocalDate date) {
        List<Stats> statsList = statsRepository.findByStartDateLessThanEqualAndEndDateGreaterThanEqual(date, date);

        return buildTimeRanking(statsList);
    }

    public List<CalorieRankingDto> getCalorieRankingByDate(LocalDate date) {
        List<Stats> statsList = statsRepository.findByStartDateLessThanEqualAndEndDateGreaterThanEqual(date, date);

        return buildCalorieRanking(statsList);
    }

    private LocalDate getLastWeekStart() { // 월요일 시작, 일요일 끝
        LocalDate today = LocalDate.now();
        LocalDate thisWeekMonday = today.with(DayOfWeek.MONDAY);
        return thisWeekMonday.minusWeeks(1);
    }

    private LocalDate getLastWeekEnd() {
        return getLastWeekStart().plusDays(6);
    }

    private List<TimeRankingDto> buildTimeRanking(List<Stats> statsList) {
        List<TimeRankingDto> result = new ArrayList<>();

        int index = 0;
        int currentRank = 0;
        Integer prevValue = null;

        for (Stats stats : statsList) {
            index++;

            Integer value = stats.getTotalDuration();
            if (value == null) value = 0;

            if (prevValue == null || !prevValue.equals(value)) {
                currentRank = index;
                prevValue = value;
            }

            result.add(new TimeRankingDto(
                    stats.getUserId(),
                    currentRank,
                    value
            ));
        }

        return result;
    }

    private List<CalorieRankingDto> buildCalorieRanking(List<Stats> statsList) {
        List<CalorieRankingDto> result = new ArrayList<>();

        int index = 0;
        int currentRank = 0;
        Integer prevValue = null;

        for (Stats stats : statsList) {
            index++;

            Integer value = stats.getTotalCalories();
            if (value == null) value = 0;

            if (prevValue == null || !prevValue.equals(value)) {
                currentRank = index;
                prevValue = value;
            }

            result.add(new CalorieRankingDto(
                    stats.getUserId(),
                    currentRank,
                    value
            ));
        }

        return result;
    }
}

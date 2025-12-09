package com.team3.what_if_workout.stats.service;

import com.team3.what_if_workout.stats.domain.Stats;
import com.team3.what_if_workout.stats.dto.respose.CalorieRankingDto;
import com.team3.what_if_workout.stats.dto.respose.TimeRankingDto;
import com.team3.what_if_workout.stats.repository.StatsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class StatsServiceTest {

    @Autowired
    private StatsService statsService;

    @Autowired
    private StatsRepository statsRepository;

    @BeforeEach
    void setUp() {
        statsRepository.deleteAll();
    }

    @DisplayName("지난 주 통계 생성")
    @Test
    void createLastWeekStats() {
        // given
        Long userId = 1L;
        Integer totalDuration = 60;
        Integer totalCalories = 500;

        // when
        statsService.createLastWeekStats(userId, totalDuration, totalCalories);

        // then
        Stats stats = statsRepository.findTopByUserIdOrderByStartDateDesc(userId)
                .orElseThrow(() -> new AssertionError("통계가 저장되지 않았습니다."));

        assertEquals(userId, stats.getUserId());
        assertEquals(totalDuration, stats.getTotalDuration());
        assertEquals(totalCalories, stats.getTotalCalories());
        assertNotNull(stats.getStartDate());
        assertNotNull(stats.getEndDate());
    }

    @DisplayName("사용자별 전체 통계 조회")
    @Test
    void getStatsByUserId() {
        // given
        Long userId = 1L;
        statsService.createLastWeekStats(userId, 30, 300);

        // when
        List<Stats> stats = statsService.getStatsByUserId(userId);

        // then
        assertFalse(stats.isEmpty(), "해당 아이디의 통계가 존재하지 않습니다.");
        assertEquals(userId, stats.get(0).getUserId());
    }

    @DisplayName("지난 주 운동량 랭킹 조회 테스트")
    @Test
    void getLastWeekTimeRanking() {
        // given
        Long user1 = 1L;
        Long user2 = 2L;
        Long user3 = 3L;

        statsService.createLastWeekStats(user1, 30, 300);
        statsService.createLastWeekStats(user2, 60, 500);
        statsService.createLastWeekStats(user3, 45, 400);

        // when
        List<TimeRankingDto> ranking = statsService.getLastWeekTimeRanking();

        // then
        assertEquals(3, ranking.size());

        assertEquals(user2, ranking.get(0).userId());
        assertEquals(1, ranking.get(0).timeRank());
        assertEquals(60, ranking.get(0).totalDuration());

        assertEquals(user3, ranking.get(1).userId());
        assertEquals(2, ranking.get(1).timeRank());
        assertEquals(45, ranking.get(1).totalDuration());

        assertEquals(user1, ranking.get(2).userId());
        assertEquals(3, ranking.get(2).timeRank());
        assertEquals(30, ranking.get(2).totalDuration());
    }

    @DisplayName("지난 주 칼로리 소모량 랭킹 조회 테스트")
    @Test
    void getLastWeekCalorieRanking() {
        // given
        Long user1 = 1L;
        Long user2 = 2L;
        Long user3 = 3L;

        statsService.createLastWeekStats(user1, 30, 300);
        statsService.createLastWeekStats(user2, 60, 500);
        statsService.createLastWeekStats(user3, 45, 400);

        // when
        List<CalorieRankingDto> ranking = statsService.getLastWeekCalorieRanking();

        // then
        assertEquals(3, ranking.size());

        assertEquals(user2, ranking.get(0).userId());
        assertEquals(1, ranking.get(0).calorieRank());
        assertEquals(500, ranking.get(0).totalCalories());

        assertEquals(user3, ranking.get(1).userId());
        assertEquals(2, ranking.get(1).calorieRank());
        assertEquals(400, ranking.get(1).totalCalories());

        assertEquals(user1, ranking.get(2).userId());
        assertEquals(3, ranking.get(2).calorieRank());
        assertEquals(300, ranking.get(2).totalCalories());
    }

    @DisplayName("날짜 검색을 통한 운동량 랭킹 조회 테스트")
    @Test
    void getTimeRankingByDate() {
        // given
        Long user1 = 1L;
        Long user2 = 2L;
        Long user3 = 3L;

        statsService.createLastWeekStats(user1, 30, 300);
        statsService.createLastWeekStats(user2, 60, 500);
        statsService.createLastWeekStats(user3, 45, 400);

        // when
        List<TimeRankingDto> ranking = statsService.getTimeRankingByDate(LocalDate.now().minusDays(7));

        // then
        assertEquals(3, ranking.size());

        assertEquals(user2, ranking.get(0).userId());
        assertEquals(1, ranking.get(0).timeRank());
        assertEquals(60, ranking.get(0).totalDuration());

        assertEquals(user3, ranking.get(1).userId());
        assertEquals(2, ranking.get(1).timeRank());
        assertEquals(45, ranking.get(1).totalDuration());

        assertEquals(user1, ranking.get(2).userId());
        assertEquals(3, ranking.get(2).timeRank());
        assertEquals(30, ranking.get(2).totalDuration());
    }

    @DisplayName("날짜 검색을 통한 칼로리 소모량 랭킹 조회 테스트")
    @Test
    void getCalorieRankingByDate() {
        // given
        Long user1 = 1L;
        Long user2 = 2L;
        Long user3 = 3L;

        statsService.createLastWeekStats(user1, 30, 300);
        statsService.createLastWeekStats(user2, 60, 500);
        statsService.createLastWeekStats(user3, 45, 400);

        // when
        List<CalorieRankingDto> ranking = statsService.getCalorieRankingByDate(LocalDate.now().minusDays(7));

        // then
        assertEquals(3, ranking.size());

        assertEquals(user2, ranking.get(0).userId());
        assertEquals(1, ranking.get(0).calorieRank());
        assertEquals(500, ranking.get(0).totalCalories());

        assertEquals(user3, ranking.get(1).userId());
        assertEquals(2, ranking.get(1).calorieRank());
        assertEquals(400, ranking.get(1).totalCalories());

        assertEquals(user1, ranking.get(2).userId());
        assertEquals(3, ranking.get(2).calorieRank());
        assertEquals(300, ranking.get(2).totalCalories());
    }
}
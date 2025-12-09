package com.team3.what_if_workout.stats.controller;

import com.team3.what_if_workout.stats.dto.request.CreateStatsDTO;
import com.team3.what_if_workout.stats.service.StatsService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/stats")
@RequiredArgsConstructor
public class StatsController {

    private final StatsService statsService;

    @PostMapping("/last-week-stats")
    @Operation(summary = "지난 주 통계 생성 API입니다.")
    public ResponseEntity<String> createLastWeekStats(@RequestBody CreateStatsDTO dto) {
        statsService.createLastWeekStats(dto.userId(), dto.totalDuration(), dto.totalCalories());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("지난 주 랭킹이 생성되었습니다.");
    }

    @GetMapping("/user-stats/{userId}")
    @Operation(summary = "사용자별 통계 조회 API입니다.")
    public ResponseEntity<?> viewStatsByUserId(@PathVariable("userId") Long userId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(statsService.getStatsByUserId(userId));
    }

    @GetMapping("/last-week-time-ranking")
    @Operation(summary = "지난 주 운동량 랭킹 조회 API입니다.")
    public ResponseEntity<?> viewLastWeekTimeRanking() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(statsService.getLastWeekTimeRanking());
    }

    @GetMapping("/last-week-calorie-ranking")
    @Operation(summary = "지난 주 칼로리 소모량 랭킹 조회 API입니다.")
    public ResponseEntity<?> viewLastWeekCalorieRanking() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(statsService.getLastWeekCalorieRanking());
    }

    @GetMapping("/search-time-ranking/{date}")
    @Operation(summary = "날짜 검색을 통한 운동량 랭킹 조회 API입니다.")
    public ResponseEntity<?> viewTimeRankingByDate(@PathVariable("date") LocalDate date) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(statsService.getTimeRankingByDate(date));
    }

    @GetMapping("/search-calorie-ranking/{date}")
    @Operation(summary = "날짜 검색을 통한 칼로리 소모량 랭킹 조회 API입니다.")
    public ResponseEntity<?> viewCalorieRankingByDate(@PathVariable("date") LocalDate date) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(statsService.getCalorieRankingByDate(date));
    }
}

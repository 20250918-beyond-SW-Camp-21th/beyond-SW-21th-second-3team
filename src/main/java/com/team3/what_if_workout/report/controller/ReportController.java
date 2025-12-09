package com.team3.what_if_workout.report.controller;

import com.team3.what_if_workout.report.dto.request.CreateReportDTO;
import com.team3.what_if_workout.report.service.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/report")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @PostMapping("/last-week-report")
    @Operation(summary = "지난 주 리포트 생성 API입니다.")
    public ResponseEntity<String> createLastWeekReport(@RequestBody CreateReportDTO dto) {
        reportService.createLastWeekReport(dto.userId(), dto.plannedAmount(), dto.achievedAmount());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("지난 주 리포트가 생성되었습니다.");
    }

    @GetMapping("/all-report/{userId}")
    @Operation(summary = "사용자별 전체 리포트 조회 API입니다.")
    public ResponseEntity<?> userViewReport(@PathVariable("userId") Long userId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(reportService.getReportsByUserId(userId));
    }

    @GetMapping("/search-report/{userId}/{date}")
    @Operation(summary = "날짜 검색을 통한 리포트 조회 API입니다.")
    public ResponseEntity<?> searchViewReport(
            @PathVariable("userId") Long userId,
            @PathVariable("date") LocalDate date
    ) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(reportService.getReportByDate(userId, date));
    }

    @DeleteMapping("/{userId}/{reportId}")
    @Operation(summary = "리포트 삭제 API입니다.")
    public ResponseEntity<String> deleteReport(
            @PathVariable("userId") Long userId,
            @PathVariable("reportId") Long reportId
    ) {
        reportService.deleteReportById(userId, reportId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body("리포트가 삭제되었습니다.");
    }
}

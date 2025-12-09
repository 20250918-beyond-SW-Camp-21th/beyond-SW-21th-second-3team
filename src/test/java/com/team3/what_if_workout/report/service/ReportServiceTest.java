package com.team3.what_if_workout.report.service;

import com.team3.what_if_workout.report.domain.Report;
import com.team3.what_if_workout.report.repository.ReportRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReportServiceTest {

    @Autowired
    private ReportService reportService;

    @Autowired
    private ReportRepository reportRepository;

    @DisplayName("지난 주 리포트 생성")
    @Test
    void createLastWeekReport_success_void() {
        // given
        Long userId = 1L;
        Integer plannedAmount = 1000;
        Integer achievedAmount = 800;

        // when
        reportService.createLastWeekReport(userId, plannedAmount, achievedAmount);

        // then
        Report report = reportRepository.findTopByUserIdOrderByCreatedAtDesc(userId)
                .orElseThrow(() -> new AssertionError("리포트가 저장되지 않았습니다."));

        assertEquals(userId, report.getUserId());
        assertEquals(plannedAmount, report.getPlannedAmount());
        assertEquals(achievedAmount, report.getAchievedAmount());
        assertEquals(plannedAmount - achievedAmount, report.getResultValue());
        assertNotNull(report.getStartDate());
        assertNotNull(report.getEndDate());
    }

    @DisplayName("사용자별 전체 리포트 조회")
    @Test
    void getReportsByUserId() {
        // given
        Long userId = 1L;

        // when
        reportService.getReportsByUserId(userId);

        // then
        List<Report> reports = reportRepository.findByUserId(userId);

        assertFalse(reports.isEmpty(), "해당 아이디의 리포트가 존재하지 않습니다.");
        assertEquals(userId, reports.get(0).getUserId());
    }

    @DisplayName("날짜 검색을 통한 리포트 조회")
    @Test
    void getReportByDate() {
        // given
        Long userId = 2L;

        LocalDate lastWeekStart = LocalDate.now()
                .minusWeeks(1)
                .with(DayOfWeek.MONDAY);
        LocalDate lastWeekEnd = lastWeekStart.plusDays(6);

        Report report = Report.builder()
                .userId(userId)
                .startDate(lastWeekStart)
                .endDate(lastWeekEnd)
                .plannedAmount(1000)
                .achievedAmount(800)
                .resultValue(200)
                .build();

        reportRepository.save(report);

        LocalDate searchDate = lastWeekStart.plusDays(3);

        // when
        Report foundReport = reportService.getReportByDate(userId, searchDate);

        // then
        assertNotNull(foundReport);
        assertEquals(userId, foundReport.getUserId());
        assertEquals(lastWeekStart, foundReport.getStartDate());
        assertEquals(lastWeekEnd, foundReport.getEndDate());
    }
}
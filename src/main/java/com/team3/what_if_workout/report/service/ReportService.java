package com.team3.what_if_workout.report.service;

import com.team3.what_if_workout.report.domain.Report;
import com.team3.what_if_workout.report.repository.ReportRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ReportRepository reportRepository;

    @Transactional
    public void createLastWeekReport(Long userId, Integer plannedAmount, Integer achievedAmount) {
        LocalDate lastWeekStart = getLastWeekStart();
        LocalDate lastWeekEnd = getLastWeekEnd();

        // 1. 이미 저번 주 리포트가 있는지 체크
        if (reportRepository.existsByUserIdAndStartDate(userId, lastWeekStart)) {
            throw new IllegalStateException("이미 지난 주 리포트가 존재합니다.");
        }

        // 2. resultValue 계산 (계획한 칼로리량 - 달성량)
        plannedAmount = (plannedAmount == null) ? 0 : plannedAmount;
        achievedAmount = (achievedAmount == null) ? 0 : achievedAmount;
        int resultValue = plannedAmount - achievedAmount;

        Report report = Report.builder()
                .userId(userId)
                .startDate(lastWeekStart)
                .endDate(lastWeekEnd)
                .plannedAmount(plannedAmount)
                .achievedAmount(achievedAmount)
                .resultValue(resultValue)
                .build();

        reportRepository.save(report);
    }

    public List<Report> getReportsByUserId(Long userId) {
        return reportRepository.findByUserId(userId);
    }

    public Report getReportByDate(Long userId, LocalDate date) {
        return reportRepository.findByUserIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(userId, date, date);
    }

    @Transactional
    public void deleteReportById(Long userId, Long reportId) {
        reportRepository.deleteByUserIdAndReportId(userId, reportId);
    }

    private LocalDate getLastWeekStart() { // 월요일 시작, 일요일 끝
        LocalDate today = LocalDate.now();
        LocalDate thisWeekMonday = today.with(DayOfWeek.MONDAY);
        return thisWeekMonday.minusWeeks(1);
    }

    private LocalDate getLastWeekEnd() {
        return getLastWeekStart().plusDays(6);
    }
}

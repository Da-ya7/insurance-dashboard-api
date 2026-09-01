package com.example.insurance_dashboard_api.controller;

import com.example.insurance_dashboard_api.dto.CompletedTransactionSummaryResponse;
import com.example.insurance_dashboard_api.dto.DashboardResponse;
import com.example.insurance_dashboard_api.dto.PerformanceResponse;
import com.example.insurance_dashboard_api.service.DashboardService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    // API 1
    @GetMapping("/api/dashboard")
    public DashboardResponse getDashboard() {

        return dashboardService.getDashboard();
    }

    // API 2
    @GetMapping("/api/transactions/completed")
    public CompletedTransactionSummaryResponse getCompletedTransactions(
            @RequestParam LocalDate from,
            @RequestParam LocalDate to) {

        LocalDateTime fromDateTime = from.atStartOfDay();

        LocalDateTime toDateTime = to.plusDays(1)
                .atStartOfDay()
                .minusNanos(1);

        return dashboardService.getCompletedTransactions(
                fromDateTime,
                toDateTime);
    }

    // API 3
    @GetMapping("/api/performance")
    public PerformanceResponse getPerformance(
            @RequestParam LocalDate from,
            @RequestParam LocalDate to) {

        LocalDateTime fromDateTime = from.atStartOfDay();

        LocalDateTime toDateTime = to.plusDays(1)
                .atStartOfDay()
                .minusNanos(1);

        return dashboardService.getPerformance(
                fromDateTime,
                toDateTime);
    }
}
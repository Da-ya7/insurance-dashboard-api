package com.example.insurance_dashboard_api.dto;

import java.time.LocalDate;

public class PerformanceTrend {

    private LocalDate date;
    private double policyTat;
    private double endorsementTat;

    public PerformanceTrend(
            LocalDate date,
            double policyTat,
            double endorsementTat) {

        this.date = date;
        this.policyTat = policyTat;
        this.endorsementTat = endorsementTat;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getPolicyTat() {
        return policyTat;
    }

    public double getEndorsementTat() {
        return endorsementTat;
    }
}
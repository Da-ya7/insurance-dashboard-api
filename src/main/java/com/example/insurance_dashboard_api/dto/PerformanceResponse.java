package com.example.insurance_dashboard_api.dto;

import java.util.List;

public class PerformanceResponse {

    private double policyTat;
    private double endorsementTat;
    private List<PerformanceTrend> trend;

    public PerformanceResponse(
            double policyTat,
            double endorsementTat,
            List<PerformanceTrend> trend) {

        this.policyTat = policyTat;
        this.endorsementTat = endorsementTat;
        this.trend = trend;
    }

    public double getPolicyTat() {
        return policyTat;
    }

    public double getEndorsementTat() {
        return endorsementTat;
    }

    public List<PerformanceTrend> getTrend() {
        return trend;
    }
}
package com.example.insurance_dashboard_api.dto;

import java.util.List;

public class CompletedTransactionSummaryResponse {

    private long totalCompleted;
    private long policyCompleted;
    private long endorsementCompleted;
    private List<CompletedTransactionResponse> transactions;

    public CompletedTransactionSummaryResponse(
            long totalCompleted,
            long policyCompleted,
            long endorsementCompleted,
            List<CompletedTransactionResponse> transactions) {

        this.totalCompleted = totalCompleted;
        this.policyCompleted = policyCompleted;
        this.endorsementCompleted = endorsementCompleted;
        this.transactions = transactions;
    }

    public long getTotalCompleted() {
        return totalCompleted;
    }

    public long getPolicyCompleted() {
        return policyCompleted;
    }

    public long getEndorsementCompleted() {
        return endorsementCompleted;
    }

    public List<CompletedTransactionResponse> getTransactions() {
        return transactions;
    }
}
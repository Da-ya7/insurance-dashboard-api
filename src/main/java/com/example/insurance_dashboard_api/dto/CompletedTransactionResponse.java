package com.example.insurance_dashboard_api.dto;

import java.time.LocalDateTime;

public class CompletedTransactionResponse {

    private String referenceNumber;
    private String transactionType;
    private LocalDateTime createdAt;
    private LocalDateTime completedAt;

    public CompletedTransactionResponse(
            String referenceNumber,
            String transactionType,
            LocalDateTime createdAt,
            LocalDateTime completedAt) {

        this.referenceNumber = referenceNumber;
        this.transactionType = transactionType;
        this.createdAt = createdAt;
        this.completedAt = completedAt;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }
}
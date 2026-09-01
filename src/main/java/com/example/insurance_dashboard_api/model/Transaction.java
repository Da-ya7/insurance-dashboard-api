package com.example.insurance_dashboard_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String referenceNumber;

    private String transactionType;

    private String stage;

    private LocalDateTime createdAt;

    private LocalDateTime completedAt;

    public Transaction() {
    }

    public Transaction(
            String referenceNumber,
            String transactionType,
            String stage,
            LocalDateTime createdAt,
            LocalDateTime completedAt) {
        this.referenceNumber = referenceNumber;
        this.transactionType = transactionType;
        this.stage = stage;
        this.createdAt = createdAt;
        this.completedAt = completedAt;
    }

    public Long getId() {
        return id;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public String getStage() {
        return stage;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }
}
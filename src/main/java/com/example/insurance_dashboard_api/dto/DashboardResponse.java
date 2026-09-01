package com.example.insurance_dashboard_api.dto;

public class DashboardResponse {

    private long pendingLeadReference;
    private long pendingQuotations;
    private long pendingPaymentConfirmations;
    private long pendingManagerApprovals;
    private long pendingRiAcceptance;
    private long pendingUnderwriterAction;
    private long completedTransactions;

    public DashboardResponse(
            long pendingLeadReference,
            long pendingQuotations,
            long pendingPaymentConfirmations,
            long pendingManagerApprovals,
            long pendingRiAcceptance,
            long pendingUnderwriterAction,
            long completedTransactions) {

        this.pendingLeadReference = pendingLeadReference;
        this.pendingQuotations = pendingQuotations;
        this.pendingPaymentConfirmations = pendingPaymentConfirmations;
        this.pendingManagerApprovals = pendingManagerApprovals;
        this.pendingRiAcceptance = pendingRiAcceptance;
        this.pendingUnderwriterAction = pendingUnderwriterAction;
        this.completedTransactions = completedTransactions;
    }

    public long getPendingLeadReference() {
        return pendingLeadReference;
    }

    public long getPendingQuotations() {
        return pendingQuotations;
    }

    public long getPendingPaymentConfirmations() {
        return pendingPaymentConfirmations;
    }

    public long getPendingManagerApprovals() {
        return pendingManagerApprovals;
    }

    public long getPendingRiAcceptance() {
        return pendingRiAcceptance;
    }

    public long getPendingUnderwriterAction() {
        return pendingUnderwriterAction;
    }

    public long getCompletedTransactions() {
        return completedTransactions;
    }
}
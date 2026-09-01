package com.example.insurance_dashboard_api.service;

import com.example.insurance_dashboard_api.dto.CompletedTransactionResponse;
import com.example.insurance_dashboard_api.dto.CompletedTransactionSummaryResponse;
import com.example.insurance_dashboard_api.dto.DashboardResponse;
import com.example.insurance_dashboard_api.dto.PerformanceResponse;
import com.example.insurance_dashboard_api.dto.PerformanceTrend;
import com.example.insurance_dashboard_api.model.Transaction;
import com.example.insurance_dashboard_api.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DashboardService {

    private final TransactionRepository transactionRepository;

    public DashboardService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    // API 1
    public DashboardResponse getDashboard() {

        long pendingLeadReference = transactionRepository.countByStage("LEAD_REFERENCE");

        long pendingQuotations = transactionRepository.countByStage("QUOTATION");

        long pendingPaymentConfirmations = transactionRepository.countByStage("PAYMENT_CONFIRMATION");

        long pendingManagerApprovals = transactionRepository.countByStage(
                "MANAGER_CUSTOMER_CONFIRMATION");

        long pendingRiAcceptance = transactionRepository.countByStage("RI_ACCEPTANCE");

        long pendingUnderwriterAction = transactionRepository.countByStage("UNDERWRITER_ACTION");

        long completedTransactions = transactionRepository.countByStage("COMPLETED");

        return new DashboardResponse(
                pendingLeadReference,
                pendingQuotations,
                pendingPaymentConfirmations,
                pendingManagerApprovals,
                pendingRiAcceptance,
                pendingUnderwriterAction,
                completedTransactions);
    }

    // API 2
    public CompletedTransactionSummaryResponse getCompletedTransactions(
            LocalDateTime from,
            LocalDateTime to) {

        List<Transaction> transactions = transactionRepository.findByStageAndCompletedAtBetween(
                "COMPLETED",
                from,
                to);

        List<CompletedTransactionResponse> responses = transactions.stream()
                .map(transaction -> new CompletedTransactionResponse(
                        transaction.getReferenceNumber(),
                        transaction.getTransactionType(),
                        transaction.getCreatedAt(),
                        transaction.getCompletedAt()))
                .toList();

        long totalCompleted = responses.size();

        long policyCompleted = responses.stream()
                .filter(transaction -> transaction.getTransactionType().equals("POLICY"))
                .count();

        long endorsementCompleted = responses.stream()
                .filter(transaction -> transaction.getTransactionType()
                        .equals("ENDORSEMENT"))
                .count();

        return new CompletedTransactionSummaryResponse(
                totalCompleted,
                policyCompleted,
                endorsementCompleted,
                responses);
    }

    // API 3
    public PerformanceResponse getPerformance(
            LocalDateTime from,
            LocalDateTime to) {

        List<Transaction> transactions = transactionRepository.findByStageAndCompletedAtBetween(
                "COMPLETED",
                from,
                to);

        // Overall Policy TAT
        double policyTat = transactions.stream()
                .filter(transaction -> transaction.getTransactionType()
                        .equals("POLICY"))
                .mapToDouble(this::calculateTatInDays)
                .average()
                .orElse(0.0);

        // Overall Endorsement TAT
        double endorsementTat = transactions.stream()
                .filter(transaction -> transaction.getTransactionType()
                        .equals("ENDORSEMENT"))
                .mapToDouble(this::calculateTatInDays)
                .average()
                .orElse(0.0);

        // Group transactions by completion date
        Map<LocalDate, List<Transaction>> transactionsByDate = transactions.stream()
                .collect(Collectors.groupingBy(
                        transaction -> transaction.getCompletedAt()
                                .toLocalDate()));

        // Create trend data
        List<PerformanceTrend> trend = transactionsByDate.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .map(entry -> {

                    LocalDate date = entry.getKey();

                    List<Transaction> dailyTransactions = entry.getValue();

                    double dailyPolicyTat = dailyTransactions.stream()
                            .filter(transaction -> transaction
                                    .getTransactionType()
                                    .equals("POLICY"))
                            .mapToDouble(
                                    this::calculateTatInDays)
                            .average()
                            .orElse(0.0);

                    double dailyEndorsementTat = dailyTransactions.stream()
                            .filter(transaction -> transaction
                                    .getTransactionType()
                                    .equals("ENDORSEMENT"))
                            .mapToDouble(
                                    this::calculateTatInDays)
                            .average()
                            .orElse(0.0);

                    return new PerformanceTrend(
                            date,
                            dailyPolicyTat,
                            dailyEndorsementTat);
                })
                .toList();

        return new PerformanceResponse(
                policyTat,
                endorsementTat,
                trend);
    }

    // Helper method
    private double calculateTatInDays(Transaction transaction) {

        Duration duration = Duration.between(
                transaction.getCreatedAt(),
                transaction.getCompletedAt());

        return duration.toHours() / 24.0;
    }
}
package com.example.insurance_dashboard_api.repository;

import com.example.insurance_dashboard_api.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository
        extends JpaRepository<Transaction, Long> {

    long countByStage(String stage);

    List<Transaction> findByStage(String stage);

    List<Transaction> findByStageAndCompletedAtBetween(
            String stage,
            LocalDateTime from,
            LocalDateTime to);
}
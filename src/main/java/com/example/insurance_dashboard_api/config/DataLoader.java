package com.example.insurance_dashboard_api.config;

import com.example.insurance_dashboard_api.model.Transaction;
import com.example.insurance_dashboard_api.repository.TransactionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner loadData(TransactionRepository repository) {

        return args -> {

            if (repository.count() > 0) {
                return;
            }
            repository.save(new Transaction(
                    "TXN001",
                    "POLICY",
                    "LEAD_REFERENCE",
                    LocalDateTime.now().minusDays(5),
                    null));

            repository.save(new Transaction(
                    "TXN002",
                    "POLICY",
                    "QUOTATION",
                    LocalDateTime.now().minusDays(4),
                    null));

            repository.save(new Transaction(
                    "TXN003",
                    "POLICY",
                    "PAYMENT_CONFIRMATION",
                    LocalDateTime.now().minusDays(3),
                    null));

            repository.save(new Transaction(
                    "TXN004",
                    "POLICY",
                    "MANAGER_CUSTOMER_CONFIRMATION",
                    LocalDateTime.now().minusDays(2),
                    null));

            repository.save(new Transaction(
                    "TXN005",
                    "POLICY",
                    "RI_ACCEPTANCE",
                    LocalDateTime.now().minusDays(2),
                    null));

            repository.save(new Transaction(
                    "TXN006",
                    "POLICY",
                    "UNDERWRITER_ACTION",
                    LocalDateTime.now().minusDays(1),
                    null));

            repository.save(new Transaction(
                    "TXN007",
                    "POLICY",
                    "COMPLETED",
                    LocalDateTime.now().minusDays(7),
                    LocalDateTime.now().minusDays(5)));

            repository.save(new Transaction(
                    "TXN008",
                    "ENDORSEMENT",
                    "COMPLETED",
                    LocalDateTime.now().minusDays(6),
                    LocalDateTime.now().minusDays(3)));

            repository.save(new Transaction(
                    "TXN009",
                    "POLICY",
                    "QUOTATION",
                    LocalDateTime.now().minusDays(2),
                    null));

            repository.save(new Transaction(
                    "TXN010",
                    "ENDORSEMENT",
                    "PAYMENT_CONFIRMATION",
                    LocalDateTime.now().minusDays(1),
                    null));
        };
    }
}
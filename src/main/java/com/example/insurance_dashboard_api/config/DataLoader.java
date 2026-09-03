package com.example.insurance_dashboard_api.config;

import com.example.insurance_dashboard_api.model.Transaction;
import com.example.insurance_dashboard_api.repository.TransactionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Configuration
public class DataLoader {

        private static final String[] TYPES = { "POLICY", "ENDORSEMENT" };
        private static final String[] STAGES = {
                        "LEAD_REFERENCE",
                        "QUOTATION",
                        "PAYMENT_CONFIRMATION",
                        "MANAGER_CUSTOMER_CONFIRMATION",
                        "RI_ACCEPTANCE",
                        "UNDERWRITER_ACTION",
                        "COMPLETED"
        };

        @Bean
        CommandLineRunner loadData(TransactionRepository repository) {

                return args -> {

                        if (repository.count() > 0) {
                                return;
                        }

                        Random rnd = new Random(42);
                        int txnCounter = 1;

                        for (int daysAgo = 30; daysAgo >= 0; daysAgo--) {

                                int txnsToday = 2 + rnd.nextInt(5); // 2-6 per day

                                for (int i = 0; i < txnsToday; i++) {

                                        String type = TYPES[rnd.nextInt(TYPES.length)];
                                        String stage = STAGES[rnd.nextInt(STAGES.length)];

                                        LocalDateTime createdAt = LocalDateTime.now()
                                                        .minusDays(daysAgo)
                                                        .minusHours(rnd.nextInt(20))
                                                        .minusMinutes(rnd.nextInt(60));

                                        LocalDateTime completedAt = null;
                                        if (stage.equals("COMPLETED")) {
                                                completedAt = createdAt.plusDays(1 + rnd.nextInt(4));
                                        }

                                        String ref = String.format("TXN%03d", txnCounter++);

                                        repository.save(new Transaction(
                                                        ref,
                                                        type,
                                                        stage,
                                                        createdAt,
                                                        completedAt));
                                }
                        }
                };
        }
}
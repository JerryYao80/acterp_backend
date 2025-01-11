package com.arterp.finance.service;

import com.arterp.finance.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FinanceService {
    private final TransactionRepository transactionRepository;

    @Transactional(readOnly = true)
    public Map<String, Long> getTypeDistribution() {
        return transactionRepository.countByType().stream()
            .collect(Collectors.toMap(
                row -> (String) row[0],
                row -> (Long) row[1]
            ));
    }

    @Transactional(readOnly = true)
    public Map<String, Long> getStatusDistribution() {
        return transactionRepository.countByStatus().stream()
            .collect(Collectors.toMap(
                row -> (String) row[0],
                row -> (Long) row[1]
            ));
    }

    @Transactional(readOnly = true)
    public Map<String, Long> getPaymentMethodDistribution() {
        return transactionRepository.countByPaymentMethod().stream()
            .collect(Collectors.toMap(
                row -> (String) row[0],
                row -> (Long) row[1]
            ));
    }
} 
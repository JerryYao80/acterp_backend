package com.arterp.finance.controller;

import com.arterp.common.dto.BaseResponse;
import com.arterp.finance.service.FinanceService;
import com.arterp.finance.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/finance")
@RequiredArgsConstructor
public class FinanceController {
    private final FinanceService financeService;
    private final TransactionRepository transactionRepository;

    @GetMapping("/stats")
    @PreAuthorize("hasRole('USER')")
    public BaseResponse<Map<String, Object>> getFinanceStats() {
        Map<String, Object> stats = new HashMap<>();
        
        // Get monthly revenue
        LocalDateTime startOfMonth = LocalDateTime.now().withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0);
        BigDecimal monthlyRevenue = transactionRepository.sumAmountByTypeAndDateAfter("Income", startOfMonth);
        stats.put("monthlyRevenue", monthlyRevenue);
        
        // Get revenue growth (vs last month)
        LocalDateTime startOfLastMonth = startOfMonth.minusMonths(1);
        BigDecimal lastMonthRevenue = transactionRepository.sumAmountByTypeAndDateBetween(
            "Income", startOfLastMonth, startOfMonth);
        double revenueGrowth = lastMonthRevenue.compareTo(BigDecimal.ZERO) > 0 
            ? monthlyRevenue.subtract(lastMonthRevenue).multiply(new BigDecimal("100")).divide(lastMonthRevenue, 2, RoundingMode.HALF_UP).doubleValue()
            : 0;
        stats.put("revenueGrowth", revenueGrowth);
        
        // Get transaction type distribution
        Map<String, Long> typeDistribution = financeService.getTypeDistribution();
        stats.put("typeDistribution", typeDistribution);
        
        // Get transaction status distribution
        Map<String, Long> statusDistribution = financeService.getStatusDistribution();
        stats.put("statusDistribution", statusDistribution);
        
        // Get payment method distribution
        Map<String, Long> paymentMethodDistribution = financeService.getPaymentMethodDistribution();
        stats.put("paymentMethodDistribution", paymentMethodDistribution);
        
        return BaseResponse.success(stats);
    }
} 
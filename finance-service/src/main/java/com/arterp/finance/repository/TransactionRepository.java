package com.arterp.finance.repository;

import com.arterp.finance.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("SELECT t FROM Transaction t WHERE " +
            "LOWER(t.type) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(t.category) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(t.status) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<Transaction> search(String search, Pageable pageable);

    @Query("SELECT SUM(t.amount) FROM Transaction t WHERE t.type = :type AND t.date > :date")
    BigDecimal sumAmountByTypeAndDateAfter(@Param("type") String type, @Param("date") LocalDateTime date);

    @Query("SELECT SUM(t.amount) FROM Transaction t WHERE t.type = :type AND t.date BETWEEN :startDate AND :endDate")
    BigDecimal sumAmountByTypeAndDateBetween(
        @Param("type") String type, 
        @Param("startDate") LocalDateTime startDate, 
        @Param("endDate") LocalDateTime endDate
    );

    @Query("SELECT t.type, COUNT(t) FROM Transaction t GROUP BY t.type")
    List<Object[]> countByType();

    @Query("SELECT t.status, COUNT(t) FROM Transaction t GROUP BY t.status")
    List<Object[]> countByStatus();

    @Query("SELECT t.paymentMethod, COUNT(t) FROM Transaction t GROUP BY t.paymentMethod")
    List<Object[]> countByPaymentMethod();
} 
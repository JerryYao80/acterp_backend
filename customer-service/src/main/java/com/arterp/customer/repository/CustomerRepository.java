package com.arterp.customer.repository;

import com.arterp.customer.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByEmail(String email);

    Optional<Customer> findByPhone(String phone);

    Optional<Customer> findByIdNumber(String idNumber);

    @Query("SELECT c FROM Customer c WHERE " +
            "LOWER(c.name) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(c.phone) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(c.email) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(c.idNumber) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<Customer> search(String search, Pageable pageable);

    @Query("SELECT c.status, COUNT(c) FROM Customer c GROUP BY c.status")
    List<Object[]> countByStatus();

    @Query("SELECT c.customerType, COUNT(c) FROM Customer c GROUP BY c.customerType")
    List<Object[]> countByCustomerType();

    @Query("SELECT c.riskLevel, COUNT(c) FROM Customer c GROUP BY c.riskLevel")
    List<Object[]> countByRiskLevel();

    @Query("SELECT c.source, COUNT(c) FROM Customer c GROUP BY c.source")
    List<Object[]> countBySource();

    @Query("SELECT c.nationality, COUNT(c) FROM Customer c GROUP BY c.nationality")
    List<Object[]> countByNationality();

    long countByCreatedAtAfter(LocalDateTime date);
} 
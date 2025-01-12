package com.arterp.customer.repository;

import com.arterp.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Page<Customer> findByNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrPhoneContaining(
        String name, String email, String phone, Pageable pageable);
} 
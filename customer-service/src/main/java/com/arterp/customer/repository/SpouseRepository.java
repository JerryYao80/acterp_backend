package com.arterp.customer.repository;

import com.arterp.customer.entity.Spouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpouseRepository extends JpaRepository<Spouse, Long> {
    Optional<Spouse> findByCustomerId(Long customerId);

    Optional<Spouse> findByEmail(String email);

    Optional<Spouse> findByPhone(String phone);

    Optional<Spouse> findByIdNumber(String idNumber);
} 
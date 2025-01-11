package com.arterp.customer.repository;

import com.arterp.customer.entity.ContactRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRecordRepository extends JpaRepository<ContactRecord, Long> {
    Page<ContactRecord> findByCustomerId(Long customerId, Pageable pageable);

    @Query("SELECT c FROM ContactRecord c WHERE " +
            "c.customerId = :customerId AND " +
            "(LOWER(c.contactType) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(c.purpose) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(c.status) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<ContactRecord> searchByCustomerId(Long customerId, String search, Pageable pageable);

    @Query("SELECT c.contactType, COUNT(c) FROM ContactRecord c GROUP BY c.contactType")
    List<Object[]> countByContactType();

    @Query("SELECT c.status, COUNT(c) FROM ContactRecord c GROUP BY c.status")
    List<Object[]> countByStatus();

    @Query("SELECT c.purpose, COUNT(c) FROM ContactRecord c GROUP BY c.purpose")
    List<Object[]> countByPurpose();

    @Query("SELECT c.channel, COUNT(c) FROM ContactRecord c GROUP BY c.channel")
    List<Object[]> countByChannel();

    @Query("SELECT c.priority, COUNT(c) FROM ContactRecord c GROUP BY c.priority")
    List<Object[]> countByPriority();

    @Query("SELECT c.assignedTo, COUNT(c) FROM ContactRecord c GROUP BY c.assignedTo")
    List<Object[]> countByAssignedTo();
} 
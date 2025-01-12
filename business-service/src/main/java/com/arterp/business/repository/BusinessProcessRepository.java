package com.arterp.business.repository;

import com.arterp.business.entity.BusinessProcess;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface BusinessProcessRepository extends JpaRepository<BusinessProcess, Long> {
    List<BusinessProcess> findByDeletedFalse();
    Optional<BusinessProcess> findByIdAndDeletedFalse(Long id);
    List<BusinessProcess> findByCustomerIdAndDeletedFalse(Long customerId);
    List<BusinessProcess> findByProcessTypeAndDeletedFalse(String processType);
    List<BusinessProcess> findByStageAndDeletedFalse(String stage);
    List<BusinessProcess> findByStatusAndDeletedFalse(String status);
    List<BusinessProcess> findByExpectedEndDateBeforeAndStatusNotAndDeletedFalse(LocalDateTime date, String status);
    Long countByStartDateBetweenAndDeletedFalse(LocalDateTime startDate, LocalDateTime endDate);
    Page<BusinessProcess> findByCustomerIdAndDeletedFalse(Long customerId, Pageable pageable);

    @Query("SELECT COUNT(p) FROM BusinessProcess p WHERE p.deleted = false")
    Long countByDeletedFalse();

    @Query("SELECT p.processType, COUNT(p) FROM BusinessProcess p WHERE p.deleted = false GROUP BY p.processType")
    List<Object[]> countByProcessType();

    @Query("SELECT p.stage, COUNT(p) FROM BusinessProcess p WHERE p.deleted = false GROUP BY p.stage")
    List<Object[]> countByStage();

    @Query("SELECT p.status, COUNT(p) FROM BusinessProcess p WHERE p.deleted = false GROUP BY p.status")
    List<Object[]> countByStatus();

    @Query("SELECT CAST(p.startDate AS date), p.processType, COUNT(p) FROM BusinessProcess p " +
            "WHERE p.startDate BETWEEN :startDate AND :endDate AND p.deleted = false " +
            "GROUP BY CAST(p.startDate AS date), p.processType")
    List<Object[]> countByStartDateGroupByType(@Param("startDate") LocalDateTime startDate,
                                             @Param("endDate") LocalDateTime endDate);
} 
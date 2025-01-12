package com.arterp.business.repository;

import com.arterp.business.entity.Business;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface BusinessRepository extends JpaRepository<Business, Long> {
    List<Business> findByDeletedFalse();
    Optional<Business> findByIdAndDeletedFalse(Long id);
    List<Business> findByCustomerIdAndDeletedFalse(Long customerId);
    List<Business> findByTypeAndDeletedFalse(String type);
    List<Business> findByStageAndDeletedFalse(String stage);
    List<Business> findByStatusAndDeletedFalse(String status);
    List<Business> findByExpectedEndDateBeforeAndStatusNotAndDeletedFalse(LocalDateTime date, String status);

    @Query("SELECT CAST(b.startDate AS date), b.type, COUNT(b) FROM Business b " +
           "WHERE b.startDate BETWEEN :startDate AND :endDate AND b.deleted = false " +
           "GROUP BY CAST(b.startDate AS date), b.type")
    List<Object[]> countByStartDateBetweenAndDeletedFalse(@Param("startDate") LocalDateTime startDate,
                                                         @Param("endDate") LocalDateTime endDate);

    @Query("SELECT b.type, COUNT(b) FROM Business b WHERE b.deleted = false GROUP BY b.type")
    List<Object[]> countByType();

    @Query("SELECT b.stage, COUNT(b) FROM Business b WHERE b.deleted = false GROUP BY b.stage")
    List<Object[]> countByStage();

    @Query("SELECT b.status, COUNT(b) FROM Business b WHERE b.deleted = false GROUP BY b.status")
    List<Object[]> countByStatus();
} 
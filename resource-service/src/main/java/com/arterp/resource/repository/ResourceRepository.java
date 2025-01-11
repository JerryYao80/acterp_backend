package com.arterp.resource.repository;

import com.arterp.resource.entity.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Long> {
    @Query("SELECT r FROM Resource r WHERE " +
            "TYPE(r) = :type AND " +
            "(LOWER(r.name) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(r.location) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<Resource> searchByType(Class<?> type, String search, Pageable pageable);

    @Query("SELECT r FROM Resource r WHERE " +
            "TYPE(r) = :type AND " +
            "r.status = :status")
    Page<Resource> findByTypeAndStatus(Class<?> type, String status, Pageable pageable);

    @Query("SELECT r.status, COUNT(r) FROM Resource r WHERE TYPE(r) = :type GROUP BY r.status")
    List<Object[]> countByTypeAndStatus(Class<?> type);

    @Query("SELECT r.location, COUNT(r) FROM Resource r WHERE TYPE(r) = :type GROUP BY r.location")
    List<Object[]> countByTypeAndLocation(Class<?> type);

    @Query("SELECT r.quality, COUNT(r) FROM Resource r WHERE TYPE(r) = :type GROUP BY r.quality")
    List<Object[]> countByTypeAndQuality(Class<?> type);

    @Query("SELECT r.riskLevel, COUNT(r) FROM Resource r WHERE TYPE(r) = :type GROUP BY r.riskLevel")
    List<Object[]> countByTypeAndRiskLevel(Class<?> type);

    long countByStatus(String status);

    long countByCreatedAtAfter(LocalDateTime date);

    @Query("SELECT COUNT(r) FROM Resource r WHERE TYPE(r) = :type")
    long countByDiscriminatorValue(@Param("type") String type);
} 
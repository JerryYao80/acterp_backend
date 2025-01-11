package com.arterp.document.repository;

import com.arterp.document.entity.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
    @Query("SELECT d FROM Document d WHERE " +
            "LOWER(d.name) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(d.description) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(d.tags) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<Document> search(String search, Pageable pageable);

    @Query("SELECT d FROM Document d WHERE " +
            "d.relatedEntityType = :entityType AND " +
            "d.relatedEntityId = :entityId")
    List<Document> findByRelatedEntity(
        @Param("entityType") String entityType,
        @Param("entityId") Long entityId
    );

    @Query("SELECT d FROM Document d WHERE " +
            "d.relatedEntityType = :entityType AND " +
            "d.relatedEntityId = :entityId AND " +
            "d.category = :category")
    List<Document> findByRelatedEntityAndCategory(
        @Param("entityType") String entityType,
        @Param("entityId") Long entityId,
        @Param("category") String category
    );

    @Query("SELECT d.category, COUNT(d) FROM Document d GROUP BY d.category")
    List<Object[]> countByCategory();

    @Query("SELECT d.type, COUNT(d) FROM Document d GROUP BY d.type")
    List<Object[]> countByType();

    @Query("SELECT d.status, COUNT(d) FROM Document d GROUP BY d.status")
    List<Object[]> countByStatus();
} 
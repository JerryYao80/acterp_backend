package com.arterp.riskservice.repository;

import com.arterp.riskservice.entity.RiskWarning;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RiskWarningRepository extends JpaRepository<RiskWarning, Long> {
    List<RiskWarning> findByTypeAndStatus(String type, String status);
    List<RiskWarning> findByLevelAndStatus(String level, String status);
    List<RiskWarning> findBySourceIdAndType(Long sourceId, String type);
    List<RiskWarning> findByStatus(String status);
} 
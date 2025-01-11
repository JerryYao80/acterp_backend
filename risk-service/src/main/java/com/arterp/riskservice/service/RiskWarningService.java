package com.arterp.riskservice.service;

import com.arterp.riskservice.dto.RiskWarningDTO;
import java.util.List;

public interface RiskWarningService {
    RiskWarningDTO createRiskWarning(RiskWarningDTO riskWarningDTO);
    RiskWarningDTO updateRiskWarning(Long id, RiskWarningDTO riskWarningDTO);
    void deleteRiskWarning(Long id);
    RiskWarningDTO getRiskWarning(Long id);
    List<RiskWarningDTO> getAllRiskWarnings();
    List<RiskWarningDTO> getRiskWarningsByType(String type, String status);
    List<RiskWarningDTO> getRiskWarningsByLevel(String level, String status);
    List<RiskWarningDTO> getRiskWarningsBySourceAndType(Long sourceId, String type);
    List<RiskWarningDTO> getRiskWarningsByStatus(String status);
    RiskWarningDTO resolveRiskWarning(Long id, String resolvedBy, String resolutionNotes);
} 
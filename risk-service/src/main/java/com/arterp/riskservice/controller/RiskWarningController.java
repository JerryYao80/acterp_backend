package com.arterp.riskservice.controller;

import com.arterp.riskservice.dto.RiskWarningDTO;
import com.arterp.riskservice.service.RiskWarningService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/risk-warnings")
@RequiredArgsConstructor
public class RiskWarningController {

    private final RiskWarningService riskWarningService;

    @PostMapping
    public ResponseEntity<RiskWarningDTO> createRiskWarning(@RequestBody RiskWarningDTO riskWarningDTO) {
        return ResponseEntity.ok(riskWarningService.createRiskWarning(riskWarningDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RiskWarningDTO> updateRiskWarning(
            @PathVariable Long id,
            @RequestBody RiskWarningDTO riskWarningDTO) {
        return ResponseEntity.ok(riskWarningService.updateRiskWarning(id, riskWarningDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRiskWarning(@PathVariable Long id) {
        riskWarningService.deleteRiskWarning(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RiskWarningDTO> getRiskWarning(@PathVariable Long id) {
        return ResponseEntity.ok(riskWarningService.getRiskWarning(id));
    }

    @GetMapping
    public ResponseEntity<List<RiskWarningDTO>> getAllRiskWarnings() {
        return ResponseEntity.ok(riskWarningService.getAllRiskWarnings());
    }

    @GetMapping("/by-type")
    public ResponseEntity<List<RiskWarningDTO>> getRiskWarningsByType(
            @RequestParam String type,
            @RequestParam(required = false, defaultValue = "ACTIVE") String status) {
        return ResponseEntity.ok(riskWarningService.getRiskWarningsByType(type, status));
    }

    @GetMapping("/by-level")
    public ResponseEntity<List<RiskWarningDTO>> getRiskWarningsByLevel(
            @RequestParam String level,
            @RequestParam(required = false, defaultValue = "ACTIVE") String status) {
        return ResponseEntity.ok(riskWarningService.getRiskWarningsByLevel(level, status));
    }

    @GetMapping("/by-source")
    public ResponseEntity<List<RiskWarningDTO>> getRiskWarningsBySourceAndType(
            @RequestParam Long sourceId,
            @RequestParam String type) {
        return ResponseEntity.ok(riskWarningService.getRiskWarningsBySourceAndType(sourceId, type));
    }

    @GetMapping("/by-status")
    public ResponseEntity<List<RiskWarningDTO>> getRiskWarningsByStatus(
            @RequestParam String status) {
        return ResponseEntity.ok(riskWarningService.getRiskWarningsByStatus(status));
    }

    @PostMapping("/{id}/resolve")
    public ResponseEntity<RiskWarningDTO> resolveRiskWarning(
            @PathVariable Long id,
            @RequestParam String resolvedBy,
            @RequestParam String resolutionNotes) {
        return ResponseEntity.ok(riskWarningService.resolveRiskWarning(id, resolvedBy, resolutionNotes));
    }
} 
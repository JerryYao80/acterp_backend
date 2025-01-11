package com.arterp.riskservice.service.impl;

import com.arterp.riskservice.dto.RiskWarningDTO;
import com.arterp.riskservice.entity.RiskWarning;
import com.arterp.riskservice.repository.RiskWarningRepository;
import com.arterp.riskservice.service.RiskWarningService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class RiskWarningServiceImpl implements RiskWarningService {

    private final RiskWarningRepository riskWarningRepository;

    private RiskWarningDTO convertToDTO(RiskWarning riskWarning) {
        RiskWarningDTO dto = new RiskWarningDTO();
        BeanUtils.copyProperties(riskWarning, dto);
        return dto;
    }

    private RiskWarning convertToEntity(RiskWarningDTO dto) {
        RiskWarning entity = new RiskWarning();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    @Override
    public RiskWarningDTO createRiskWarning(RiskWarningDTO riskWarningDTO) {
        RiskWarning riskWarning = convertToEntity(riskWarningDTO);
        riskWarning.setStatus("ACTIVE");
        riskWarning = riskWarningRepository.save(riskWarning);
        return convertToDTO(riskWarning);
    }

    @Override
    public RiskWarningDTO updateRiskWarning(Long id, RiskWarningDTO riskWarningDTO) {
        RiskWarning existingWarning = riskWarningRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Risk warning not found with id: " + id));
        
        BeanUtils.copyProperties(riskWarningDTO, existingWarning, "id", "createdAt");
        existingWarning = riskWarningRepository.save(existingWarning);
        return convertToDTO(existingWarning);
    }

    @Override
    public void deleteRiskWarning(Long id) {
        if (!riskWarningRepository.existsById(id)) {
            throw new EntityNotFoundException("Risk warning not found with id: " + id);
        }
        riskWarningRepository.deleteById(id);
    }

    @Override
    public RiskWarningDTO getRiskWarning(Long id) {
        return riskWarningRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new EntityNotFoundException("Risk warning not found with id: " + id));
    }

    @Override
    public List<RiskWarningDTO> getAllRiskWarnings() {
        return riskWarningRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<RiskWarningDTO> getRiskWarningsByType(String type, String status) {
        return riskWarningRepository.findByTypeAndStatus(type, status).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<RiskWarningDTO> getRiskWarningsByLevel(String level, String status) {
        return riskWarningRepository.findByLevelAndStatus(level, status).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<RiskWarningDTO> getRiskWarningsBySourceAndType(Long sourceId, String type) {
        return riskWarningRepository.findBySourceIdAndType(sourceId, type).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<RiskWarningDTO> getRiskWarningsByStatus(String status) {
        return riskWarningRepository.findByStatus(status).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public RiskWarningDTO resolveRiskWarning(Long id, String resolvedBy, String resolutionNotes) {
        RiskWarning warning = riskWarningRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Risk warning not found with id: " + id));
        
        warning.setStatus("RESOLVED");
        warning.setResolvedBy(resolvedBy);
        warning.setResolutionNotes(resolutionNotes);
        warning.setUpdatedAt(LocalDateTime.now());
        
        warning = riskWarningRepository.save(warning);
        return convertToDTO(warning);
    }
} 
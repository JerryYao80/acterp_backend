package com.arterp.business.service.impl;

import com.arterp.business.dto.BusinessDTO;
import com.arterp.business.entity.Business;
import com.arterp.business.repository.BusinessRepository;
import com.arterp.business.service.BusinessService;
import com.arterp.common.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BusinessServiceImpl implements BusinessService {
    private final BusinessRepository businessRepository;

    @Override
    @Transactional
    public BusinessDTO createBusiness(BusinessDTO businessDTO) {
        Business business = convertToEntity(businessDTO);
        business = businessRepository.save(business);
        return convertToDTO(business);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BusinessDTO> getAllBusinesses() {
        return businessRepository.findByDeletedFalse().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public BusinessDTO getBusinessById(Long id) {
        Business business = businessRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new BusinessException("Business not found with id: " + id));
        return convertToDTO(business);
    }

    @Override
    @Transactional
    public BusinessDTO updateBusiness(Long id, BusinessDTO businessDTO) {
        Business business = businessRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new BusinessException("Business not found with id: " + id));
        updateBusinessFields(business, businessDTO);
        business = businessRepository.save(business);
        return convertToDTO(business);
    }

    @Override
    @Transactional
    public void deleteBusiness(Long id) {
        Business business = businessRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new BusinessException("Business not found with id: " + id));
        business.setDeleted(true);
        businessRepository.save(business);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BusinessDTO> getBusinessesByCustomerId(Long customerId) {
        return businessRepository.findByCustomerIdAndDeletedFalse(customerId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<BusinessDTO> getBusinessesByType(String type) {
        return businessRepository.findByTypeAndDeletedFalse(type).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<BusinessDTO> getBusinessesByStage(String stage) {
        return businessRepository.findByStageAndDeletedFalse(stage).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<BusinessDTO> getBusinessesByStatus(String status) {
        return businessRepository.findByStatusAndDeletedFalse(status).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public BusinessDTO updateBusinessType(Long id, String type) {
        Business business = businessRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new BusinessException("Business not found with id: " + id));
        business.setType(type);
        business = businessRepository.save(business);
        return convertToDTO(business);
    }

    @Override
    @Transactional
    public BusinessDTO updateBusinessStage(Long id, String stage) {
        Business business = businessRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new BusinessException("Business not found with id: " + id));
        business.setStage(stage);
        business = businessRepository.save(business);
        return convertToDTO(business);
    }

    @Override
    @Transactional
    public BusinessDTO updateBusinessStatus(Long id, String status) {
        Business business = businessRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new BusinessException("Business not found with id: " + id));
        business.setStatus(status);
        business = businessRepository.save(business);
        return convertToDTO(business);
    }

    @Override
    @Transactional
    public BusinessDTO updateBusinessStartDate(Long id, LocalDateTime startDate) {
        Business business = businessRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new BusinessException("Business not found with id: " + id));
        business.setStartDate(startDate);
        business = businessRepository.save(business);
        return convertToDTO(business);
    }

    @Override
    @Transactional
    public BusinessDTO updateBusinessExpectedEndDate(Long id, LocalDateTime expectedEndDate) {
        Business business = businessRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new BusinessException("Business not found with id: " + id));
        business.setExpectedEndDate(expectedEndDate);
        business = businessRepository.save(business);
        return convertToDTO(business);
    }

    @Override
    @Transactional
    public BusinessDTO updateBusinessActualEndDate(Long id, LocalDateTime actualEndDate) {
        Business business = businessRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new BusinessException("Business not found with id: " + id));
        business.setActualEndDate(actualEndDate);
        business = businessRepository.save(business);
        return convertToDTO(business);
    }

    @Override
    @Transactional
    public BusinessDTO updateBusinessCustomerId(Long id, Long customerId) {
        Business business = businessRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new BusinessException("Business not found with id: " + id));
        business.setCustomerId(customerId);
        business = businessRepository.save(business);
        return convertToDTO(business);
    }

    @Override
    @Transactional
    public BusinessDTO updateBusinessDescription(Long id, String description) {
        Business business = businessRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new BusinessException("Business not found with id: " + id));
        business.setDescription(description);
        business = businessRepository.save(business);
        return convertToDTO(business);
    }

    @Override
    @Transactional
    public BusinessDTO updateBusinessRemark(Long id, String remark) {
        Business business = businessRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new BusinessException("Business not found with id: " + id));
        business.setRemark(remark);
        business = businessRepository.save(business);
        return convertToDTO(business);
    }

    @Override
    @Transactional
    public BusinessDTO updateBusinessPriority(Long id, Integer priority) {
        Business business = businessRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new BusinessException("Business not found with id: " + id));
        business.setPriority(priority);
        business = businessRepository.save(business);
        return convertToDTO(business);
    }

    @Override
    @Transactional
    public BusinessDTO updateBusinessDifficulty(Long id, Integer difficulty) {
        Business business = businessRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new BusinessException("Business not found with id: " + id));
        business.setDifficulty(difficulty);
        business = businessRepository.save(business);
        return convertToDTO(business);
    }

    @Override
    @Transactional
    public BusinessDTO updateBusinessValue(Long id, Integer value) {
        Business business = businessRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new BusinessException("Business not found with id: " + id));
        business.setValue(value);
        business = businessRepository.save(business);
        return convertToDTO(business);
    }

    @Override
    @Transactional
    public BusinessDTO updateBusinessProgress(Long id, Integer progress) {
        Business business = businessRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new BusinessException("Business not found with id: " + id));
        business.setProgress(progress);
        business = businessRepository.save(business);
        return convertToDTO(business);
    }

    @Override
    @Transactional
    public BusinessDTO updateBusinessCost(Long id, Double cost) {
        Business business = businessRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new BusinessException("Business not found with id: " + id));
        business.setCost(cost);
        business = businessRepository.save(business);
        return convertToDTO(business);
    }

    @Override
    @Transactional
    public BusinessDTO updateBusinessRevenue(Long id, Double revenue) {
        Business business = businessRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new BusinessException("Business not found with id: " + id));
        business.setRevenue(revenue);
        business = businessRepository.save(business);
        return convertToDTO(business);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BusinessDTO> getOverdueBusinesses(LocalDateTime date) {
        return businessRepository.findByExpectedEndDateBeforeAndStatusNotAndDeletedFalse(date, "Completed").stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Long> getTypeDistribution() {
        List<Object[]> results = businessRepository.countByType();
        Map<String, Long> distribution = new HashMap<>();
        for (Object[] result : results) {
            distribution.put((String) result[0], (Long) result[1]);
        }
        return distribution;
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Long> getStageDistribution() {
        List<Object[]> results = businessRepository.countByStage();
        Map<String, Long> distribution = new HashMap<>();
        for (Object[] result : results) {
            distribution.put((String) result[0], (Long) result[1]);
        }
        return distribution;
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Long> getStatusDistribution() {
        List<Object[]> results = businessRepository.countByStatus();
        Map<String, Long> distribution = new HashMap<>();
        for (Object[] result : results) {
            distribution.put((String) result[0], (Long) result[1]);
        }
        return distribution;
    }

    @Override
    @Transactional(readOnly = true)
    public Map<LocalDate, Map<String, Long>> getProcessTypeDistributionByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        List<Object[]> results = businessRepository.countByStartDateBetweenAndDeletedFalse(startDate, endDate);
        Map<LocalDate, Map<String, Long>> distribution = new HashMap<>();
        for (Object[] result : results) {
            LocalDate date = ((LocalDateTime) result[0]).toLocalDate();
            String type = (String) result[1];
            Long count = (Long) result[2];
            distribution.computeIfAbsent(date, k -> new HashMap<>()).put(type, count);
        }
        return distribution;
    }

    private Business convertToEntity(BusinessDTO dto) {
        Business business = new Business();
        business.setCustomerId(dto.getCustomerId());
        business.setType(dto.getType());
        business.setStatus(dto.getStatus());
        business.setStage(dto.getStage());
        business.setStartDate(dto.getStartDate());
        business.setExpectedEndDate(dto.getExpectedEndDate());
        business.setActualEndDate(dto.getActualEndDate());
        business.setDescription(dto.getDescription());
        business.setRemark(dto.getRemark());
        business.setPriority(dto.getPriority());
        business.setDifficulty(dto.getDifficulty());
        business.setValue(dto.getValue());
        business.setProgress(dto.getProgress());
        business.setCost(dto.getCost());
        business.setRevenue(dto.getRevenue());
        business.setDeleted(false);
        return business;
    }

    private BusinessDTO convertToDTO(Business business) {
        BusinessDTO dto = new BusinessDTO();
        dto.setId(business.getId());
        dto.setCustomerId(business.getCustomerId());
        dto.setType(business.getType());
        dto.setStatus(business.getStatus());
        dto.setStage(business.getStage());
        dto.setStartDate(business.getStartDate());
        dto.setExpectedEndDate(business.getExpectedEndDate());
        dto.setActualEndDate(business.getActualEndDate());
        dto.setDescription(business.getDescription());
        dto.setRemark(business.getRemark());
        dto.setPriority(business.getPriority());
        dto.setDifficulty(business.getDifficulty());
        dto.setValue(business.getValue());
        dto.setProgress(business.getProgress());
        dto.setCost(business.getCost());
        dto.setRevenue(business.getRevenue());
        dto.setCreatedTime(business.getCreatedAt());
        dto.setUpdatedTime(business.getUpdatedAt());
        return dto;
    }

    private void updateBusinessFields(Business business, BusinessDTO dto) {
        if (dto.getCustomerId() != null) business.setCustomerId(dto.getCustomerId());
        if (dto.getType() != null) business.setType(dto.getType());
        if (dto.getStatus() != null) business.setStatus(dto.getStatus());
        if (dto.getStage() != null) business.setStage(dto.getStage());
        if (dto.getStartDate() != null) business.setStartDate(dto.getStartDate());
        if (dto.getExpectedEndDate() != null) business.setExpectedEndDate(dto.getExpectedEndDate());
        if (dto.getActualEndDate() != null) business.setActualEndDate(dto.getActualEndDate());
        if (dto.getDescription() != null) business.setDescription(dto.getDescription());
        if (dto.getRemark() != null) business.setRemark(dto.getRemark());
        if (dto.getPriority() != null) business.setPriority(dto.getPriority());
        if (dto.getDifficulty() != null) business.setDifficulty(dto.getDifficulty());
        if (dto.getValue() != null) business.setValue(dto.getValue());
        if (dto.getProgress() != null) business.setProgress(dto.getProgress());
        if (dto.getCost() != null) business.setCost(dto.getCost());
        if (dto.getRevenue() != null) business.setRevenue(dto.getRevenue());
    }
} 
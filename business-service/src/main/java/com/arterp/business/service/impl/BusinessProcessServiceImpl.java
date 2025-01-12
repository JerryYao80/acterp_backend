package com.arterp.business.service.impl;

import com.arterp.business.dto.BusinessProcessDTO;
import com.arterp.business.entity.BusinessProcess;
import com.arterp.business.repository.BusinessProcessRepository;
import com.arterp.business.service.BusinessProcessService;
import com.arterp.common.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
public class BusinessProcessServiceImpl implements BusinessProcessService {
    private final BusinessProcessRepository businessProcessRepository;

    @Override
    @Transactional
    public Page<BusinessProcessDTO> getProcessesByCustomerId(Long customerId, String search, Pageable pageable) {
        return businessProcessRepository.findByCustomerIdAndDeletedFalse(customerId, pageable)
                .map(this::convertToDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public BusinessProcessDTO getProcessById(Long id) {
        return convertToDTO(findProcessById(id));
    }

    @Override
    @Transactional
    public BusinessProcessDTO createProcess(BusinessProcessDTO processDTO) {
        BusinessProcess process = convertToEntity(processDTO);
        return convertToDTO(businessProcessRepository.save(process));
    }

    @Override
    @Transactional
    public BusinessProcessDTO updateProcess(Long id, BusinessProcessDTO processDTO) {
        BusinessProcess existingProcess = findProcessById(id);
        updateProcessFields(existingProcess, processDTO);
        return convertToDTO(businessProcessRepository.save(existingProcess));
    }

    @Override
    @Transactional
    public void deleteProcess(Long id) {
        BusinessProcess process = findProcessById(id);
        process.setDeleted(true);
        businessProcessRepository.save(process);
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Long> getProcessTypeDistribution() {
        List<Object[]> results = businessProcessRepository.countByProcessType();
        Map<String, Long> distribution = new HashMap<>();
        for (Object[] result : results) {
            distribution.put((String) result[0], (Long) result[1]);
        }
        return distribution;
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Long> getStatusDistribution() {
        List<Object[]> results = businessProcessRepository.countByStatus();
        Map<String, Long> distribution = new HashMap<>();
        for (Object[] result : results) {
            distribution.put((String) result[0], (Long) result[1]);
        }
        return distribution;
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> getBusinessStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalProcesses", businessProcessRepository.countByDeletedFalse());
        stats.put("typeDistribution", getProcessTypeDistribution());
        stats.put("statusDistribution", getStatusDistribution());
        return stats;
    }

    @Override
    @Transactional
    public BusinessProcessDTO updateBusinessStage(Long id, String stage) {
        BusinessProcess process = findProcessById(id);
        process.setStage(stage);
        return convertToDTO(businessProcessRepository.save(process));
    }

    @Override
    @Transactional
    public BusinessProcessDTO updateBusinessStatus(Long id, String status) {
        BusinessProcess process = findProcessById(id);
        process.setStatus(status);
        return convertToDTO(businessProcessRepository.save(process));
    }

    @Override
    @Transactional
    public BusinessProcessDTO addCheckupRecord(Long id, String record) {
        BusinessProcess process = findProcessById(id);
        if (process.getCheckupRecords() == null) {
            process.setCheckupRecords(new ArrayList<>());
        }
        process.getCheckupRecords().add(record);
        return convertToDTO(businessProcessRepository.save(process));
    }

    @Override
    @Transactional
    public BusinessProcessDTO addNotification(Long id, String notification) {
        BusinessProcess process = findProcessById(id);
        if (process.getNotifications() == null) {
            process.setNotifications(new ArrayList<>());
        }
        process.getNotifications().add(notification);
        return convertToDTO(businessProcessRepository.save(process));
    }

    @Override
    @Transactional
    public BusinessProcessDTO addSocialMediaRecord(Long id, String record) {
        BusinessProcess process = findProcessById(id);
        if (process.getSocialMediaRecords() == null) {
            process.setSocialMediaRecords(new ArrayList<>());
        }
        process.getSocialMediaRecords().add(record);
        return convertToDTO(businessProcessRepository.save(process));
    }

    @Override
    @Transactional
    public BusinessProcessDTO updateIVFRecord(Long id, String record) {
        BusinessProcess process = findProcessById(id);
        process.setIvfRecord(record);
        return convertToDTO(businessProcessRepository.save(process));
    }

    @Override
    @Transactional
    public BusinessProcessDTO updateEmbryoTransferRecord(Long id, String record) {
        BusinessProcess process = findProcessById(id);
        process.setEmbryoTransferRecord(record);
        return convertToDTO(businessProcessRepository.save(process));
    }

    @Override
    @Transactional
    public BusinessProcessDTO updatePregnancyCareRecord(Long id, String record) {
        BusinessProcess process = findProcessById(id);
        process.setPregnancyCareRecord(record);
        return convertToDTO(businessProcessRepository.save(process));
    }

    @Override
    @Transactional
    public BusinessProcessDTO updateDeliveryRecord(Long id, String record) {
        BusinessProcess process = findProcessById(id);
        process.setDeliveryRecord(record);
        return convertToDTO(businessProcessRepository.save(process));
    }

    @Override
    @Transactional
    public BusinessProcessDTO updateEntryServiceRecord(Long id, String record) {
        BusinessProcess process = findProcessById(id);
        process.setEntryServiceRecord(record);
        return convertToDTO(businessProcessRepository.save(process));
    }

    @Override
    @Transactional
    public BusinessProcessDTO updateSettlementServiceRecord(Long id, String record) {
        BusinessProcess process = findProcessById(id);
        process.setSettlementServiceRecord(record);
        return convertToDTO(businessProcessRepository.save(process));
    }

    @Override
    @Transactional
    public BusinessProcessDTO updateTimeline(Long id, LocalDateTime startTime, LocalDateTime expectedEndTime) {
        BusinessProcess process = findProcessById(id);
        process.setStartDate(startTime);
        process.setExpectedEndDate(expectedEndTime);
        return convertToDTO(businessProcessRepository.save(process));
    }

    @Override
    @Transactional
    public BusinessProcessDTO completeBusiness(Long id, LocalDateTime actualEndTime) {
        BusinessProcess process = findProcessById(id);
        process.setActualEndDate(actualEndTime);
        process.setStatus("COMPLETED");
        return convertToDTO(businessProcessRepository.save(process));
    }

    @Override
    @Transactional(readOnly = true)
    public List<BusinessProcessDTO> getUpcomingDeadlines(LocalDateTime date) {
        return businessProcessRepository.findByExpectedEndDateBeforeAndStatusNotAndDeletedFalse(date, "COMPLETED")
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Long countBusinessesInPeriod(LocalDateTime startDate, LocalDateTime endDate) {
        return businessProcessRepository.countByStartDateBetweenAndDeletedFalse(startDate, endDate);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Map<String, Object>> getBusinessTrend(LocalDateTime startDate, LocalDateTime endDate) {
        // TODO: Implement business trend analysis
        return new ArrayList<>();
    }

    @Override
    @Transactional(readOnly = true)
    public Map<LocalDate, Map<String, Long>> getProcessTypeDistributionByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        List<Object[]> results = businessProcessRepository.countByStartDateGroupByType(startDate, endDate);
        Map<LocalDate, Map<String, Long>> distribution = new HashMap<>();
        
        for (Object[] result : results) {
            LocalDate date = ((java.sql.Date) result[0]).toLocalDate();
            String type = (String) result[1];
            Long count = (Long) result[2];
            
            distribution.computeIfAbsent(date, k -> new HashMap<>())
                       .put(type, count);
        }
        
        return distribution;
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Long> getStageDistribution() {
        List<Object[]> results = businessProcessRepository.countByStage();
        Map<String, Long> distribution = new HashMap<>();
        for (Object[] result : results) {
            distribution.put((String) result[0], (Long) result[1]);
        }
        return distribution;
    }

    private BusinessProcess findProcessById(Long id) {
        return businessProcessRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new BusinessException("Business process not found with id: " + id));
    }

    private BusinessProcessDTO convertToDTO(BusinessProcess process) {
        BusinessProcessDTO dto = new BusinessProcessDTO();
        dto.setId(process.getId());
        dto.setCustomerId(process.getCustomerId());
        dto.setProcessType(process.getProcessType());
        dto.setStatus(process.getStatus());
        dto.setStage(process.getStage());
        dto.setStartDate(process.getStartDate());
        dto.setExpectedEndDate(process.getExpectedEndDate());
        dto.setActualEndDate(process.getActualEndDate());
        dto.setCheckupRecords(process.getCheckupRecords() != null ? new ArrayList<>(process.getCheckupRecords()) : new ArrayList<>());
        dto.setNotifications(process.getNotifications() != null ? new ArrayList<>(process.getNotifications()) : new ArrayList<>());
        dto.setSocialMediaRecords(process.getSocialMediaRecords() != null ? new ArrayList<>(process.getSocialMediaRecords()) : new ArrayList<>());
        dto.setIvfRecord(process.getIvfRecord());
        dto.setEmbryoTransferRecord(process.getEmbryoTransferRecord());
        dto.setPregnancyCareRecord(process.getPregnancyCareRecord());
        dto.setDeliveryRecord(process.getDeliveryRecord());
        dto.setEntryServiceRecord(process.getEntryServiceRecord());
        dto.setSettlementServiceRecord(process.getSettlementServiceRecord());
        return dto;
    }

    private BusinessProcess convertToEntity(BusinessProcessDTO dto) {
        BusinessProcess process = new BusinessProcess();
        process.setCustomerId(dto.getCustomerId());
        process.setProcessType(dto.getProcessType());
        process.setStatus(dto.getStatus());
        process.setStage(dto.getStage());
        process.setStartDate(dto.getStartDate());
        process.setExpectedEndDate(dto.getExpectedEndDate());
        process.setActualEndDate(dto.getActualEndDate());
        process.setCheckupRecords(dto.getCheckupRecords() != null ? new ArrayList<>(dto.getCheckupRecords()) : new ArrayList<>());
        process.setNotifications(dto.getNotifications() != null ? new ArrayList<>(dto.getNotifications()) : new ArrayList<>());
        process.setSocialMediaRecords(dto.getSocialMediaRecords() != null ? new ArrayList<>(dto.getSocialMediaRecords()) : new ArrayList<>());
        process.setIvfRecord(dto.getIvfRecord());
        process.setEmbryoTransferRecord(dto.getEmbryoTransferRecord());
        process.setPregnancyCareRecord(dto.getPregnancyCareRecord());
        process.setDeliveryRecord(dto.getDeliveryRecord());
        process.setEntryServiceRecord(dto.getEntryServiceRecord());
        process.setSettlementServiceRecord(dto.getSettlementServiceRecord());
        return process;
    }

    private void updateProcessFields(BusinessProcess process, BusinessProcessDTO dto) {
        process.setCustomerId(dto.getCustomerId());
        process.setProcessType(dto.getProcessType());
        process.setStatus(dto.getStatus());
        process.setStage(dto.getStage());
        process.setStartDate(dto.getStartDate());
        process.setExpectedEndDate(dto.getExpectedEndDate());
        process.setActualEndDate(dto.getActualEndDate());
        process.setCheckupRecords(dto.getCheckupRecords() != null ? new ArrayList<>(dto.getCheckupRecords()) : new ArrayList<>());
        process.setNotifications(dto.getNotifications() != null ? new ArrayList<>(dto.getNotifications()) : new ArrayList<>());
        process.setSocialMediaRecords(dto.getSocialMediaRecords() != null ? new ArrayList<>(dto.getSocialMediaRecords()) : new ArrayList<>());
        process.setIvfRecord(dto.getIvfRecord());
        process.setEmbryoTransferRecord(dto.getEmbryoTransferRecord());
        process.setPregnancyCareRecord(dto.getPregnancyCareRecord());
        process.setDeliveryRecord(dto.getDeliveryRecord());
        process.setEntryServiceRecord(dto.getEntryServiceRecord());
        process.setSettlementServiceRecord(dto.getSettlementServiceRecord());
    }
} 
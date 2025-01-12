package com.arterp.business.service;

import com.arterp.business.dto.BusinessProcessDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface BusinessProcessService {
    // Basic CRUD operations
    Page<BusinessProcessDTO> getProcessesByCustomerId(Long customerId, String search, Pageable pageable);
    BusinessProcessDTO getProcessById(Long id);
    BusinessProcessDTO createProcess(BusinessProcessDTO processDTO);
    BusinessProcessDTO updateProcess(Long id, BusinessProcessDTO processDTO);
    void deleteProcess(Long id);

    // Statistics and distributions
    Map<String, Long> getStageDistribution();
    Map<String, Long> getStatusDistribution();
    Map<String, Long> getProcessTypeDistribution();
    Map<LocalDate, Map<String, Long>> getProcessTypeDistributionByDateRange(LocalDateTime startDate, LocalDateTime endDate);
    Map<String, Object> getBusinessStats();

    // Business process management
    BusinessProcessDTO updateBusinessStage(Long id, String stage);
    BusinessProcessDTO updateBusinessStatus(Long id, String status);

    // Record management
    BusinessProcessDTO addCheckupRecord(Long id, String record);
    BusinessProcessDTO addNotification(Long id, String notification);
    BusinessProcessDTO addSocialMediaRecord(Long id, String record);

    // Medical service records
    BusinessProcessDTO updateIVFRecord(Long id, String record);
    BusinessProcessDTO updateEmbryoTransferRecord(Long id, String record);
    BusinessProcessDTO updatePregnancyCareRecord(Long id, String record);
    BusinessProcessDTO updateDeliveryRecord(Long id, String record);

    // Support service records
    BusinessProcessDTO updateEntryServiceRecord(Long id, String record);
    BusinessProcessDTO updateSettlementServiceRecord(Long id, String record);

    // Timeline management
    BusinessProcessDTO updateTimeline(Long id, LocalDateTime startTime, LocalDateTime expectedEndTime);
    BusinessProcessDTO completeBusiness(Long id, LocalDateTime actualEndTime);
    List<BusinessProcessDTO> getUpcomingDeadlines(LocalDateTime date);
    Long countBusinessesInPeriod(LocalDateTime startDate, LocalDateTime endDate);
    List<Map<String, Object>> getBusinessTrend(LocalDateTime startDate, LocalDateTime endDate);
} 
package com.arterp.business.service;

import com.arterp.business.dto.BusinessDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface BusinessService {
    BusinessDTO createBusiness(BusinessDTO businessDTO);
    List<BusinessDTO> getAllBusinesses();
    BusinessDTO getBusinessById(Long id);
    BusinessDTO updateBusiness(Long id, BusinessDTO businessDTO);
    void deleteBusiness(Long id);

    List<BusinessDTO> getBusinessesByCustomerId(Long customerId);
    List<BusinessDTO> getBusinessesByType(String type);
    List<BusinessDTO> getBusinessesByStage(String stage);
    List<BusinessDTO> getBusinessesByStatus(String status);

    BusinessDTO updateBusinessType(Long id, String type);
    BusinessDTO updateBusinessStage(Long id, String stage);
    BusinessDTO updateBusinessStatus(Long id, String status);

    BusinessDTO updateBusinessStartDate(Long id, LocalDateTime startDate);
    BusinessDTO updateBusinessExpectedEndDate(Long id, LocalDateTime expectedEndDate);
    BusinessDTO updateBusinessActualEndDate(Long id, LocalDateTime actualEndDate);

    BusinessDTO updateBusinessCustomerId(Long id, Long customerId);
    BusinessDTO updateBusinessDescription(Long id, String description);
    BusinessDTO updateBusinessRemark(Long id, String remark);

    BusinessDTO updateBusinessPriority(Long id, Integer priority);
    BusinessDTO updateBusinessDifficulty(Long id, Integer difficulty);
    BusinessDTO updateBusinessValue(Long id, Integer value);

    BusinessDTO updateBusinessProgress(Long id, Integer progress);
    BusinessDTO updateBusinessCost(Long id, Double cost);
    BusinessDTO updateBusinessRevenue(Long id, Double revenue);

    List<BusinessDTO> getOverdueBusinesses(LocalDateTime date);

    Map<String, Long> getTypeDistribution();
    Map<String, Long> getStageDistribution();
    Map<String, Long> getStatusDistribution();
    Map<LocalDate, Map<String, Long>> getProcessTypeDistributionByDateRange(LocalDateTime startDate, LocalDateTime endDate);
} 
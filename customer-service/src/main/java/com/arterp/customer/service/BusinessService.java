package com.arterp.customer.service;

import com.arterp.customer.dto.BusinessDTO;
import com.arterp.customer.entity.enums.BusinessPhase;
import com.arterp.customer.entity.enums.BusinessStatus;

import java.util.List;
import java.util.Map;

public interface BusinessService {
    BusinessDTO createBusiness(BusinessDTO businessDTO);
    
    BusinessDTO updateBusiness(Long id, BusinessDTO businessDTO);
    
    BusinessDTO getBusiness(Long id);
    
    List<BusinessDTO> getBusinessesByCustomer(Long customerId);
    
    List<BusinessDTO> getBusinessesByPhase(BusinessPhase phase);
    
    List<BusinessDTO> getBusinessesByStatus(BusinessStatus status);
    
    void deleteBusiness(Long id);
    
    // 统计相关
    Map<BusinessPhase, Long> countByPhase();
    
    Map<String, Long> countByLocation();
    
    Map<String, Long> countByType();
} 
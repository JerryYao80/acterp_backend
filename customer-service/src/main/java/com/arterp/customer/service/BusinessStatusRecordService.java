package com.arterp.customer.service;

import com.arterp.customer.dto.BusinessStatusRecordDTO;
import java.util.List;

public interface BusinessStatusRecordService {
    BusinessStatusRecordDTO createStatusRecord(BusinessStatusRecordDTO recordDTO);
    
    List<BusinessStatusRecordDTO> getStatusRecordsByBusinessId(Long businessId);
    
    List<BusinessStatusRecordDTO> getAbnormalRecordsByBusinessId(Long businessId);
    
    BusinessStatusRecordDTO updateStatusRecord(Long id, BusinessStatusRecordDTO recordDTO);
    
    void deleteStatusRecord(Long id);
} 
package com.arterp.customer.service;

import com.arterp.customer.dto.MarketingRecordDTO;
import java.util.List;
import java.util.Map;

public interface MarketingRecordService {
    MarketingRecordDTO createMarketingRecord(MarketingRecordDTO recordDTO);
    
    List<MarketingRecordDTO> getMarketingRecordsByBusinessId(Long businessId);
    
    MarketingRecordDTO updateMarketingRecord(Long id, MarketingRecordDTO recordDTO);
    
    void deleteMarketingRecord(Long id);
    
    Map<String, Long> countByChannel();
    
    Map<String, Long> countByConversionSource();
} 
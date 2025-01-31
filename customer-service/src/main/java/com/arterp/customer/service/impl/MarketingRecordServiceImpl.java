package com.arterp.customer.service.impl;

import com.arterp.customer.dto.MarketingRecordDTO;
import com.arterp.customer.entity.Business;
import com.arterp.customer.entity.Customer;
import com.arterp.customer.entity.MarketingRecord;
import com.arterp.customer.repository.BusinessRepository;
import com.arterp.customer.repository.CustomerRepository;
import com.arterp.customer.repository.MarketingRecordRepository;
import com.arterp.customer.service.MarketingRecordService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MarketingRecordServiceImpl implements MarketingRecordService {
    private final MarketingRecordRepository marketingRecordRepository;
    private final BusinessRepository businessRepository;
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public MarketingRecordDTO createMarketingRecord(MarketingRecordDTO recordDTO) {
        MarketingRecord record = modelMapper.map(recordDTO, MarketingRecord.class);
        
        Business business = businessRepository.findById(recordDTO.getBusinessId())
                .orElseThrow(() -> new RuntimeException("Business not found"));
        record.setBusiness(business);
        
        if (recordDTO.getReferralCustomerId() != null) {
            Customer referralCustomer = customerRepository.findById(recordDTO.getReferralCustomerId())
                    .orElseThrow(() -> new RuntimeException("Referral customer not found"));
            record.setReferralCustomer(referralCustomer);
        }
        
        record = marketingRecordRepository.save(record);
        return modelMapper.map(record, MarketingRecordDTO.class);
    }

    @Override
    public List<MarketingRecordDTO> getMarketingRecordsByBusinessId(Long businessId) {
        return marketingRecordRepository.findByBusinessId(businessId).stream()
                .map(record -> modelMapper.map(record, MarketingRecordDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public MarketingRecordDTO updateMarketingRecord(Long id, MarketingRecordDTO recordDTO) {
        MarketingRecord record = marketingRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Marketing record not found"));
        
        modelMapper.map(recordDTO, record);
        
        if (recordDTO.getReferralCustomerId() != null) {
            Customer referralCustomer = customerRepository.findById(recordDTO.getReferralCustomerId())
                    .orElseThrow(() -> new RuntimeException("Referral customer not found"));
            record.setReferralCustomer(referralCustomer);
        }
        
        record = marketingRecordRepository.save(record);
        return modelMapper.map(record, MarketingRecordDTO.class);
    }

    @Override
    @Transactional
    public void deleteMarketingRecord(Long id) {
        marketingRecordRepository.deleteById(id);
    }

    @Override
    public Map<String, Long> countByChannel() {
        return marketingRecordRepository.countByChannel().stream()
                .collect(Collectors.toMap(
                        row -> (String) row[0],
                        row -> (Long) row[1]
                ));
    }

    @Override
    public Map<String, Long> countByConversionSource() {
        return marketingRecordRepository.countByConversionSource().stream()
                .collect(Collectors.toMap(
                        row -> (String) row[0],
                        row -> (Long) row[1]
                ));
    }
} 
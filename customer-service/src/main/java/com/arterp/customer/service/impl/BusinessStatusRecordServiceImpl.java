package com.arterp.customer.service.impl;

import com.arterp.customer.dto.BusinessStatusRecordDTO;
import com.arterp.customer.entity.Business;
import com.arterp.customer.entity.BusinessStatusRecord;
import com.arterp.customer.repository.BusinessRepository;
import com.arterp.customer.repository.BusinessStatusRecordRepository;
import com.arterp.customer.service.BusinessStatusRecordService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BusinessStatusRecordServiceImpl implements BusinessStatusRecordService {
    private final BusinessStatusRecordRepository statusRecordRepository;
    private final BusinessRepository businessRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public BusinessStatusRecordDTO createStatusRecord(BusinessStatusRecordDTO recordDTO) {
        BusinessStatusRecord record = modelMapper.map(recordDTO, BusinessStatusRecord.class);
        Business business = businessRepository.findById(recordDTO.getBusinessId())
                .orElseThrow(() -> new RuntimeException("Business not found"));
        record.setBusiness(business);
        record = statusRecordRepository.save(record);
        return modelMapper.map(record, BusinessStatusRecordDTO.class);
    }

    @Override
    public List<BusinessStatusRecordDTO> getStatusRecordsByBusinessId(Long businessId) {
        return statusRecordRepository.findByBusinessId(businessId).stream()
                .map(record -> modelMapper.map(record, BusinessStatusRecordDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<BusinessStatusRecordDTO> getAbnormalRecordsByBusinessId(Long businessId) {
        return statusRecordRepository.findByBusinessIdAndIsAbnormalTrue(businessId).stream()
                .map(record -> modelMapper.map(record, BusinessStatusRecordDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public BusinessStatusRecordDTO updateStatusRecord(Long id, BusinessStatusRecordDTO recordDTO) {
        BusinessStatusRecord record = statusRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Status record not found"));
        modelMapper.map(recordDTO, record);
        record = statusRecordRepository.save(record);
        return modelMapper.map(record, BusinessStatusRecordDTO.class);
    }

    @Override
    @Transactional
    public void deleteStatusRecord(Long id) {
        statusRecordRepository.deleteById(id);
    }
} 
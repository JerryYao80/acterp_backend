package com.arterp.customer.service.impl;

import com.arterp.customer.dto.MedicalRecordDTO;
import com.arterp.customer.entity.Business;
import com.arterp.customer.entity.MedicalRecord;
import com.arterp.customer.repository.BusinessRepository;
import com.arterp.customer.repository.MedicalRecordRepository;
import com.arterp.customer.service.MedicalRecordService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MedicalRecordServiceImpl implements MedicalRecordService {
    private final MedicalRecordRepository medicalRecordRepository;
    private final BusinessRepository businessRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public MedicalRecordDTO createMedicalRecord(MedicalRecordDTO recordDTO) {
        MedicalRecord record = modelMapper.map(recordDTO, MedicalRecord.class);
        Business business = businessRepository.findById(recordDTO.getBusinessId())
                .orElseThrow(() -> new RuntimeException("Business not found"));
        record.setBusiness(business);
        record = medicalRecordRepository.save(record);
        return modelMapper.map(record, MedicalRecordDTO.class);
    }

    @Override
    public List<MedicalRecordDTO> getMedicalRecordsByBusinessId(Long businessId) {
        return medicalRecordRepository.findByBusinessId(businessId).stream()
                .map(record -> modelMapper.map(record, MedicalRecordDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<MedicalRecordDTO> getMedicalRecordsByBusinessIdAndType(Long businessId, String recordType) {
        return medicalRecordRepository.findByBusinessIdAndRecordType(businessId, recordType).stream()
                .map(record -> modelMapper.map(record, MedicalRecordDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<MedicalRecordDTO> getAbnormalMedicalRecords(Long businessId) {
        return medicalRecordRepository.findByBusinessIdAndAbnormalFlagsIsNotEmpty(businessId).stream()
                .map(record -> modelMapper.map(record, MedicalRecordDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public MedicalRecordDTO updateMedicalRecord(Long id, MedicalRecordDTO recordDTO) {
        MedicalRecord record = medicalRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medical record not found"));
        modelMapper.map(recordDTO, record);
        record = medicalRecordRepository.save(record);
        return modelMapper.map(record, MedicalRecordDTO.class);
    }

    @Override
    @Transactional
    public void deleteMedicalRecord(Long id) {
        medicalRecordRepository.deleteById(id);
    }
} 
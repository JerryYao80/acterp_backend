package com.arterp.customer.service;

import com.arterp.customer.dto.MedicalRecordDTO;
import java.util.List;

public interface MedicalRecordService {
    MedicalRecordDTO createMedicalRecord(MedicalRecordDTO recordDTO);
    
    List<MedicalRecordDTO> getMedicalRecordsByBusinessId(Long businessId);
    
    List<MedicalRecordDTO> getMedicalRecordsByBusinessIdAndType(Long businessId, String recordType);
    
    List<MedicalRecordDTO> getAbnormalMedicalRecords(Long businessId);
    
    MedicalRecordDTO updateMedicalRecord(Long id, MedicalRecordDTO recordDTO);
    
    void deleteMedicalRecord(Long id);
} 
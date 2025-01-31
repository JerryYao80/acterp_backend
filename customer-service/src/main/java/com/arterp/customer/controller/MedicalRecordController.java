package com.arterp.customer.controller;

import com.arterp.customer.dto.MedicalRecordDTO;
import com.arterp.customer.service.MedicalRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medical-records")
@RequiredArgsConstructor
public class MedicalRecordController {
    private final MedicalRecordService medicalRecordService;

    @PostMapping
    public ResponseEntity<MedicalRecordDTO> createMedicalRecord(@RequestBody MedicalRecordDTO recordDTO) {
        return ResponseEntity.ok(medicalRecordService.createMedicalRecord(recordDTO));
    }

    @GetMapping("/business/{businessId}")
    public ResponseEntity<List<MedicalRecordDTO>> getMedicalRecordsByBusinessId(@PathVariable Long businessId) {
        return ResponseEntity.ok(medicalRecordService.getMedicalRecordsByBusinessId(businessId));
    }

    @GetMapping("/business/{businessId}/type/{recordType}")
    public ResponseEntity<List<MedicalRecordDTO>> getMedicalRecordsByBusinessIdAndType(
            @PathVariable Long businessId,
            @PathVariable String recordType) {
        return ResponseEntity.ok(medicalRecordService.getMedicalRecordsByBusinessIdAndType(businessId, recordType));
    }

    @GetMapping("/business/{businessId}/abnormal")
    public ResponseEntity<List<MedicalRecordDTO>> getAbnormalMedicalRecords(@PathVariable Long businessId) {
        return ResponseEntity.ok(medicalRecordService.getAbnormalMedicalRecords(businessId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicalRecordDTO> updateMedicalRecord(
            @PathVariable Long id,
            @RequestBody MedicalRecordDTO recordDTO) {
        return ResponseEntity.ok(medicalRecordService.updateMedicalRecord(id, recordDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedicalRecord(@PathVariable Long id) {
        medicalRecordService.deleteMedicalRecord(id);
        return ResponseEntity.ok().build();
    }
} 
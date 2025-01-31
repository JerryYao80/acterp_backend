package com.arterp.customer.controller;

import com.arterp.customer.dto.BusinessStatusRecordDTO;
import com.arterp.customer.service.BusinessStatusRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/business-status-records")
@RequiredArgsConstructor
public class BusinessStatusRecordController {
    private final BusinessStatusRecordService statusRecordService;

    @PostMapping
    public ResponseEntity<BusinessStatusRecordDTO> createStatusRecord(@RequestBody BusinessStatusRecordDTO recordDTO) {
        return ResponseEntity.ok(statusRecordService.createStatusRecord(recordDTO));
    }

    @GetMapping("/business/{businessId}")
    public ResponseEntity<List<BusinessStatusRecordDTO>> getStatusRecordsByBusinessId(@PathVariable Long businessId) {
        return ResponseEntity.ok(statusRecordService.getStatusRecordsByBusinessId(businessId));
    }

    @GetMapping("/business/{businessId}/abnormal")
    public ResponseEntity<List<BusinessStatusRecordDTO>> getAbnormalRecordsByBusinessId(@PathVariable Long businessId) {
        return ResponseEntity.ok(statusRecordService.getAbnormalRecordsByBusinessId(businessId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BusinessStatusRecordDTO> updateStatusRecord(
            @PathVariable Long id,
            @RequestBody BusinessStatusRecordDTO recordDTO) {
        return ResponseEntity.ok(statusRecordService.updateStatusRecord(id, recordDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStatusRecord(@PathVariable Long id) {
        statusRecordService.deleteStatusRecord(id);
        return ResponseEntity.ok().build();
    }
} 
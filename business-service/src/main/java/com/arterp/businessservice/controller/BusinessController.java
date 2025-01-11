package com.arterp.businessservice.controller;

import com.arterp.businessservice.dto.BusinessDTO;
import com.arterp.businessservice.service.BusinessService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/businesses")
@RequiredArgsConstructor
public class BusinessController {

    private final BusinessService businessService;

    // 基础CRUD操作
    @PostMapping
    public ResponseEntity<BusinessDTO> createBusiness(@RequestBody BusinessDTO businessDTO) {
        return ResponseEntity.ok(businessService.createBusiness(businessDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BusinessDTO> updateBusiness(
            @PathVariable Long id,
            @RequestBody BusinessDTO businessDTO) {
        return ResponseEntity.ok(businessService.updateBusiness(id, businessDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBusiness(@PathVariable Long id) {
        businessService.deleteBusiness(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BusinessDTO> getBusiness(@PathVariable Long id) {
        return ResponseEntity.ok(businessService.getBusiness(id));
    }

    @GetMapping
    public ResponseEntity<List<BusinessDTO>> getAllBusinesses() {
        return ResponseEntity.ok(businessService.getAllBusinesses());
    }

    // 业务总览
    @GetMapping("/overview")
    public ResponseEntity<Map<String, Object>> getBusinessOverview() {
        return ResponseEntity.ok(businessService.getBusinessOverview());
    }

    @GetMapping("/type-distribution")
    public ResponseEntity<List<Map<String, Object>>> getBusinessTypeDistribution() {
        return ResponseEntity.ok(businessService.getBusinessTypeDistribution());
    }

    @GetMapping("/stage-distribution")
    public ResponseEntity<List<Map<String, Object>>> getBusinessStageDistribution() {
        return ResponseEntity.ok(businessService.getBusinessStageDistribution());
    }

    @GetMapping("/status-distribution")
    public ResponseEntity<List<Map<String, Object>>> getBusinessStatusDistribution() {
        return ResponseEntity.ok(businessService.getBusinessStatusDistribution());
    }

    // 业务筛选
    @GetMapping("/by-customer/{customerId}")
    public ResponseEntity<List<BusinessDTO>> getBusinessesByCustomer(@PathVariable Long customerId) {
        return ResponseEntity.ok(businessService.getBusinessesByCustomer(customerId));
    }

    @GetMapping("/by-type")
    public ResponseEntity<List<BusinessDTO>> getBusinessesByType(@RequestParam String type) {
        return ResponseEntity.ok(businessService.getBusinessesByType(type));
    }

    @GetMapping("/by-stage")
    public ResponseEntity<List<BusinessDTO>> getBusinessesByStage(@RequestParam String stage) {
        return ResponseEntity.ok(businessService.getBusinessesByStage(stage));
    }

    @GetMapping("/by-status")
    public ResponseEntity<List<BusinessDTO>> getBusinessesByStatus(@RequestParam String status) {
        return ResponseEntity.ok(businessService.getBusinessesByStatus(status));
    }

    // 业务流程管理
    @PutMapping("/{id}/stage")
    public ResponseEntity<BusinessDTO> updateBusinessStage(
            @PathVariable Long id,
            @RequestParam String stage) {
        return ResponseEntity.ok(businessService.updateBusinessStage(id, stage));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<BusinessDTO> updateBusinessStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        return ResponseEntity.ok(businessService.updateBusinessStatus(id, status));
    }

    // 记录管理
    @PostMapping("/{id}/checkup-records")
    public ResponseEntity<BusinessDTO> addCheckupRecord(
            @PathVariable Long id,
            @RequestBody String record) {
        return ResponseEntity.ok(businessService.addCheckupRecord(id, record));
    }

    @PostMapping("/{id}/notifications")
    public ResponseEntity<BusinessDTO> addNotification(
            @PathVariable Long id,
            @RequestBody String notification) {
        return ResponseEntity.ok(businessService.addNotification(id, notification));
    }

    @PostMapping("/{id}/social-media-records")
    public ResponseEntity<BusinessDTO> addSocialMediaRecord(
            @PathVariable Long id,
            @RequestBody String record) {
        return ResponseEntity.ok(businessService.addSocialMediaRecord(id, record));
    }

    // 医疗服务记录
    @PutMapping("/{id}/ivf-record")
    public ResponseEntity<BusinessDTO> updateIVFRecord(
            @PathVariable Long id,
            @RequestBody String record) {
        return ResponseEntity.ok(businessService.updateIVFRecord(id, record));
    }

    @PutMapping("/{id}/embryo-transfer-record")
    public ResponseEntity<BusinessDTO> updateEmbryoTransferRecord(
            @PathVariable Long id,
            @RequestBody String record) {
        return ResponseEntity.ok(businessService.updateEmbryoTransferRecord(id, record));
    }

    @PutMapping("/{id}/pregnancy-care-record")
    public ResponseEntity<BusinessDTO> updatePregnancyCareRecord(
            @PathVariable Long id,
            @RequestBody String record) {
        return ResponseEntity.ok(businessService.updatePregnancyCareRecord(id, record));
    }

    @PutMapping("/{id}/delivery-record")
    public ResponseEntity<BusinessDTO> updateDeliveryRecord(
            @PathVariable Long id,
            @RequestBody String record) {
        return ResponseEntity.ok(businessService.updateDeliveryRecord(id, record));
    }

    // 辅助服务记录
    @PutMapping("/{id}/entry-service-record")
    public ResponseEntity<BusinessDTO> updateEntryServiceRecord(
            @PathVariable Long id,
            @RequestBody String record) {
        return ResponseEntity.ok(businessService.updateEntryServiceRecord(id, record));
    }

    @PutMapping("/{id}/settlement-service-record")
    public ResponseEntity<BusinessDTO> updateSettlementServiceRecord(
            @PathVariable Long id,
            @RequestBody String record) {
        return ResponseEntity.ok(businessService.updateSettlementServiceRecord(id, record));
    }

    // 时间管理
    @PutMapping("/{id}/timeline")
    public ResponseEntity<BusinessDTO> updateTimeline(
            @PathVariable Long id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime expectedEndTime) {
        return ResponseEntity.ok(businessService.updateTimeline(id, startTime, expectedEndTime));
    }

    @PutMapping("/{id}/complete")
    public ResponseEntity<BusinessDTO> completeBusiness(
            @PathVariable Long id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime actualEndTime) {
        return ResponseEntity.ok(businessService.completeBusiness(id, actualEndTime));
    }

    @GetMapping("/upcoming-deadlines")
    public ResponseEntity<List<BusinessDTO>> getUpcomingDeadlines(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        return ResponseEntity.ok(businessService.getUpcomingDeadlines(date));
    }

    // 统计分析
    @GetMapping("/count-in-period")
    public ResponseEntity<Long> countBusinessesInPeriod(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        return ResponseEntity.ok(businessService.countBusinessesInPeriod(startDate, endDate));
    }

    @GetMapping("/trend")
    public ResponseEntity<List<Map<String, Object>>> getBusinessTrend(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        return ResponseEntity.ok(businessService.getBusinessTrend(startDate, endDate));
    }
} 
package com.arterp.business.controller;

import com.arterp.business.dto.BusinessProcessDTO;
import com.arterp.business.service.BusinessProcessService;
import com.arterp.common.dto.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.time.LocalDateTime;
import java.util.List;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

@RestController
@RequestMapping("/api/business")
@RequiredArgsConstructor
@CrossOrigin
public class BusinessProcessController {
    private final BusinessProcessService businessProcessService;

    @GetMapping("/customer/{customerId}")
    @PreAuthorize("hasRole('USER')")
    public BaseResponse<Page<BusinessProcessDTO>> getProcessesByCustomerId(
            @PathVariable Long customerId,
            @RequestParam(required = false) String search,
            Pageable pageable) {
        return BaseResponse.success(businessProcessService.getProcessesByCustomerId(customerId, search, pageable));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public BaseResponse<BusinessProcessDTO> getProcess(@PathVariable Long id) {
        return BaseResponse.success(businessProcessService.getProcessById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public BaseResponse<BusinessProcessDTO> createProcess(@Valid @RequestBody BusinessProcessDTO processDTO) {
        return BaseResponse.success(businessProcessService.createProcess(processDTO));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public BaseResponse<BusinessProcessDTO> updateProcess(
            @PathVariable Long id,
            @Valid @RequestBody BusinessProcessDTO processDTO) {
        return BaseResponse.success(businessProcessService.updateProcess(id, processDTO));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public BaseResponse<Void> deleteProcess(@PathVariable Long id) {
        businessProcessService.deleteProcess(id);
        return BaseResponse.success(null);
    }

    @GetMapping("/stats/status")
    @PreAuthorize("hasRole('USER')")
    public BaseResponse<Map<String, Long>> getStatusDistribution() {
        return BaseResponse.success(businessProcessService.getStatusDistribution());
    }

    @GetMapping("/stats/type")
    @PreAuthorize("hasRole('USER')")
    public BaseResponse<Map<String, Long>> getProcessTypeDistribution() {
        return BaseResponse.success(businessProcessService.getProcessTypeDistribution());
    }

    @GetMapping("/stats/type/date-range")
    @PreAuthorize("hasRole('USER')")
    public BaseResponse<Map<LocalDate, Map<String, Long>>> getProcessTypeDistributionByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        return BaseResponse.success(businessProcessService.getProcessTypeDistributionByDateRange(startDate, endDate));
    }

    @GetMapping("/stats")
    @PreAuthorize("hasRole('USER')")
    public BaseResponse<Map<String, Object>> getBusinessStats() {
        return BaseResponse.success(businessProcessService.getBusinessStats());
    }

    @PutMapping("/{id}/stage")
    @PreAuthorize("hasRole('USER')")
    public BaseResponse<BusinessProcessDTO> updateBusinessStage(
            @PathVariable Long id,
            @RequestParam String stage) {
        return BaseResponse.success(businessProcessService.updateBusinessStage(id, stage));
    }

    @PutMapping("/{id}/status")
    @PreAuthorize("hasRole('USER')")
    public BaseResponse<BusinessProcessDTO> updateBusinessStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        return BaseResponse.success(businessProcessService.updateBusinessStatus(id, status));
    }

    @PostMapping("/{id}/checkup")
    @PreAuthorize("hasRole('USER')")
    public BaseResponse<BusinessProcessDTO> addCheckupRecord(
            @PathVariable Long id,
            @RequestParam String record) {
        return BaseResponse.success(businessProcessService.addCheckupRecord(id, record));
    }

    @PostMapping("/{id}/notification")
    @PreAuthorize("hasRole('USER')")
    public BaseResponse<BusinessProcessDTO> addNotification(
            @PathVariable Long id,
            @RequestParam String notification) {
        return BaseResponse.success(businessProcessService.addNotification(id, notification));
    }

    @PostMapping("/{id}/social-media")
    @PreAuthorize("hasRole('USER')")
    public BaseResponse<BusinessProcessDTO> addSocialMediaRecord(
            @PathVariable Long id,
            @RequestParam String record) {
        return BaseResponse.success(businessProcessService.addSocialMediaRecord(id, record));
    }

    @PutMapping("/{id}/ivf")
    @PreAuthorize("hasRole('USER')")
    public BaseResponse<BusinessProcessDTO> updateIVFRecord(
            @PathVariable Long id,
            @RequestParam String record) {
        return BaseResponse.success(businessProcessService.updateIVFRecord(id, record));
    }

    @PutMapping("/{id}/embryo-transfer")
    @PreAuthorize("hasRole('USER')")
    public BaseResponse<BusinessProcessDTO> updateEmbryoTransferRecord(
            @PathVariable Long id,
            @RequestParam String record) {
        return BaseResponse.success(businessProcessService.updateEmbryoTransferRecord(id, record));
    }

    @PutMapping("/{id}/pregnancy-care")
    @PreAuthorize("hasRole('USER')")
    public BaseResponse<BusinessProcessDTO> updatePregnancyCareRecord(
            @PathVariable Long id,
            @RequestParam String record) {
        return BaseResponse.success(businessProcessService.updatePregnancyCareRecord(id, record));
    }

    @PutMapping("/{id}/delivery")
    @PreAuthorize("hasRole('USER')")
    public BaseResponse<BusinessProcessDTO> updateDeliveryRecord(
            @PathVariable Long id,
            @RequestParam String record) {
        return BaseResponse.success(businessProcessService.updateDeliveryRecord(id, record));
    }

    @PutMapping("/{id}/entry-service")
    @PreAuthorize("hasRole('USER')")
    public BaseResponse<BusinessProcessDTO> updateEntryServiceRecord(
            @PathVariable Long id,
            @RequestParam String record) {
        return BaseResponse.success(businessProcessService.updateEntryServiceRecord(id, record));
    }

    @PutMapping("/{id}/settlement-service")
    @PreAuthorize("hasRole('USER')")
    public BaseResponse<BusinessProcessDTO> updateSettlementServiceRecord(
            @PathVariable Long id,
            @RequestParam String record) {
        return BaseResponse.success(businessProcessService.updateSettlementServiceRecord(id, record));
    }

    @PutMapping("/{id}/timeline")
    @PreAuthorize("hasRole('USER')")
    public BaseResponse<BusinessProcessDTO> updateTimeline(
            @PathVariable Long id,
            @RequestParam LocalDateTime startTime,
            @RequestParam LocalDateTime expectedEndTime) {
        return BaseResponse.success(businessProcessService.updateTimeline(id, startTime, expectedEndTime));
    }

    @PutMapping("/{id}/complete")
    @PreAuthorize("hasRole('USER')")
    public BaseResponse<BusinessProcessDTO> completeBusiness(
            @PathVariable Long id,
            @RequestParam LocalDateTime actualEndTime) {
        return BaseResponse.success(businessProcessService.completeBusiness(id, actualEndTime));
    }

    @GetMapping("/upcoming-deadlines")
    @PreAuthorize("hasRole('USER')")
    public BaseResponse<List<BusinessProcessDTO>> getUpcomingDeadlines(
            @RequestParam LocalDateTime date) {
        return BaseResponse.success(businessProcessService.getUpcomingDeadlines(date));
    }

    @GetMapping("/count")
    @PreAuthorize("hasRole('USER')")
    public BaseResponse<Long> countBusinessesInPeriod(
            @RequestParam LocalDateTime startDate,
            @RequestParam LocalDateTime endDate) {
        return BaseResponse.success(businessProcessService.countBusinessesInPeriod(startDate, endDate));
    }

    @GetMapping("/trend")
    @PreAuthorize("hasRole('USER')")
    public BaseResponse<List<Map<String, Object>>> getBusinessTrend(
            @RequestParam LocalDateTime startDate,
            @RequestParam LocalDateTime endDate) {
        return BaseResponse.success(businessProcessService.getBusinessTrend(startDate, endDate));
    }
} 
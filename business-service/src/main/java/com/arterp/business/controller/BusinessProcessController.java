package com.arterp.business.controller;

import com.arterp.business.dto.BusinessProcessDTO;
import com.arterp.business.service.BusinessProcessService;
import com.arterp.business.repository.BusinessProcessRepository;
import com.arterp.common.dto.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.time.LocalDateTime;
import java.util.HashMap;

@RestController
@RequestMapping("/api/business")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class BusinessProcessController {
    private final BusinessProcessService businessProcessService;
    private final BusinessProcessRepository businessProcessRepository;

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

    @GetMapping("/stats/process-type")
    @PreAuthorize("hasRole('USER')")
    public BaseResponse<Map<String, Long>> getProcessTypeDistribution() {
        return BaseResponse.success(businessProcessService.getProcessTypeDistribution());
    }

    @GetMapping("/stats/status")
    @PreAuthorize("hasRole('USER')")
    public BaseResponse<Map<String, Long>> getStatusDistribution() {
        return BaseResponse.success(businessProcessService.getStatusDistribution());
    }

    @GetMapping("/stats/risk-level")
    @PreAuthorize("hasRole('USER')")
    public BaseResponse<Map<String, Long>> getRiskLevelDistribution() {
        return BaseResponse.success(businessProcessService.getRiskLevelDistribution());
    }

    @GetMapping("/stats")
    @PreAuthorize("hasRole('USER')")
    public BaseResponse<Map<String, Object>> getBusinessStats() {
        Map<String, Object> stats = new HashMap<>();
        
        // Get active processes count
        long activeProcesses = businessProcessRepository.countByStatus("In Progress");
        stats.put("activeProcesses", activeProcesses);
        
        // Get process growth (new processes in last month)
        LocalDateTime lastMonth = LocalDateTime.now().minusMonths(1);
        long newProcesses = businessProcessRepository.countByCreatedAtAfter(lastMonth);
        double processGrowth = activeProcesses > 0 ? (newProcesses * 100.0 / activeProcesses) : 0;
        stats.put("processGrowth", processGrowth);
        
        // Get process type distribution
        Map<String, Long> typeDistribution = businessProcessService.getProcessTypeDistribution();
        stats.put("typeDistribution", typeDistribution);
        
        // Get process status distribution
        Map<String, Long> statusDistribution = businessProcessService.getStatusDistribution();
        stats.put("statusDistribution", statusDistribution);
        
        // Get process risk level distribution
        Map<String, Long> riskDistribution = businessProcessService.getRiskLevelDistribution();
        stats.put("riskDistribution", riskDistribution);
        
        return BaseResponse.success(stats);
    }
} 
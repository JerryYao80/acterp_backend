package com.arterp.resource.controller;

import com.arterp.common.dto.BaseResponse;
import com.arterp.resource.dto.ResourceDTO;
import com.arterp.resource.service.ResourceService;
import com.arterp.resource.repository.ResourceRepository;
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
@RequestMapping("/api/resources")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class ResourceController {
    private final ResourceService resourceService;
    private final ResourceRepository resourceRepository;

    @GetMapping("/search")
    @PreAuthorize("hasRole('USER')")
    public BaseResponse<Page<ResourceDTO>> searchResources(
            @RequestParam String type,
            @RequestParam(required = false) String search,
            Pageable pageable) {
        return BaseResponse.success(resourceService.searchResources(type, search, pageable));
    }

    @GetMapping("/status")
    @PreAuthorize("hasRole('USER')")
    public BaseResponse<Page<ResourceDTO>> getResourcesByStatus(
            @RequestParam String type,
            @RequestParam String status,
            Pageable pageable) {
        return BaseResponse.success(resourceService.getResourcesByStatus(type, status, pageable));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public BaseResponse<ResourceDTO> getResource(@PathVariable Long id) {
        return BaseResponse.success(resourceService.getResourceById(id));
    }

    @PostMapping("/{type}")
    @PreAuthorize("hasRole('ADMIN')")
    public BaseResponse<ResourceDTO> createResource(
            @PathVariable String type,
            @Valid @RequestBody ResourceDTO resourceDTO) {
        return BaseResponse.success(resourceService.createResource(type, resourceDTO));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public BaseResponse<ResourceDTO> updateResource(
            @PathVariable Long id,
            @Valid @RequestBody ResourceDTO resourceDTO) {
        return BaseResponse.success(resourceService.updateResource(id, resourceDTO));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public BaseResponse<Void> deleteResource(@PathVariable Long id) {
        resourceService.deleteResource(id);
        return BaseResponse.success(null);
    }

    @GetMapping("/stats/status")
    @PreAuthorize("hasRole('USER')")
    public BaseResponse<Map<String, Long>> getStatusDistribution(@RequestParam String type) {
        return BaseResponse.success(resourceService.getStatusDistribution(type));
    }

    @GetMapping("/stats/location")
    @PreAuthorize("hasRole('USER')")
    public BaseResponse<Map<String, Long>> getLocationDistribution(@RequestParam String type) {
        return BaseResponse.success(resourceService.getLocationDistribution(type));
    }

    @GetMapping("/stats/quality")
    @PreAuthorize("hasRole('USER')")
    public BaseResponse<Map<String, Long>> getQualityDistribution(@RequestParam String type) {
        return BaseResponse.success(resourceService.getQualityDistribution(type));
    }

    @GetMapping("/stats/risk-level")
    @PreAuthorize("hasRole('USER')")
    public BaseResponse<Map<String, Long>> getRiskLevelDistribution(@RequestParam String type) {
        return BaseResponse.success(resourceService.getRiskLevelDistribution(type));
    }

    @GetMapping("/stats")
    @PreAuthorize("hasRole('USER')")
    public BaseResponse<Map<String, Object>> getResourceStats() {
        Map<String, Object> stats = new HashMap<>();
        
        // Get available resources count
        long availableResources = resourceRepository.countByStatus("Available");
        stats.put("availableResources", availableResources);
        
        // Get resource growth (new resources in last month)
        LocalDateTime lastMonth = LocalDateTime.now().minusMonths(1);
        long newResources = resourceRepository.countByCreatedAtAfter(lastMonth);
        double resourceGrowth = availableResources > 0 ? (newResources * 100.0 / availableResources) : 0;
        stats.put("resourceGrowth", resourceGrowth);
        
        // Get resource type distribution
        Map<String, Long> typeDistribution = new HashMap<>();
        typeDistribution.put("DONOR", resourceRepository.countByDiscriminatorValue("DONOR"));
        typeDistribution.put("SURROGATE", resourceRepository.countByDiscriminatorValue("SURROGATE"));
        typeDistribution.put("MEDICAL", resourceRepository.countByDiscriminatorValue("MEDICAL"));
        typeDistribution.put("POSTNATAL", resourceRepository.countByDiscriminatorValue("POSTNATAL"));
        typeDistribution.put("HUMAN", resourceRepository.countByDiscriminatorValue("HUMAN"));
        stats.put("typeDistribution", typeDistribution);
        
        // Get resource status distribution
        Map<String, Long> statusDistribution = resourceService.getStatusDistribution("ALL");
        stats.put("statusDistribution", statusDistribution);
        
        // Get resource quality distribution
        Map<String, Long> qualityDistribution = resourceService.getQualityDistribution("ALL");
        stats.put("qualityDistribution", qualityDistribution);
        
        // Get resource risk level distribution
        Map<String, Long> riskDistribution = resourceService.getRiskLevelDistribution("ALL");
        stats.put("riskDistribution", riskDistribution);
        
        return BaseResponse.success(stats);
    }
} 
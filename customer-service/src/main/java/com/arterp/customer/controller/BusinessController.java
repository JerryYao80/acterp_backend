package com.arterp.customer.controller;

import com.arterp.customer.dto.BusinessDTO;
import com.arterp.customer.entity.enums.BusinessPhase;
import com.arterp.customer.entity.enums.BusinessStatus;
import com.arterp.customer.service.BusinessService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/businesses")
@RequiredArgsConstructor
public class BusinessController {
    private final BusinessService businessService;

    @PostMapping
    public ResponseEntity<BusinessDTO> createBusiness(@RequestBody BusinessDTO businessDTO) {
        return ResponseEntity.ok(businessService.createBusiness(businessDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BusinessDTO> updateBusiness(@PathVariable Long id, @RequestBody BusinessDTO businessDTO) {
        return ResponseEntity.ok(businessService.updateBusiness(id, businessDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BusinessDTO> getBusiness(@PathVariable Long id) {
        return ResponseEntity.ok(businessService.getBusiness(id));
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<BusinessDTO>> getBusinessesByCustomer(@PathVariable Long customerId) {
        return ResponseEntity.ok(businessService.getBusinessesByCustomer(customerId));
    }

    @GetMapping("/phase/{phase}")
    public ResponseEntity<List<BusinessDTO>> getBusinessesByPhase(@PathVariable BusinessPhase phase) {
        return ResponseEntity.ok(businessService.getBusinessesByPhase(phase));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<BusinessDTO>> getBusinessesByStatus(@PathVariable BusinessStatus status) {
        return ResponseEntity.ok(businessService.getBusinessesByStatus(status));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBusiness(@PathVariable Long id) {
        businessService.deleteBusiness(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/statistics/phase")
    public ResponseEntity<Map<BusinessPhase, Long>> countByPhase() {
        return ResponseEntity.ok(businessService.countByPhase());
    }

    @GetMapping("/statistics/location")
    public ResponseEntity<Map<String, Long>> countByLocation() {
        return ResponseEntity.ok(businessService.countByLocation());
    }

    @GetMapping("/statistics/type")
    public ResponseEntity<Map<String, Long>> countByType() {
        return ResponseEntity.ok(businessService.countByType());
    }
} 
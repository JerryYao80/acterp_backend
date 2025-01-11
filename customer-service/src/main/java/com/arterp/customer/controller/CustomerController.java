package com.arterp.customer.controller;

import com.arterp.common.dto.BaseResponse;
import com.arterp.customer.dto.CustomerDTO;
import com.arterp.customer.service.CustomerService;
import com.arterp.customer.repository.CustomerRepository;
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
@RequestMapping("/api/customers")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class CustomerController {
    private final CustomerService customerService;
    private final CustomerRepository customerRepository;

    @GetMapping("/search")
    @PreAuthorize("hasRole('USER')")
    public BaseResponse<Page<CustomerDTO>> searchCustomers(
            @RequestParam(required = false, defaultValue = "") String search,
            Pageable pageable) {
        return BaseResponse.success(customerService.searchCustomers(search, pageable));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public BaseResponse<CustomerDTO> getCustomer(@PathVariable Long id) {
        return BaseResponse.success(customerService.getCustomerById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public BaseResponse<CustomerDTO> createCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        return BaseResponse.success(customerService.createCustomer(customerDTO));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public BaseResponse<CustomerDTO> updateCustomer(
            @PathVariable Long id,
            @Valid @RequestBody CustomerDTO customerDTO) {
        return BaseResponse.success(customerService.updateCustomer(id, customerDTO));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public BaseResponse<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return BaseResponse.success(null);
    }

    @GetMapping("/stats/nationality")
    @PreAuthorize("hasRole('USER')")
    public BaseResponse<Map<String, Long>> getNationalityDistribution() {
        return BaseResponse.success(customerService.getNationalityDistribution());
    }

    @GetMapping("/stats/risk-level")
    @PreAuthorize("hasRole('USER')")
    public BaseResponse<Map<String, Long>> getRiskLevelDistribution() {
        return BaseResponse.success(customerService.getRiskLevelDistribution());
    }

    @GetMapping("/stats")
    @PreAuthorize("hasRole('USER')")
    public BaseResponse<Map<String, Object>> getCustomerStats() {
        Map<String, Object> stats = new HashMap<>();
        
        // Get total customers
        long totalCustomers = customerRepository.count();
        stats.put("totalCustomers", totalCustomers);
        
        // Get customer growth (new customers in last month)
        LocalDateTime lastMonth = LocalDateTime.now().minusMonths(1);
        long newCustomers = customerRepository.countByCreatedAtAfter(lastMonth);
        double customerGrowth = totalCustomers > 0 ? (newCustomers * 100.0 / totalCustomers) : 0;
        stats.put("customerGrowth", customerGrowth);
        
        // Get customer type distribution
        Map<String, Long> typeDistribution = customerService.getCustomerTypeDistribution();
        stats.put("typeDistribution", typeDistribution);
        
        // Get customer status distribution
        Map<String, Long> statusDistribution = customerService.getStatusDistribution();
        stats.put("statusDistribution", statusDistribution);
        
        // Get customer risk level distribution
        Map<String, Long> riskDistribution = customerService.getRiskLevelDistribution();
        stats.put("riskDistribution", riskDistribution);
        
        return BaseResponse.success(stats);
    }
} 
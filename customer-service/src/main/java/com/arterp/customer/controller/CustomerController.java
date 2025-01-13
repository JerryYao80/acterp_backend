package com.arterp.customer.controller;

import com.arterp.customer.dto.CustomerDTO;
import com.arterp.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> searchCustomers(
            @RequestParam(required = false, defaultValue = "") String search,
            Pageable pageable) {
        Page<CustomerDTO> page = customerService.searchCustomers(search, pageable);
        return ResponseEntity.ok(Map.of(
            "success", true,
            "message", "Customers retrieved successfully",
            "data", page,
            "code", 200
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getCustomer(@PathVariable Long id) {
        CustomerDTO customer = customerService.getCustomer(id);
        return ResponseEntity.ok(Map.of(
            "success", true,
            "message", "Customer retrieved successfully",
            "data", customer,
            "code", 200
        ));
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        CustomerDTO createdCustomer = customerService.createCustomer(customerDTO);
        return ResponseEntity.ok(Map.of(
            "success", true,
            "message", "Customer created successfully",
            "data", createdCustomer,
            "code", 200
        ));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateCustomer(
            @PathVariable Long id,
            @Valid @RequestBody CustomerDTO customerDTO) {
        CustomerDTO updatedCustomer = customerService.updateCustomer(id, customerDTO);
        return ResponseEntity.ok(Map.of(
            "success", true,
            "message", "Customer updated successfully",
            "data", updatedCustomer,
            "code", 200
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok(Map.of(
            "success", true,
            "message", "Customer deleted successfully",
            "data", null,
            "code", 200
        ));
    }

    @GetMapping("/stats/status")
    public ResponseEntity<Map<String, Object>> getStatusDistribution() {
        Map<String, Long> distribution = customerService.getStatusDistribution();
        return ResponseEntity.ok(Map.of(
            "success", true,
            "message", "Status distribution retrieved successfully",
            "data", distribution,
            "code", 200
        ));
    }

    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getCustomerStats() {
        Map<String, Object> stats = customerService.getCustomerStats();
        return ResponseEntity.ok(Map.of(
            "success", true,
            "message", "Customer stats retrieved successfully",
            "data", stats,
            "code", 200
        ));
    }
} 
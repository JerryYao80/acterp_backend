package com.arterp.customer.service;

import com.arterp.customer.dto.CustomerDTO;
import com.arterp.customer.entity.Customer;
import com.arterp.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.modelmapper.ModelMapper;

import javax.persistence.EntityNotFoundException;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    public Page<CustomerDTO> searchCustomers(String search, Pageable pageable) {
        Page<Customer> customers = customerRepository.findByNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrPhoneContaining(
            search, search, search, pageable);
        return customers.map(customer -> modelMapper.map(customer, CustomerDTO.class));
    }

    public CustomerDTO getCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Customer not found with id: " + id));
        return modelMapper.map(customer, CustomerDTO.class);
    }

    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        Customer customer = modelMapper.map(customerDTO, Customer.class);
        customer = customerRepository.save(customer);
        return modelMapper.map(customer, CustomerDTO.class);
    }

    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) {
        Customer customer = customerRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Customer not found with id: " + id));
        
        modelMapper.map(customerDTO, customer);
        customer = customerRepository.save(customer);
        return modelMapper.map(customer, CustomerDTO.class);
    }

    public void deleteCustomer(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new EntityNotFoundException("Customer not found with id: " + id);
        }
        customerRepository.deleteById(id);
    }

    public Map<String, Long> getStatusDistribution() {
        return customerRepository.findAll().stream()
            .collect(Collectors.groupingBy(Customer::getStatus, Collectors.counting()));
    }

    public Map<String, Long> getRiskLevelDistribution() {
        return customerRepository.findAll().stream()
            .collect(Collectors.groupingBy(Customer::getRiskLevel, Collectors.counting()));
    }

    public Map<String, Object> getCustomerStats() {
        long totalCustomers = customerRepository.count();
        // TODO: Implement customer growth calculation
        double customerGrowth = 0.0;

        Map<String, Long> typeDistribution = customerRepository.findAll().stream()
            .collect(Collectors.groupingBy(Customer::getCustomerType, Collectors.counting()));
        
        Map<String, Long> statusDistribution = getStatusDistribution();
        Map<String, Long> riskDistribution = getRiskLevelDistribution();

        return Map.of(
            "totalCustomers", totalCustomers,
            "customerGrowth", customerGrowth,
            "typeDistribution", typeDistribution,
            "statusDistribution", statusDistribution,
            "riskDistribution", riskDistribution
        );
    }
} 
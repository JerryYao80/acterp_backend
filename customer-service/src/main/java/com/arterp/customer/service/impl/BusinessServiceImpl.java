package com.arterp.customer.service.impl;

import com.arterp.customer.dto.BusinessDTO;
import com.arterp.customer.entity.Business;
import com.arterp.customer.entity.Customer;
import com.arterp.customer.entity.enums.BusinessPhase;
import com.arterp.customer.entity.enums.BusinessStatus;
import com.arterp.customer.repository.BusinessRepository;
import com.arterp.customer.repository.CustomerRepository;
import com.arterp.customer.service.BusinessService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BusinessServiceImpl implements BusinessService {
    private final BusinessRepository businessRepository;
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public BusinessDTO createBusiness(BusinessDTO businessDTO) {
        Business business = modelMapper.map(businessDTO, Business.class);
        Customer customer = customerRepository.findById(businessDTO.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        business.setCustomer(customer);
        business = businessRepository.save(business);
        return modelMapper.map(business, BusinessDTO.class);
    }

    @Override
    @Transactional
    public BusinessDTO updateBusiness(Long id, BusinessDTO businessDTO) {
        Business business = businessRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Business not found"));
        modelMapper.map(businessDTO, business);
        business = businessRepository.save(business);
        return modelMapper.map(business, BusinessDTO.class);
    }

    @Override
    public BusinessDTO getBusiness(Long id) {
        Business business = businessRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Business not found"));
        return modelMapper.map(business, BusinessDTO.class);
    }

    @Override
    public List<BusinessDTO> getBusinessesByCustomer(Long customerId) {
        return businessRepository.findByCustomerId(customerId).stream()
                .map(business -> modelMapper.map(business, BusinessDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<BusinessDTO> getBusinessesByPhase(BusinessPhase phase) {
        return businessRepository.findByCurrentPhase(phase).stream()
                .map(business -> modelMapper.map(business, BusinessDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<BusinessDTO> getBusinessesByStatus(BusinessStatus status) {
        return businessRepository.findByStatus(status).stream()
                .map(business -> modelMapper.map(business, BusinessDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteBusiness(Long id) {
        businessRepository.deleteById(id);
    }

    @Override
    public Map<BusinessPhase, Long> countByPhase() {
        return businessRepository.countByPhase().stream()
                .collect(Collectors.toMap(
                        row -> (BusinessPhase) row[0],
                        row -> (Long) row[1]
                ));
    }

    @Override
    public Map<String, Long> countByLocation() {
        return businessRepository.countByLocation().stream()
                .collect(Collectors.toMap(
                        row -> (String) row[0],
                        row -> (Long) row[1]
                ));
    }

    @Override
    public Map<String, Long> countByType() {
        return businessRepository.countByType().stream()
                .collect(Collectors.toMap(
                        row -> (String) row[0],
                        row -> (Long) row[1]
                ));
    }
} 
package com.arterp.customer.service;

import com.arterp.common.exception.BusinessException;
import com.arterp.customer.dto.CustomerDTO;
import com.arterp.customer.entity.Customer;
import com.arterp.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Transactional(readOnly = true)
    public Page<CustomerDTO> searchCustomers(String search, Pageable pageable) {
        return customerRepository.search(search, pageable)
                .map(this::convertToDTO);
    }

    @Transactional(readOnly = true)
    public CustomerDTO getCustomerById(Long id) {
        return convertToDTO(findCustomerById(id));
    }

    @Transactional
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        validateNewCustomer(customerDTO);
        Customer customer = convertToEntity(customerDTO);
        return convertToDTO(customerRepository.save(customer));
    }

    @Transactional
    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) {
        Customer existingCustomer = findCustomerById(id);
        validateExistingCustomer(customerDTO, existingCustomer);
        updateCustomerFields(existingCustomer, customerDTO);
        return convertToDTO(customerRepository.save(existingCustomer));
    }

    @Transactional
    public void deleteCustomer(Long id) {
        Customer customer = findCustomerById(id);
        customer.setDeleted(true);
        customerRepository.save(customer);
    }

    @Transactional(readOnly = true)
    public Map<String, Long> getStatusDistribution() {
        return customerRepository.countByStatus().stream()
                .collect(Collectors.toMap(
                        row -> (String) row[0],
                        row -> (Long) row[1]
                ));
    }

    @Transactional(readOnly = true)
    public Map<String, Long> getCustomerTypeDistribution() {
        return customerRepository.countByCustomerType().stream()
                .collect(Collectors.toMap(
                        row -> (String) row[0],
                        row -> (Long) row[1]
                ));
    }

    @Transactional(readOnly = true)
    public Map<String, Long> getRiskLevelDistribution() {
        return customerRepository.countByRiskLevel().stream()
                .collect(Collectors.toMap(
                        row -> (String) row[0],
                        row -> (Long) row[1]
                ));
    }

    @Transactional(readOnly = true)
    public Map<String, Long> getSourceDistribution() {
        return customerRepository.countBySource().stream()
                .collect(Collectors.toMap(
                        row -> (String) row[0],
                        row -> (Long) row[1]
                ));
    }

    private Customer findCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Customer not found with id: " + id));
    }

    private void validateNewCustomer(CustomerDTO customerDTO) {
        customerRepository.findByEmail(customerDTO.getEmail())
                .ifPresent(c -> {
                    throw new BusinessException("Email already exists: " + customerDTO.getEmail());
                });

        customerRepository.findByPhone(customerDTO.getPhone())
                .ifPresent(c -> {
                    throw new BusinessException("Phone already exists: " + customerDTO.getPhone());
                });

        customerRepository.findByIdNumber(customerDTO.getIdNumber())
                .ifPresent(c -> {
                    throw new BusinessException("ID number already exists: " + customerDTO.getIdNumber());
                });
    }

    private void validateExistingCustomer(CustomerDTO customerDTO, Customer existingCustomer) {
        customerRepository.findByEmail(customerDTO.getEmail())
                .ifPresent(c -> {
                    if (!c.getId().equals(existingCustomer.getId())) {
                        throw new BusinessException("Email already exists: " + customerDTO.getEmail());
                    }
                });

        customerRepository.findByPhone(customerDTO.getPhone())
                .ifPresent(c -> {
                    if (!c.getId().equals(existingCustomer.getId())) {
                        throw new BusinessException("Phone already exists: " + customerDTO.getPhone());
                    }
                });

        customerRepository.findByIdNumber(customerDTO.getIdNumber())
                .ifPresent(c -> {
                    if (!c.getId().equals(existingCustomer.getId())) {
                        throw new BusinessException("ID number already exists: " + customerDTO.getIdNumber());
                    }
                });
    }

    private CustomerDTO convertToDTO(Customer customer) {
        CustomerDTO dto = new CustomerDTO();
        dto.setId(customer.getId());
        dto.setName(customer.getName());
        dto.setGender(customer.getGender());
        dto.setBirthDate(customer.getBirthDate());
        dto.setPhone(customer.getPhone());
        dto.setEmail(customer.getEmail());
        dto.setIdType(customer.getIdType());
        dto.setIdNumber(customer.getIdNumber());
        dto.setNationality(customer.getNationality());
        dto.setMaritalStatus(customer.getMaritalStatus());
        dto.setMedicalHistory(customer.getMedicalHistory());
        dto.setFamilyHistory(customer.getFamilyHistory());
        dto.setGeneticScreening(customer.getGeneticScreening());
        dto.setStatus(customer.getStatus());
        dto.setCustomerType(customer.getCustomerType());
        dto.setRequirements(customer.getRequirements());
        dto.setPreferences(customer.getPreferences());
        dto.setRiskLevel(customer.getRiskLevel());
        dto.setAddresses(customer.getAddresses());
        dto.setDocumentUrls(customer.getDocumentUrls());
        dto.setNotes(customer.getNotes());
        dto.setHasInsurance(customer.getHasInsurance());
        dto.setInsuranceInformation(customer.getInsuranceInformation());
        dto.setSource(customer.getSource());
        dto.setEmergencyContact(customer.getEmergencyContact());
        dto.setPreferredLanguage(customer.getPreferredLanguage());
        dto.setCommunicationPreference(customer.getCommunicationPreference());
        dto.setMarketingConsent(customer.getMarketingConsent());
        return dto;
    }

    private Customer convertToEntity(CustomerDTO dto) {
        Customer customer = new Customer();
        updateCustomerFields(customer, dto);
        return customer;
    }

    private void updateCustomerFields(Customer customer, CustomerDTO dto) {
        customer.setName(dto.getName());
        customer.setGender(dto.getGender());
        customer.setBirthDate(dto.getBirthDate());
        customer.setPhone(dto.getPhone());
        customer.setEmail(dto.getEmail());
        customer.setIdType(dto.getIdType());
        customer.setIdNumber(dto.getIdNumber());
        customer.setNationality(dto.getNationality());
        customer.setMaritalStatus(dto.getMaritalStatus());
        customer.setMedicalHistory(dto.getMedicalHistory());
        customer.setFamilyHistory(dto.getFamilyHistory());
        customer.setGeneticScreening(dto.getGeneticScreening());
        customer.setStatus(dto.getStatus());
        customer.setCustomerType(dto.getCustomerType());
        customer.setRequirements(dto.getRequirements());
        customer.setPreferences(dto.getPreferences());
        customer.setRiskLevel(dto.getRiskLevel());
        customer.setAddresses(dto.getAddresses());
        customer.setDocumentUrls(dto.getDocumentUrls());
        customer.setNotes(dto.getNotes());
        customer.setHasInsurance(dto.getHasInsurance());
        customer.setInsuranceInformation(dto.getInsuranceInformation());
        customer.setSource(dto.getSource());
        customer.setEmergencyContact(dto.getEmergencyContact());
        customer.setPreferredLanguage(dto.getPreferredLanguage());
        customer.setCommunicationPreference(dto.getCommunicationPreference());
        customer.setMarketingConsent(dto.getMarketingConsent());
    }
} 
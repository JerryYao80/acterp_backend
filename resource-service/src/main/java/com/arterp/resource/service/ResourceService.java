package com.arterp.resource.service;

import com.arterp.common.exception.BusinessException;
import com.arterp.resource.dto.*;
import com.arterp.resource.entity.*;
import com.arterp.resource.repository.ResourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ResourceService {
    private final ResourceRepository resourceRepository;

    @Transactional(readOnly = true)
    public Page<ResourceDTO> searchResources(String type, String search, Pageable pageable) {
        Class<?> resourceType = getResourceType(type);
        return resourceRepository.searchByType(resourceType, search, pageable)
                .map(this::convertToDTO);
    }

    @Transactional(readOnly = true)
    public Page<ResourceDTO> getResourcesByStatus(String type, String status, Pageable pageable) {
        Class<?> resourceType = getResourceType(type);
        return resourceRepository.findByTypeAndStatus(resourceType, status, pageable)
                .map(this::convertToDTO);
    }

    @Transactional(readOnly = true)
    public ResourceDTO getResourceById(Long id) {
        return convertToDTO(findResourceById(id));
    }

    @Transactional
    public ResourceDTO createResource(String type, ResourceDTO resourceDTO) {
        Resource resource = convertToEntity(type, resourceDTO);
        return convertToDTO(resourceRepository.save(resource));
    }

    @Transactional
    public ResourceDTO updateResource(Long id, ResourceDTO resourceDTO) {
        Resource existingResource = findResourceById(id);
        updateResourceFields(existingResource, resourceDTO);
        return convertToDTO(resourceRepository.save(existingResource));
    }

    @Transactional
    public void deleteResource(Long id) {
        Resource resource = findResourceById(id);
        resource.setDeleted(true);
        resourceRepository.save(resource);
    }

    @Transactional(readOnly = true)
    public Map<String, Long> getStatusDistribution(String type) {
        Class<?> resourceType = getResourceType(type);
        return resourceRepository.countByTypeAndStatus(resourceType).stream()
                .collect(Collectors.toMap(
                        row -> (String) row[0],
                        row -> (Long) row[1]
                ));
    }

    @Transactional(readOnly = true)
    public Map<String, Long> getLocationDistribution(String type) {
        Class<?> resourceType = getResourceType(type);
        return resourceRepository.countByTypeAndLocation(resourceType).stream()
                .collect(Collectors.toMap(
                        row -> (String) row[0],
                        row -> (Long) row[1]
                ));
    }

    @Transactional(readOnly = true)
    public Map<String, Long> getQualityDistribution(String type) {
        Class<?> resourceType = getResourceType(type);
        return resourceRepository.countByTypeAndQuality(resourceType).stream()
                .collect(Collectors.toMap(
                        row -> (String) row[0],
                        row -> (Long) row[1]
                ));
    }

    @Transactional(readOnly = true)
    public Map<String, Long> getRiskLevelDistribution(String type) {
        Class<?> resourceType = getResourceType(type);
        return resourceRepository.countByTypeAndRiskLevel(resourceType).stream()
                .collect(Collectors.toMap(
                        row -> (String) row[0],
                        row -> (Long) row[1]
                ));
    }

    private Resource findResourceById(Long id) {
        return resourceRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Resource not found with id: " + id));
    }

    private Class<?> getResourceType(String type) {
        switch (type.toUpperCase()) {
            case "DONOR":
                return DonorResource.class;
            case "SURROGATE":
                return SurrogateResource.class;
            case "MEDICAL":
                return MedicalResource.class;
            case "POSTNATAL":
                return PostnatalResource.class;
            case "HUMAN":
                return HumanResource.class;
            default:
                throw new BusinessException("Invalid resource type: " + type);
        }
    }

    private ResourceDTO convertToDTO(Resource resource) {
        if (resource instanceof DonorResource) {
            return convertToDonorDTO((DonorResource) resource);
        } else if (resource instanceof SurrogateResource) {
            return convertToSurrogateDTO((SurrogateResource) resource);
        } else if (resource instanceof MedicalResource) {
            return convertToMedicalDTO((MedicalResource) resource);
        } else if (resource instanceof PostnatalResource) {
            return convertToPostnatalDTO((PostnatalResource) resource);
        } else if (resource instanceof HumanResource) {
            return convertToHumanDTO((HumanResource) resource);
        }
        throw new BusinessException("Unknown resource type");
    }

    private Resource convertToEntity(String type, ResourceDTO dto) {
        switch (type.toUpperCase()) {
            case "DONOR":
                return convertToDonorEntity((DonorResourceDTO) dto);
            case "SURROGATE":
                return convertToSurrogateEntity((SurrogateResourceDTO) dto);
            case "MEDICAL":
                return convertToMedicalEntity((MedicalResourceDTO) dto);
            case "POSTNATAL":
                return convertToPostnatalEntity((PostnatalResourceDTO) dto);
            case "HUMAN":
                return convertToHumanEntity((HumanResourceDTO) dto);
            default:
                throw new BusinessException("Invalid resource type: " + type);
        }
    }

    private void updateResourceFields(Resource resource, ResourceDTO dto) {
        resource.setName(dto.getName());
        resource.setStatus(dto.getStatus());
        resource.setLocation(dto.getLocation());
        resource.setCost(dto.getCost());
        resource.setQuality(dto.getQuality());
        resource.setDescription(dto.getDescription());
        resource.setTags(dto.getTags());
        resource.setDocumentUrls(dto.getDocumentUrls());
        resource.setNotes(dto.getNotes());
        resource.setAvailableFrom(dto.getAvailableFrom());
        resource.setAvailableTo(dto.getAvailableTo());
        resource.setRiskLevel(dto.getRiskLevel());

        if (resource instanceof DonorResource && dto instanceof DonorResourceDTO) {
            updateDonorFields((DonorResource) resource, (DonorResourceDTO) dto);
        } else if (resource instanceof SurrogateResource && dto instanceof SurrogateResourceDTO) {
            updateSurrogateFields((SurrogateResource) resource, (SurrogateResourceDTO) dto);
        } else if (resource instanceof MedicalResource && dto instanceof MedicalResourceDTO) {
            updateMedicalFields((MedicalResource) resource, (MedicalResourceDTO) dto);
        } else if (resource instanceof PostnatalResource && dto instanceof PostnatalResourceDTO) {
            updatePostnatalFields((PostnatalResource) resource, (PostnatalResourceDTO) dto);
        } else if (resource instanceof HumanResource && dto instanceof HumanResourceDTO) {
            updateHumanFields((HumanResource) resource, (HumanResourceDTO) dto);
        }
    }

    // Conversion methods for each resource type...
    // These methods would contain the specific conversion logic for each resource type
    // I'll implement one as an example, and you can follow the same pattern for others

    private DonorResourceDTO convertToDonorDTO(DonorResource resource) {
        DonorResourceDTO dto = new DonorResourceDTO();
        // Copy base fields
        dto.setId(resource.getId());
        dto.setName(resource.getName());
        dto.setStatus(resource.getStatus());
        dto.setLocation(resource.getLocation());
        dto.setCost(resource.getCost());
        dto.setQuality(resource.getQuality());
        dto.setDescription(resource.getDescription());
        dto.setTags(resource.getTags());
        dto.setDocumentUrls(resource.getDocumentUrls());
        dto.setNotes(resource.getNotes());
        dto.setAvailableFrom(resource.getAvailableFrom());
        dto.setAvailableTo(resource.getAvailableTo());
        dto.setRiskLevel(resource.getRiskLevel());

        // Copy donor-specific fields
        dto.setDonorType(resource.getDonorType());
        dto.setBirthDate(resource.getBirthDate());
        dto.setBloodType(resource.getBloodType());
        dto.setEthnicity(resource.getEthnicity());
        dto.setEducation(resource.getEducation());
        dto.setHeight(resource.getHeight());
        dto.setWeight(resource.getWeight());
        dto.setMedicalHistory(resource.getMedicalHistory());
        dto.setFamilyHistory(resource.getFamilyHistory());
        dto.setGeneticScreening(resource.getGeneticScreening());
        dto.setEyeColor(resource.getEyeColor());
        dto.setHairColor(resource.getHairColor());
        dto.setSkinTone(resource.getSkinTone());
        dto.setPersonalityTraits(resource.getPersonalityTraits());
        dto.setSpecialTalents(resource.getSpecialTalents());
        dto.setDonationCount(resource.getDonationCount());
        dto.setSuccessfulDonations(resource.getSuccessfulDonations());

        return dto;
    }

    private DonorResource convertToDonorEntity(DonorResourceDTO dto) {
        DonorResource resource = new DonorResource();
        updateDonorFields(resource, dto);
        return resource;
    }

    private void updateDonorFields(DonorResource resource, DonorResourceDTO dto) {
        // Update base fields first
        updateResourceFields(resource, dto);

        // Update donor-specific fields
        resource.setDonorType(dto.getDonorType());
        resource.setBirthDate(dto.getBirthDate());
        resource.setBloodType(dto.getBloodType());
        resource.setEthnicity(dto.getEthnicity());
        resource.setEducation(dto.getEducation());
        resource.setHeight(dto.getHeight());
        resource.setWeight(dto.getWeight());
        resource.setMedicalHistory(dto.getMedicalHistory());
        resource.setFamilyHistory(dto.getFamilyHistory());
        resource.setGeneticScreening(dto.getGeneticScreening());
        resource.setEyeColor(dto.getEyeColor());
        resource.setHairColor(dto.getHairColor());
        resource.setSkinTone(dto.getSkinTone());
        resource.setPersonalityTraits(dto.getPersonalityTraits());
        resource.setSpecialTalents(dto.getSpecialTalents());
        resource.setDonationCount(dto.getDonationCount());
        resource.setSuccessfulDonations(dto.getSuccessfulDonations());
    }

    private SurrogateResourceDTO convertToSurrogateDTO(SurrogateResource resource) {
        SurrogateResourceDTO dto = new SurrogateResourceDTO();
        // Copy base fields
        dto.setId(resource.getId());
        dto.setName(resource.getName());
        dto.setStatus(resource.getStatus());
        dto.setLocation(resource.getLocation());
        dto.setCost(resource.getCost());
        dto.setQuality(resource.getQuality());
        dto.setDescription(resource.getDescription());
        dto.setTags(resource.getTags());
        dto.setDocumentUrls(resource.getDocumentUrls());
        dto.setNotes(resource.getNotes());
        dto.setAvailableFrom(resource.getAvailableFrom());
        dto.setAvailableTo(resource.getAvailableTo());
        dto.setRiskLevel(resource.getRiskLevel());

        // Copy surrogate-specific fields
        dto.setBirthDate(resource.getBirthDate());
        dto.setBloodType(resource.getBloodType());
        dto.setEthnicity(resource.getEthnicity());
        dto.setEducation(resource.getEducation());
        dto.setHeight(resource.getHeight());
        dto.setWeight(resource.getWeight());
        dto.setMedicalHistory(resource.getMedicalHistory());
        dto.setFamilyHistory(resource.getFamilyHistory());
        dto.setGeneticScreening(resource.getGeneticScreening());
        dto.setPregnancyCount(resource.getPregnancyCount());
        dto.setSuccessfulPregnancies(resource.getSuccessfulPregnancies());
        dto.setSurrogacyCount(resource.getSurrogacyCount());
        dto.setSuccessfulSurrogacies(resource.getSuccessfulSurrogacies());
        dto.setPregnancyHistory(resource.getPregnancyHistory());
        dto.setMaritalStatus(resource.getMaritalStatus());
        dto.setHasPartnerSupport(resource.getHasPartnerSupport());
        dto.setLifestyleInformation(resource.getLifestyleInformation());
        dto.setEmploymentStatus(resource.getEmploymentStatus());
        dto.setPsychologicalEvaluation(resource.getPsychologicalEvaluation());
        dto.setHasInsurance(resource.getHasInsurance());

        return dto;
    }

    private SurrogateResource convertToSurrogateEntity(SurrogateResourceDTO dto) {
        SurrogateResource resource = new SurrogateResource();
        updateSurrogateFields(resource, dto);
        return resource;
    }

    private void updateSurrogateFields(SurrogateResource resource, SurrogateResourceDTO dto) {
        // Update base fields first
        updateResourceFields(resource, dto);

        // Update surrogate-specific fields
        resource.setBirthDate(dto.getBirthDate());
        resource.setBloodType(dto.getBloodType());
        resource.setEthnicity(dto.getEthnicity());
        resource.setEducation(dto.getEducation());
        resource.setHeight(dto.getHeight());
        resource.setWeight(dto.getWeight());
        resource.setMedicalHistory(dto.getMedicalHistory());
        resource.setFamilyHistory(dto.getFamilyHistory());
        resource.setGeneticScreening(dto.getGeneticScreening());
        resource.setPregnancyCount(dto.getPregnancyCount());
        resource.setSuccessfulPregnancies(dto.getSuccessfulPregnancies());
        resource.setSurrogacyCount(dto.getSurrogacyCount());
        resource.setSuccessfulSurrogacies(dto.getSuccessfulSurrogacies());
        resource.setPregnancyHistory(dto.getPregnancyHistory());
        resource.setMaritalStatus(dto.getMaritalStatus());
        resource.setHasPartnerSupport(dto.getHasPartnerSupport());
        resource.setLifestyleInformation(dto.getLifestyleInformation());
        resource.setEmploymentStatus(dto.getEmploymentStatus());
        resource.setPsychologicalEvaluation(dto.getPsychologicalEvaluation());
        resource.setHasInsurance(dto.getHasInsurance());
    }

    private MedicalResourceDTO convertToMedicalDTO(MedicalResource resource) {
        MedicalResourceDTO dto = new MedicalResourceDTO();
        // Copy base fields
        dto.setId(resource.getId());
        dto.setName(resource.getName());
        dto.setStatus(resource.getStatus());
        dto.setLocation(resource.getLocation());
        dto.setCost(resource.getCost());
        dto.setQuality(resource.getQuality());
        dto.setDescription(resource.getDescription());
        dto.setTags(resource.getTags());
        dto.setDocumentUrls(resource.getDocumentUrls());
        dto.setNotes(resource.getNotes());
        dto.setAvailableFrom(resource.getAvailableFrom());
        dto.setAvailableTo(resource.getAvailableTo());
        dto.setRiskLevel(resource.getRiskLevel());

        // Copy medical-specific fields
        dto.setResourceType(resource.getResourceType());
        dto.setSpecialization(resource.getSpecialization());
        dto.setLicenseNumber(resource.getLicenseNumber());
        dto.setAccreditation(resource.getAccreditation());
        dto.setCapacity(resource.getCapacity());
        dto.setCurrentOccupancy(resource.getCurrentOccupancy());
        dto.setFacilities(resource.getFacilities());
        dto.setEquipment(resource.getEquipment());
        dto.setSuccessRate(resource.getSuccessRate());
        dto.setExperienceYears(resource.getExperienceYears());
        dto.setCertifications(resource.getCertifications());
        dto.setHasEmergencySupport(resource.getHasEmergencySupport());
        dto.setInsuranceInformation(resource.getInsuranceInformation());
        dto.setOperatingHours(resource.getOperatingHours());
        dto.setContactPerson(resource.getContactPerson());
        dto.setContactPhone(resource.getContactPhone());
        dto.setContactEmail(resource.getContactEmail());

        return dto;
    }

    private MedicalResource convertToMedicalEntity(MedicalResourceDTO dto) {
        MedicalResource resource = new MedicalResource();
        updateMedicalFields(resource, dto);
        return resource;
    }

    private void updateMedicalFields(MedicalResource resource, MedicalResourceDTO dto) {
        // Update base fields first
        updateResourceFields(resource, dto);

        // Update medical-specific fields
        resource.setResourceType(dto.getResourceType());
        resource.setSpecialization(dto.getSpecialization());
        resource.setLicenseNumber(dto.getLicenseNumber());
        resource.setAccreditation(dto.getAccreditation());
        resource.setCapacity(dto.getCapacity());
        resource.setCurrentOccupancy(dto.getCurrentOccupancy());
        resource.setFacilities(dto.getFacilities());
        resource.setEquipment(dto.getEquipment());
        resource.setSuccessRate(dto.getSuccessRate());
        resource.setExperienceYears(dto.getExperienceYears());
        resource.setCertifications(dto.getCertifications());
        resource.setHasEmergencySupport(dto.getHasEmergencySupport());
        resource.setInsuranceInformation(dto.getInsuranceInformation());
        resource.setOperatingHours(dto.getOperatingHours());
        resource.setContactPerson(dto.getContactPerson());
        resource.setContactPhone(dto.getContactPhone());
        resource.setContactEmail(dto.getContactEmail());
    }

    private PostnatalResourceDTO convertToPostnatalDTO(PostnatalResource resource) {
        PostnatalResourceDTO dto = new PostnatalResourceDTO();
        // Copy base fields
        dto.setId(resource.getId());
        dto.setName(resource.getName());
        dto.setStatus(resource.getStatus());
        dto.setLocation(resource.getLocation());
        dto.setCost(resource.getCost());
        dto.setQuality(resource.getQuality());
        dto.setDescription(resource.getDescription());
        dto.setTags(resource.getTags());
        dto.setDocumentUrls(resource.getDocumentUrls());
        dto.setNotes(resource.getNotes());
        dto.setAvailableFrom(resource.getAvailableFrom());
        dto.setAvailableTo(resource.getAvailableTo());
        dto.setRiskLevel(resource.getRiskLevel());

        // Copy postnatal-specific fields
        dto.setResourceType(resource.getResourceType());
        dto.setRoomCount(resource.getRoomCount());
        dto.setCurrentOccupancy(resource.getCurrentOccupancy());
        dto.setFacilities(resource.getFacilities());
        dto.setServices(resource.getServices());
        dto.setLicenseNumber(resource.getLicenseNumber());
        dto.setAccreditation(resource.getAccreditation());
        dto.setMedicalSupport(resource.getMedicalSupport());
        dto.setNutritionServices(resource.getNutritionServices());
        dto.setCareServices(resource.getCareServices());
        dto.setHasNursery(resource.getHasNursery());
        dto.setHasEmergencySupport(resource.getHasEmergencySupport());
        dto.setInsuranceInformation(resource.getInsuranceInformation());
        dto.setOperatingHours(resource.getOperatingHours());
        dto.setContactPerson(resource.getContactPerson());
        dto.setContactPhone(resource.getContactPhone());
        dto.setContactEmail(resource.getContactEmail());
        dto.setRoomTypes(resource.getRoomTypes());
        dto.setAmenities(resource.getAmenities());

        return dto;
    }

    private PostnatalResource convertToPostnatalEntity(PostnatalResourceDTO dto) {
        PostnatalResource resource = new PostnatalResource();
        updatePostnatalFields(resource, dto);
        return resource;
    }

    private void updatePostnatalFields(PostnatalResource resource, PostnatalResourceDTO dto) {
        // Update base fields first
        updateResourceFields(resource, dto);

        // Update postnatal-specific fields
        resource.setResourceType(dto.getResourceType());
        resource.setRoomCount(dto.getRoomCount());
        resource.setCurrentOccupancy(dto.getCurrentOccupancy());
        resource.setFacilities(dto.getFacilities());
        resource.setServices(dto.getServices());
        resource.setLicenseNumber(dto.getLicenseNumber());
        resource.setAccreditation(dto.getAccreditation());
        resource.setMedicalSupport(dto.getMedicalSupport());
        resource.setNutritionServices(dto.getNutritionServices());
        resource.setCareServices(dto.getCareServices());
        resource.setHasNursery(dto.getHasNursery());
        resource.setHasEmergencySupport(dto.getHasEmergencySupport());
        resource.setInsuranceInformation(dto.getInsuranceInformation());
        resource.setOperatingHours(dto.getOperatingHours());
        resource.setContactPerson(dto.getContactPerson());
        resource.setContactPhone(dto.getContactPhone());
        resource.setContactEmail(dto.getContactEmail());
        resource.setRoomTypes(dto.getRoomTypes());
        resource.setAmenities(dto.getAmenities());
    }

    private HumanResourceDTO convertToHumanDTO(HumanResource resource) {
        HumanResourceDTO dto = new HumanResourceDTO();
        // Copy base fields
        dto.setId(resource.getId());
        dto.setName(resource.getName());
        dto.setStatus(resource.getStatus());
        dto.setLocation(resource.getLocation());
        dto.setCost(resource.getCost());
        dto.setQuality(resource.getQuality());
        dto.setDescription(resource.getDescription());
        dto.setTags(resource.getTags());
        dto.setDocumentUrls(resource.getDocumentUrls());
        dto.setNotes(resource.getNotes());
        dto.setAvailableFrom(resource.getAvailableFrom());
        dto.setAvailableTo(resource.getAvailableTo());
        dto.setRiskLevel(resource.getRiskLevel());

        // Copy human-specific fields
        dto.setResourceType(resource.getResourceType());
        dto.setSpecialization(resource.getSpecialization());
        dto.setLicenseNumber(resource.getLicenseNumber());
        dto.setBirthDate(resource.getBirthDate());
        dto.setGender(resource.getGender());
        dto.setEducation(resource.getEducation());
        dto.setQualifications(resource.getQualifications());
        dto.setCertifications(resource.getCertifications());
        dto.setExperienceYears(resource.getExperienceYears());
        dto.setLanguages(resource.getLanguages());
        dto.setExpertise(resource.getExpertise());
        dto.setSuccessRate(resource.getSuccessRate());
        dto.setWorkHistory(resource.getWorkHistory());
        dto.setAvailability(resource.getAvailability());
        dto.setWorkingHours(resource.getWorkingHours());
        dto.setContactPhone(resource.getContactPhone());
        dto.setContactEmail(resource.getContactEmail());
        dto.setPerformanceMetrics(resource.getPerformanceMetrics());
        dto.setIsFullTime(resource.getIsFullTime());

        return dto;
    }

    private HumanResource convertToHumanEntity(HumanResourceDTO dto) {
        HumanResource resource = new HumanResource();
        updateHumanFields(resource, dto);
        return resource;
    }

    private void updateHumanFields(HumanResource resource, HumanResourceDTO dto) {
        // Update base fields first
        updateResourceFields(resource, dto);

        // Update human-specific fields
        resource.setResourceType(dto.getResourceType());
        resource.setSpecialization(dto.getSpecialization());
        resource.setLicenseNumber(dto.getLicenseNumber());
        resource.setBirthDate(dto.getBirthDate());
        resource.setGender(dto.getGender());
        resource.setEducation(dto.getEducation());
        resource.setQualifications(dto.getQualifications());
        resource.setCertifications(dto.getCertifications());
        resource.setExperienceYears(dto.getExperienceYears());
        resource.setLanguages(dto.getLanguages());
        resource.setExpertise(dto.getExpertise());
        resource.setSuccessRate(dto.getSuccessRate());
        resource.setWorkHistory(dto.getWorkHistory());
        resource.setAvailability(dto.getAvailability());
        resource.setWorkingHours(dto.getWorkingHours());
        resource.setContactPhone(dto.getContactPhone());
        resource.setContactEmail(dto.getContactEmail());
        resource.setPerformanceMetrics(dto.getPerformanceMetrics());
        resource.setIsFullTime(dto.getIsFullTime());
    }

    // Similar conversion methods would be implemented for other resource types
    // (SurrogateResource, MedicalResource, PostnatalResource, HumanResource)
    // Following the same pattern as the donor resource conversion methods
} 
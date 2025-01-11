package com.arterp.business.service;

import com.arterp.business.dto.BusinessProcessDTO;
import com.arterp.business.dto.ProcessStageDTO;
import com.arterp.business.dto.StageTaskDTO;
import com.arterp.business.entity.BusinessProcess;
import com.arterp.business.entity.ProcessStage;
import com.arterp.business.entity.StageTask;
import com.arterp.business.repository.BusinessProcessRepository;
import com.arterp.common.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BusinessProcessService {
    private final BusinessProcessRepository businessProcessRepository;

    @Transactional(readOnly = true)
    public Page<BusinessProcessDTO> getProcessesByCustomerId(Long customerId, String search, Pageable pageable) {
        if (search != null && !search.isEmpty()) {
            return businessProcessRepository.searchByCustomerId(customerId, search, pageable)
                    .map(this::convertToDTO);
        }
        return businessProcessRepository.findByCustomerId(customerId, pageable)
                .map(this::convertToDTO);
    }

    @Transactional(readOnly = true)
    public BusinessProcessDTO getProcessById(Long id) {
        return convertToDTO(findProcessById(id));
    }

    @Transactional
    public BusinessProcessDTO createProcess(BusinessProcessDTO processDTO) {
        BusinessProcess process = convertToEntity(processDTO);
        return convertToDTO(businessProcessRepository.save(process));
    }

    @Transactional
    public BusinessProcessDTO updateProcess(Long id, BusinessProcessDTO processDTO) {
        BusinessProcess existingProcess = findProcessById(id);
        updateProcessFields(existingProcess, processDTO);
        return convertToDTO(businessProcessRepository.save(existingProcess));
    }

    @Transactional
    public void deleteProcess(Long id) {
        BusinessProcess process = findProcessById(id);
        process.setDeleted(true);
        businessProcessRepository.save(process);
    }

    @Transactional(readOnly = true)
    public Map<String, Long> getProcessTypeDistribution() {
        return businessProcessRepository.countByProcessType().stream()
                .collect(Collectors.toMap(
                        row -> (String) row[0],
                        row -> (Long) row[1]
                ));
    }

    @Transactional(readOnly = true)
    public Map<String, Long> getStatusDistribution() {
        return businessProcessRepository.countByStatus().stream()
                .collect(Collectors.toMap(
                        row -> (String) row[0],
                        row -> (Long) row[1]
                ));
    }

    @Transactional(readOnly = true)
    public Map<String, Long> getRiskLevelDistribution() {
        return businessProcessRepository.countByRiskLevel().stream()
                .collect(Collectors.toMap(
                        row -> (String) row[0],
                        row -> (Long) row[1]
                ));
    }

    private BusinessProcess findProcessById(Long id) {
        return businessProcessRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Business process not found with id: " + id));
    }

    private BusinessProcessDTO convertToDTO(BusinessProcess process) {
        BusinessProcessDTO dto = new BusinessProcessDTO();
        dto.setId(process.getId());
        dto.setCustomerId(process.getCustomerId());
        dto.setProcessType(process.getProcessType());
        dto.setStatus(process.getStatus());
        dto.setStartDate(process.getStartDate());
        dto.setExpectedEndDate(process.getExpectedEndDate());
        dto.setActualEndDate(process.getActualEndDate());
        dto.setTotalBudget(process.getTotalBudget());
        dto.setCurrentSpent(process.getCurrentSpent());
        dto.setAssignedResourceIds(process.getAssignedResourceIds());
        dto.setNotes(process.getNotes());
        dto.setRiskLevel(process.getRiskLevel());
        dto.setDocumentUrls(process.getDocumentUrls());
        dto.setStages(process.getStages().stream()
                .map(this::convertToStageDTO)
                .collect(Collectors.toList()));
        return dto;
    }

    private ProcessStageDTO convertToStageDTO(ProcessStage stage) {
        ProcessStageDTO dto = new ProcessStageDTO();
        dto.setId(stage.getId());
        dto.setName(stage.getName());
        dto.setStatus(stage.getStatus());
        dto.setSequence(stage.getSequence());
        dto.setStartDate(stage.getStartDate());
        dto.setExpectedEndDate(stage.getExpectedEndDate());
        dto.setActualEndDate(stage.getActualEndDate());
        dto.setBudget(stage.getBudget());
        dto.setSpent(stage.getSpent());
        dto.setAssignedResourceIds(stage.getAssignedResourceIds());
        dto.setNotes(stage.getNotes());
        dto.setDocumentUrls(stage.getDocumentUrls());
        dto.setTasks(stage.getTasks().stream()
                .map(this::convertToTaskDTO)
                .collect(Collectors.toList()));
        return dto;
    }

    private StageTaskDTO convertToTaskDTO(StageTask task) {
        StageTaskDTO dto = new StageTaskDTO();
        dto.setId(task.getId());
        dto.setName(task.getName());
        dto.setStatus(task.getStatus());
        dto.setSequence(task.getSequence());
        dto.setStartDate(task.getStartDate());
        dto.setExpectedEndDate(task.getExpectedEndDate());
        dto.setActualEndDate(task.getActualEndDate());
        dto.setBudget(task.getBudget());
        dto.setSpent(task.getSpent());
        dto.setAssignedResourceIds(task.getAssignedResourceIds());
        dto.setDescription(task.getDescription());
        dto.setNotes(task.getNotes());
        dto.setDocumentUrls(task.getDocumentUrls());
        dto.setTaskType(task.getTaskType());
        dto.setResult(task.getResult());
        dto.setNextAction(task.getNextAction());
        return dto;
    }

    private BusinessProcess convertToEntity(BusinessProcessDTO dto) {
        BusinessProcess process = new BusinessProcess();
        updateProcessFields(process, dto);
        return process;
    }

    private void updateProcessFields(BusinessProcess process, BusinessProcessDTO dto) {
        process.setCustomerId(dto.getCustomerId());
        process.setProcessType(dto.getProcessType());
        process.setStatus(dto.getStatus());
        process.setStartDate(dto.getStartDate());
        process.setExpectedEndDate(dto.getExpectedEndDate());
        process.setActualEndDate(dto.getActualEndDate());
        process.setTotalBudget(dto.getTotalBudget());
        process.setCurrentSpent(dto.getCurrentSpent());
        process.setAssignedResourceIds(dto.getAssignedResourceIds());
        process.setNotes(dto.getNotes());
        process.setRiskLevel(dto.getRiskLevel());
        process.setDocumentUrls(dto.getDocumentUrls());

        // Update stages
        process.getStages().clear();
        if (dto.getStages() != null) {
            process.getStages().addAll(dto.getStages().stream()
                    .map(stageDTO -> {
                        ProcessStage stage = new ProcessStage();
                        updateStageFields(stage, stageDTO);
                        stage.setBusinessProcess(process);
                        return stage;
                    })
                    .collect(Collectors.toList()));
        }
    }

    private void updateStageFields(ProcessStage stage, ProcessStageDTO dto) {
        stage.setName(dto.getName());
        stage.setStatus(dto.getStatus());
        stage.setSequence(dto.getSequence());
        stage.setStartDate(dto.getStartDate());
        stage.setExpectedEndDate(dto.getExpectedEndDate());
        stage.setActualEndDate(dto.getActualEndDate());
        stage.setBudget(dto.getBudget());
        stage.setSpent(dto.getSpent());
        stage.setAssignedResourceIds(dto.getAssignedResourceIds());
        stage.setNotes(dto.getNotes());
        stage.setDocumentUrls(dto.getDocumentUrls());

        // Update tasks
        stage.getTasks().clear();
        if (dto.getTasks() != null) {
            stage.getTasks().addAll(dto.getTasks().stream()
                    .map(taskDTO -> {
                        StageTask task = new StageTask();
                        updateTaskFields(task, taskDTO);
                        task.setStage(stage);
                        return task;
                    })
                    .collect(Collectors.toList()));
        }
    }

    private void updateTaskFields(StageTask task, StageTaskDTO dto) {
        task.setName(dto.getName());
        task.setStatus(dto.getStatus());
        task.setSequence(dto.getSequence());
        task.setStartDate(dto.getStartDate());
        task.setExpectedEndDate(dto.getExpectedEndDate());
        task.setActualEndDate(dto.getActualEndDate());
        task.setBudget(dto.getBudget());
        task.setSpent(dto.getSpent());
        task.setAssignedResourceIds(dto.getAssignedResourceIds());
        task.setDescription(dto.getDescription());
        task.setNotes(dto.getNotes());
        task.setDocumentUrls(dto.getDocumentUrls());
        task.setTaskType(dto.getTaskType());
        task.setResult(dto.getResult());
        task.setNextAction(dto.getNextAction());
    }
} 
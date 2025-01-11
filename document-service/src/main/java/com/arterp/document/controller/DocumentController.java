package com.arterp.document.controller;

import com.arterp.common.dto.BaseResponse;
import com.arterp.document.dto.DocumentDTO;
import com.arterp.document.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/documents")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class DocumentController {
    private final DocumentService documentService;

    @GetMapping("/search")
    @PreAuthorize("hasRole('USER')")
    public BaseResponse<Page<DocumentDTO>> searchDocuments(
            @RequestParam(required = false, defaultValue = "") String search,
            Pageable pageable) {
        return BaseResponse.success(documentService.searchDocuments(search, pageable));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public BaseResponse<DocumentDTO> getDocument(@PathVariable Long id) {
        return BaseResponse.success(documentService.getDocumentById(id));
    }

    @PostMapping("/upload")
    @PreAuthorize("hasRole('USER')")
    public BaseResponse<DocumentDTO> uploadDocument(
            @RequestParam("file") MultipartFile file,
            @RequestParam String category,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String relatedEntityType,
            @RequestParam(required = false) Long relatedEntityId,
            @RequestParam String uploadedBy) {
        return BaseResponse.success(documentService.uploadDocument(
                file, category, description, relatedEntityType, relatedEntityId, uploadedBy));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public BaseResponse<Void> deleteDocument(@PathVariable Long id) {
        documentService.deleteDocument(id);
        return BaseResponse.success(null);
    }

    @GetMapping("/entity/{entityType}/{entityId}")
    @PreAuthorize("hasRole('USER')")
    public BaseResponse<List<DocumentDTO>> getDocumentsByEntity(
            @PathVariable String entityType,
            @PathVariable Long entityId) {
        return BaseResponse.success(documentService.getDocumentsByEntity(entityType, entityId));
    }

    @GetMapping("/entity/{entityType}/{entityId}/category/{category}")
    @PreAuthorize("hasRole('USER')")
    public BaseResponse<List<DocumentDTO>> getDocumentsByEntityAndCategory(
            @PathVariable String entityType,
            @PathVariable Long entityId,
            @PathVariable String category) {
        return BaseResponse.success(documentService.getDocumentsByEntityAndCategory(entityType, entityId, category));
    }

    @GetMapping("/stats/category")
    @PreAuthorize("hasRole('USER')")
    public BaseResponse<Map<String, Long>> getCategoryDistribution() {
        return BaseResponse.success(documentService.getCategoryDistribution());
    }

    @GetMapping("/stats/type")
    @PreAuthorize("hasRole('USER')")
    public BaseResponse<Map<String, Long>> getTypeDistribution() {
        return BaseResponse.success(documentService.getTypeDistribution());
    }

    @GetMapping("/stats/status")
    @PreAuthorize("hasRole('USER')")
    public BaseResponse<Map<String, Long>> getStatusDistribution() {
        return BaseResponse.success(documentService.getStatusDistribution());
    }
} 
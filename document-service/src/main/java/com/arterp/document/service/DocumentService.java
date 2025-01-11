package com.arterp.document.service;

import com.arterp.document.config.MinioConfig;
import com.arterp.document.dto.DocumentDTO;
import com.arterp.document.entity.Document;
import com.arterp.document.repository.DocumentRepository;
import io.minio.*;
import io.minio.http.Method;
import lombok.RequiredArgsConstructor;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DocumentService {
    private final DocumentRepository documentRepository;
    private final MinioClient minioClient;
    private final MinioConfig minioConfig;
    private final Tesseract tesseract;

    @Transactional(readOnly = true)
    public Page<DocumentDTO> searchDocuments(String search, Pageable pageable) {
        return documentRepository.search(search, pageable)
                .map(this::convertToDTO);
    }

    @Transactional(readOnly = true)
    public DocumentDTO getDocumentById(Long id) {
        Document document = documentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Document not found"));
        return convertToDTO(document);
    }

    @Transactional
    public DocumentDTO uploadDocument(MultipartFile file, String category, String description,
                                    String relatedEntityType, Long relatedEntityId, String uploadedBy) {
        try {
            String objectName = UUID.randomUUID().toString();
            String contentType = file.getContentType();

            // Upload to MinIO
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(minioConfig.getBucket())
                    .object(objectName)
                    .stream(file.getInputStream(), file.getSize(), -1)
                    .contentType(contentType)
                    .build());

            // Create document entity
            Document document = new Document();
            document.setName(file.getOriginalFilename());
            document.setType(getFileType(file.getOriginalFilename()));
            document.setContentType(contentType);
            document.setBucketName(minioConfig.getBucket());
            document.setObjectName(objectName);
            document.setSize(file.getSize());
            document.setDescription(description);
            document.setCategory(category);
            document.setStatus("Active");
            document.setRelatedEntityType(relatedEntityType);
            document.setRelatedEntityId(relatedEntityId);
            document.setUploadedBy(uploadedBy);

            // Perform OCR if it's an image
            if (contentType != null && contentType.startsWith("image/")) {
                BufferedImage image = ImageIO.read(file.getInputStream());
                String ocrText = performOCR(image);
                document.setOcrContent(ocrText);
                document.setProcessedAt(LocalDateTime.now());
                document.setProcessedBy("OCR-System");
            }

            document = documentRepository.save(document);
            return convertToDTO(document);
        } catch (Exception e) {
            throw new RuntimeException("Failed to upload document", e);
        }
    }

    @Transactional
    public void deleteDocument(Long id) {
        Document document = documentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Document not found"));

        try {
            // Delete from MinIO
            minioClient.removeObject(RemoveObjectArgs.builder()
                    .bucket(document.getBucketName())
                    .object(document.getObjectName())
                    .build());

            // Delete from database
            documentRepository.delete(document);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete document", e);
        }
    }

    @Transactional(readOnly = true)
    public List<DocumentDTO> getDocumentsByEntity(String entityType, Long entityId) {
        return documentRepository.findByRelatedEntity(entityType, entityId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<DocumentDTO> getDocumentsByEntityAndCategory(String entityType, Long entityId, String category) {
        return documentRepository.findByRelatedEntityAndCategory(entityType, entityId, category)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Map<String, Long> getCategoryDistribution() {
        return documentRepository.countByCategory().stream()
                .collect(Collectors.toMap(
                        row -> (String) row[0],
                        row -> (Long) row[1]
                ));
    }

    @Transactional(readOnly = true)
    public Map<String, Long> getTypeDistribution() {
        return documentRepository.countByType().stream()
                .collect(Collectors.toMap(
                        row -> (String) row[0],
                        row -> (Long) row[1]
                ));
    }

    @Transactional(readOnly = true)
    public Map<String, Long> getStatusDistribution() {
        return documentRepository.countByStatus().stream()
                .collect(Collectors.toMap(
                        row -> (String) row[0],
                        row -> (Long) row[1]
                ));
    }

    private DocumentDTO convertToDTO(Document document) {
        DocumentDTO dto = new DocumentDTO();
        dto.setId(document.getId());
        dto.setName(document.getName());
        dto.setType(document.getType());
        dto.setContentType(document.getContentType());
        dto.setBucketName(document.getBucketName());
        dto.setObjectName(document.getObjectName());
        dto.setSize(document.getSize());
        dto.setDescription(document.getDescription());
        dto.setCategory(document.getCategory());
        dto.setStatus(document.getStatus());
        dto.setRelatedEntityType(document.getRelatedEntityType());
        dto.setRelatedEntityId(document.getRelatedEntityId());
        dto.setOcrContent(document.getOcrContent());
        dto.setUploadedBy(document.getUploadedBy());
        dto.setUploadedAt(document.getCreatedAt());
        dto.setProcessedAt(document.getProcessedAt());
        dto.setProcessedBy(document.getProcessedBy());
        dto.setTags(document.getTags());

        // Generate presigned URL for downloading
        try {
            String url = minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.GET)
                            .bucket(document.getBucketName())
                            .object(document.getObjectName())
                            .expiry(1, TimeUnit.HOURS)
                            .build()
            );
            dto.setUrl(url);
        } catch (Exception e) {
            // Log error but don't fail the conversion
            e.printStackTrace();
        }

        return dto;
    }

    private String getFileType(String filename) {
        int lastDotIndex = filename.lastIndexOf('.');
        if (lastDotIndex > 0) {
            return filename.substring(lastDotIndex + 1).toUpperCase();
        }
        return "UNKNOWN";
    }

    private String performOCR(BufferedImage image) throws TesseractException {
        return tesseract.doOCR(image);
    }
} 
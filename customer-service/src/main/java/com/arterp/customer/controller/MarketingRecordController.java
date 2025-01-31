package com.arterp.customer.controller;

import com.arterp.customer.dto.MarketingRecordDTO;
import com.arterp.customer.service.MarketingRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/marketing-records")
@RequiredArgsConstructor
public class MarketingRecordController {
    private final MarketingRecordService marketingRecordService;

    @PostMapping
    public ResponseEntity<MarketingRecordDTO> createMarketingRecord(@RequestBody MarketingRecordDTO recordDTO) {
        return ResponseEntity.ok(marketingRecordService.createMarketingRecord(recordDTO));
    }

    @GetMapping("/business/{businessId}")
    public ResponseEntity<List<MarketingRecordDTO>> getMarketingRecordsByBusinessId(@PathVariable Long businessId) {
        return ResponseEntity.ok(marketingRecordService.getMarketingRecordsByBusinessId(businessId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MarketingRecordDTO> updateMarketingRecord(
            @PathVariable Long id,
            @RequestBody MarketingRecordDTO recordDTO) {
        return ResponseEntity.ok(marketingRecordService.updateMarketingRecord(id, recordDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMarketingRecord(@PathVariable Long id) {
        marketingRecordService.deleteMarketingRecord(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/statistics/channel")
    public ResponseEntity<Map<String, Long>> countByChannel() {
        return ResponseEntity.ok(marketingRecordService.countByChannel());
    }

    @GetMapping("/statistics/conversion-source")
    public ResponseEntity<Map<String, Long>> countByConversionSource() {
        return ResponseEntity.ok(marketingRecordService.countByConversionSource());
    }
} 
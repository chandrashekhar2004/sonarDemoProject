package com.simplifyvms.config.controller;

import com.simplifyvms.config.dto.GlobalConfigDTO;
import com.simplifyvms.config.entity.GlobalConfig;
import com.simplifyvms.config.service.GlobalConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller for managing Global Configs.
 */
@RestController
@RequestMapping("/api/global-configs")
public class GlobalConfigController {

    private final GlobalConfigService globalConfigService;

    @Autowired
    public GlobalConfigController(GlobalConfigService globalConfigService) {
        this.globalConfigService = globalConfigService;
    }

    @GetMapping
    public ResponseEntity<List<GlobalConfig>> getAllGlobalConfigs() {
        List<GlobalConfig> configs = globalConfigService.findAll();
        return ResponseEntity.ok(configs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GlobalConfig> getGlobalConfigById(@PathVariable String id) {
        Optional<GlobalConfig> config = globalConfigService.findById(id);
        return config.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<GlobalConfig> createGlobalConfig(@RequestBody GlobalConfigDTO globalConfigDTO) {
        GlobalConfig savedConfig = globalConfigService.save(globalConfigDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedConfig);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GlobalConfig> updateGlobalConfig(@PathVariable String id, @RequestBody GlobalConfigDTO globalConfigDTO) {
        GlobalConfig updatedConfig = globalConfigService.update(id, globalConfigDTO);
        return ResponseEntity.ok(updatedConfig);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGlobalConfig(@PathVariable String id) {
        globalConfigService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

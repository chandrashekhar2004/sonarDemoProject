package com.simplifyvms.config.controller;



import com.simplifyvms.config.service.GlobalConfigService;
import com.simplifyvms.config.entity.GlobalConfig;
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
    public List<GlobalConfig> getAllGlobalConfigs() {
        return globalConfigService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GlobalConfig> getGlobalConfigById(@PathVariable Long id) {
        Optional<GlobalConfig> config = globalConfigService.findById(id);
        return config.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<GlobalConfig> createGlobalConfig(@RequestBody GlobalConfig globalConfig) {
        GlobalConfig savedConfig = globalConfigService.save(globalConfig);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedConfig);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GlobalConfig> updateGlobalConfig(@PathVariable Long id, @RequestBody GlobalConfig globalConfig) {
        globalConfig.setId(String.valueOf(id));
        GlobalConfig updatedConfig = globalConfigService.save(globalConfig);
        return ResponseEntity.ok(updatedConfig);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGlobalConfig(@PathVariable Long id) {
        globalConfigService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

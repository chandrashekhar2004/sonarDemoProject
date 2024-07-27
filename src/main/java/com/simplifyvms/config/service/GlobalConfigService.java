package com.simplifyvms.config.service;
import com.simplifyvms.config.entity.GlobalConfig;
import com.simplifyvms.config.repository.GlobalConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class GlobalConfigService {

    private final GlobalConfigRepository globalConfigRepository;

    @Autowired
    public GlobalConfigService(GlobalConfigRepository globalConfigRepository) {
        this.globalConfigRepository = globalConfigRepository;
    }

    public List<GlobalConfig> findAll() {
        return globalConfigRepository.findAll();
    }

    public Optional<GlobalConfig> findById(Long id) {
        return globalConfigRepository.findById(id);
    }

    public GlobalConfig save(GlobalConfig globalConfig) {
        return globalConfigRepository.save(globalConfig);
    }

    public void deleteById(Long id) {
        globalConfigRepository.deleteById(id);
    }
}

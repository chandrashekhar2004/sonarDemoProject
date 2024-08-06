package com.simplifyvms.config.service.impl;

import com.simplifyvms.config.dto.GlobalConfigDTO;
import com.simplifyvms.config.entity.GlobalConfig;
import com.simplifyvms.config.repository.GlobalConfigRepository;
import com.simplifyvms.config.service.GlobalConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class GlobalConfigServiceImpl implements GlobalConfigService {

    private final GlobalConfigRepository globalConfigRepository;

    @Autowired
    public GlobalConfigServiceImpl(GlobalConfigRepository globalConfigRepository) {
        this.globalConfigRepository = globalConfigRepository;
    }

    @Override
    public List<GlobalConfig> findAll() {
        return globalConfigRepository.findAll();
    }

    @Override
    public Optional<GlobalConfig> findById(String id) {
        return globalConfigRepository.findById(Long.valueOf(id));
    }

    @Override
    public GlobalConfig save(GlobalConfigDTO globalConfigDTO) {
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setKey(globalConfigDTO.getKey());
        globalConfig.setValue(globalConfigDTO.getValue());
        globalConfig.setActive(globalConfigDTO.isActive());
        globalConfig.setDeleted(globalConfigDTO.isDeleted());
        globalConfig.setRefId(globalConfigDTO.getRefId());
        globalConfig.setExpiresOn(globalConfigDTO.getExpiresOn());
        globalConfig.setCreatedBy(globalConfigDTO.getCreatedBy());
        globalConfig.setCreatedOn(new Timestamp(System.currentTimeMillis()));
        return globalConfigRepository.save(globalConfig);
    }

    @Override
    public GlobalConfig update(String id, GlobalConfigDTO globalConfigDTO) {
        Optional<GlobalConfig> optionalGlobalConfig = globalConfigRepository.findById(Long.valueOf(id));
        if (optionalGlobalConfig.isPresent()) {
            GlobalConfig globalConfig = optionalGlobalConfig.get();
            globalConfig.setKey(globalConfigDTO.getKey());
            globalConfig.setValue(globalConfigDTO.getValue());
            globalConfig.setActive(globalConfigDTO.isActive());
            globalConfig.setDeleted(globalConfigDTO.isDeleted());
            globalConfig.setRefId(globalConfigDTO.getRefId());
            globalConfig.setExpiresOn(globalConfigDTO.getExpiresOn());
            globalConfig.setModifiedBy(globalConfigDTO.getModifiedBy());
            globalConfig.setModifiedOn(new Timestamp(System.currentTimeMillis()));
            return globalConfigRepository.save(globalConfig);
        } else {
            throw new RuntimeException("GlobalConfig not found with id " + id);
        }
    }

    @Override
    public void deleteById(String id) {
        globalConfigRepository.deleteById(Long.valueOf(id));
    }
}

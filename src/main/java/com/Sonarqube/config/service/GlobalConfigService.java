package com.simplifyvms.config.service;

import com.simplifyvms.config.dto.GlobalConfigDTO;
import com.simplifyvms.config.entity.GlobalConfig;

import java.util.List;
import java.util.Optional;

public interface GlobalConfigService {
    List<GlobalConfig> findAll();
    Optional<GlobalConfig> findById(String id);
    GlobalConfig save(GlobalConfigDTO globalConfigDTO);
    GlobalConfig update(String id, GlobalConfigDTO globalConfigDTO);
    void deleteById(String id);
}

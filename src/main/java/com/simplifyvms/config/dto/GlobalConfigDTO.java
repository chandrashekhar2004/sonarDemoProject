package com.simplifyvms.config.dto;

import lombok.Data;
import java.sql.Timestamp;

@Data
public class GlobalConfigDTO {
    private String key;
    private String value;
    private boolean active;
    private boolean deleted;
    private String refId;
    private Timestamp expiresOn;
    private String createdBy;
    private String modifiedBy;
}

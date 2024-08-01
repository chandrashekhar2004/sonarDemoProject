package com.simplifyvms.config.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.sql.Timestamp;

@Getter
@Setter
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProgramRequest {

    private String id;

    private String uniqueId;

    private String serviceType;

    private String name;

    private String contacts;

    private String description;

    private String status;

    private JsonNode data;

    private String domain;

    private boolean isActive;

    private String refId;

    private boolean isDeleted = false;

    private Timestamp createdOn;

    private Timestamp modifiedOn;

    private String createdBy;

    private String modifiedBy;
}


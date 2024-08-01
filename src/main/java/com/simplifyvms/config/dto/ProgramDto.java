package com.simplifyvms.config.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import com.simplifyvms.config.entity.Program;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProgramDto {

    private String id;


    private String serviceType;

    private String name;

    private String contacts;

    private String description;

    private String status;

    private JsonNode data;

    private String domain;

    private boolean isActive;


    public ProgramDto(Program program) {
        this.id = program.getId();
        this.serviceType = program.getServiceType();
        this.name = program.getName();
        this.contacts = program.getContacts();
        this.description = program.getDescription();
        this.status = program.getStatus();
        this.data = program.getData();
        this.domain = program.getDomain();
        this.isActive = program.isActive();
        // Set other fields as necessary
    }

//    private String refId;

//    private boolean isDeleted = false;

//    private Timestamp createdOn;

//    private Timestamp modifiedOn;

//    private String createdBy;

//    private String modifiedBy;
}



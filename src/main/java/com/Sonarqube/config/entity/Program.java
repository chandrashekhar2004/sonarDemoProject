package com.simplifyvms.config.entity;


import com.simplifyvms.config.converter.JsonNodeConverter;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Convert;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@Entity
@Table(name = "program")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Program  implements Serializable {

    private static final long serialVersionUID = -4439114469417994311L;

    @Id
    @GeneratedValue(generator = "UUID")
//    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
//    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @Column(name = "unique_id", length = 100, nullable = false)
    //    @GeneratedValue(generator = "UUID")
//    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String uniqueId;

    @Column(name = "service_type", length = 100, nullable = false)
    private String serviceType;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "contacts", length = 255, nullable = false)
    private String contacts;

    @Column(name = "description", length = 255, nullable = false)
    private String description;

    @Column(name = "status", length = 100, nullable = false)
    private String status;

    @Column(name = "data")
    @Convert(converter = JsonNodeConverter.class)
    private JsonNode data;

    @Column(name = "domain", length = 100, nullable = false)
    private String domain;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @Column(name = "ref_id", length = 100)
    private String refId;

    @Column(name = "is_deleted", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0")
    private boolean isDeleted = false;

    @Column(name = "created_on", columnDefinition = "TIMESTAMP DEFAULT NULL")
    private Timestamp createdOn;

    @Column(name = "modified_on", columnDefinition = "TIMESTAMP DEFAULT NULL")
    private Timestamp modifiedOn;

    @Column(name = "created_by", length = 100)
    private String createdBy;

    @Column(name = "modified_by", length = 100)
    private String modifiedBy;
}


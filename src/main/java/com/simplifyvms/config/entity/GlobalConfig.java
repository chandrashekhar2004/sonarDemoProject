package com.simplifyvms.config.entity;



import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Timestamp;

@Entity
@Table(name = "global_config")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GlobalConfig {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @Column(name = "`key`", nullable = false)
    private String key;

    @Column(name = "value", nullable = false)
    private String value;

    @JsonProperty("active")
    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @JsonProperty("deleted")
    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted;

    @Column(name = "ref_id")
    private String refId;

    @Column(name = "expires_on")
    private Timestamp expiresOn;

    @Column(name = "created_on")
    private Timestamp createdOn;

    @Column(name = "modified_on")
    private Timestamp modifiedOn;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "modified_by")
    private String modifiedBy;
}

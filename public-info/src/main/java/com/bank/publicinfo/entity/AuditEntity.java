package com.bank.publicinfo.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * Entity для таблицы "audit"
 */
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "audit", schema = "public_bank_information")
public class AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "entity_type")
    String entityType;

    @Column(name = "operation_type")
    String operationType;

    @Column(name = "created_by")
    String createdBy;

    @Column(name = "modified_by")
    String modifiedBy;

    @Column(name = "created_at")
    Timestamp createdAt;

    @Column(name = "modified_at")
    Timestamp modifiedAt;

    @Column(name = "new_entity_json")
    String newEntityJson;

    @Column(name = "entity_json")
    String entityJson;

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final AuditEntity audit = (AuditEntity) o;

        return id.equals(audit.id) &&
                entityType.equals(audit.entityType) &&
                operationType.equals(audit.operationType) &&
                createdBy.equals(audit.createdBy) &&
                modifiedBy.equals(audit.modifiedBy) &&
                createdAt.equals(audit.createdAt) &&
                modifiedAt.equals(audit.modifiedAt) &&
                newEntityJson.equals(audit.newEntityJson) &&
                entityJson.equals(audit.entityJson);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, entityType, operationType, createdBy, modifiedBy, createdAt,
                modifiedAt, newEntityJson, entityJson);
    }
}

package com.bank.history.entity;

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

/**
 * Entity для таблицы history.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "history", schema = "history")
public class HistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "transfer_audit_id")
    Long transferAuditId;

    @Column(name = "profile_audit_id")
    Long profileAuditId;

    @Column(name = "account_audit_id")
    Long accountAuditId;

    @Column(name = "anti_fraud_audit_id")
    Long antiFraudAuditId;

    @Column(name = "public_bank_info_audit_id")
    Long publicBankInfoAuditId;

    @Column(name = "authorization_audit_id")
    Long authorizationAuditId;
}

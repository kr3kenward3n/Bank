package com.bank.antifraud.entity;

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
import java.util.Objects;

/**
 * entity для таблицы suspicious_card_transfer
 */
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "suspicious_card_transfer", schema = "anti_fraud")
public class SuspiciousCardTransferEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "card_transfer_id")
    Long cardTransferId;

    @Column(name = "is_blocked")
    Boolean isBlocked;

    @Column(name = "is_suspicious")
    Boolean isSuspicious;

    @Column(name = "blocked_reason")
    String blockedReason;

    @Column(name = "suspicious_reason")
    String suspiciousReason;

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final SuspiciousCardTransferEntity transfer = (SuspiciousCardTransferEntity) o;

        return Objects.equals(id, transfer.id) &&
                Objects.equals(isBlocked, transfer.isBlocked) &&
                Objects.equals(isSuspicious, transfer.isSuspicious) &&
                Objects.equals(blockedReason, transfer.blockedReason) &&
                Objects.equals(cardTransferId, transfer.cardTransferId) &&
                Objects.equals(suspiciousReason, transfer.suspiciousReason);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cardTransferId, isBlocked, isSuspicious, blockedReason, suspiciousReason);
    }
}

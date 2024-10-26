package com.bank.antifraud.dto;

import com.bank.antifraud.entity.SuspiciousCardTransferEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

/**
 * Dto для {@link SuspiciousCardTransferEntity}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SuspiciousCardTransferDto implements Serializable {

    Long id;
    Long cardTransferId;
    Boolean isBlocked;
    Boolean isSuspicious;
    String blockedReason;
    String suspiciousReason;
}

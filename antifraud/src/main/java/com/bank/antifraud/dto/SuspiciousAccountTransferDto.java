package com.bank.antifraud.dto;

import com.bank.antifraud.entity.SuspiciousAccountTransferEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

/**
 * Dto для {@link SuspiciousAccountTransferEntity}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SuspiciousAccountTransferDto implements Serializable {

    Long id;
    Long accountTransferId;
    Boolean isBlocked;
    Boolean isSuspicious;
    String blockedReason;
    String suspiciousReason;
}

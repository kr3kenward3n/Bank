package com.bank.antifraud.dto;

import com.bank.antifraud.entity.SuspiciousPhoneTransferEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

/**
 * Dto для {@link SuspiciousPhoneTransferEntity}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SuspiciousPhoneTransferDto implements Serializable {

    Long id;
    Long phoneTransferId;
    Boolean isBlocked;
    Boolean isSuspicious;
    String blockedReason;
    String suspiciousReason;
}

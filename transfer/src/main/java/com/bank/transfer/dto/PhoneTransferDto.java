package com.bank.transfer.dto;

import com.bank.transfer.entity.PhoneTransferEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * ДТО {@link PhoneTransferEntity}
 */
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PhoneTransferDto implements Serializable {
    Long id;

    Long phoneNumber;

    BigDecimal amount;

    String purpose;
    Long accountDetailsId;
}

package com.bank.publicinfo.dto;

import com.bank.publicinfo.entity.BankDetailsEntity;
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
 * DTO для {@link BankDetailsEntity}
 */
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BankDetailsDto implements Serializable {
    Long id;

    Long bik;

    Long inn;

    Long kpp;

    BigDecimal corAccount;

    String city;
    String jointStockCompany;
    String name;
}

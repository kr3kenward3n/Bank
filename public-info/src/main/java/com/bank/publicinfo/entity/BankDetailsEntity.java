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
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Entity для таблицы "bank_details"
 */
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "bank_details", schema = "public_bank_information")
public class BankDetailsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "bik")
    Long bik;

    @Column(name = "inn")
    Long inn;

    @Column(name = "kpp")
    Long kpp;

    @Column(name = "cor_account")
    BigDecimal corAccount;

    @Column(name = "city")
    String city;

    @Column(name = "joint_stock_company")
    String jointStockCompany;

    @Column(name = "name")
    String name;

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final BankDetailsEntity bankDetails = (BankDetailsEntity) o;

        return id.equals(bankDetails.id) &&
                bik.equals(bankDetails.bik) &&
                inn.equals(bankDetails.inn) &&
                kpp.equals(bankDetails.kpp) &&
                corAccount.equals(bankDetails.corAccount) &&
                city.equals(bankDetails.city) &&
                jointStockCompany.equals(bankDetails.jointStockCompany) &&
                name.equals(bankDetails.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bik, inn, kpp, corAccount, city, jointStockCompany, name);
    }
}

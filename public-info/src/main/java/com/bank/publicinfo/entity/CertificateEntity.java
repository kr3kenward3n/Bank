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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Arrays;
import java.util.Objects;

/**
 * Entity для таблицы "certificate"
 */
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "certificate", schema = "public_bank_information")
public class CertificateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "photo")
    Byte[] photoCertificate;

    @ManyToOne
    @JoinColumn(name = "bank_details_id")
    BankDetailsEntity bankDetails;

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final CertificateEntity certificate = (CertificateEntity) o;

        return id.equals(certificate.id) &&
                Arrays.equals(photoCertificate, certificate.photoCertificate) &&
                bankDetails.equals(certificate.bankDetails);
    }

    @Override
    public int hashCode() {

        int result = Objects.hash(id, bankDetails);

        result = 31 * result + Arrays.hashCode(photoCertificate);

        return result;
    }
}

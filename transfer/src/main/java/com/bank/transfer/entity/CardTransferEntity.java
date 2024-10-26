package com.bank.transfer.entity;

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
 * Entity для таблицы card_transfer
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "card_transfer", schema = "transfer")
public class CardTransferEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "card_number")
    Long cardNumber;

    @Column(name = "amount")
    BigDecimal amount;

    @Column(name = "purpose")
    String purpose;

    @Column(name = "account_details_id")
    Long accountDetailsId;

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }

        if (!(o instanceof CardTransferEntity cardTransfer)) {
            return false;
        }

        return Objects.equals(getId(), cardTransfer.getId()) && Objects.equals(getCardNumber(),
                cardTransfer.getCardNumber()) && Objects.equals(getAmount(),
                cardTransfer.getAmount()) && Objects.equals(getPurpose(),
                cardTransfer.getPurpose()) && Objects.equals(getAccountDetailsId(),
                cardTransfer.getAccountDetailsId()
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCardNumber(), getAmount(), getPurpose(), getAccountDetailsId());
    }
}

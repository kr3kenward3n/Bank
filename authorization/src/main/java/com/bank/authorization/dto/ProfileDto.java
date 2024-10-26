package com.bank.authorization.dto;

import java.io.Serializable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProfileDto implements Serializable {

    Long id;

    Long phoneNumber;

    String email;

    String nameOnCard;

    Long inn;

    Long snils;

    PassportDto passport;

    ActualRegistrationDto actualRegistration;
}

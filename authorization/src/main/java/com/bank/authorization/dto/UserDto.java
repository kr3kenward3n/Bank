package com.bank.authorization.dto;

import com.bank.authorization.repository.entity.UserEntity;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.AccessLevel;

import lombok.experimental.FieldDefaults;

/**
 * DTO для {@link UserEntity}
 */
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto implements Serializable {

    Long id;

    String role;

    String password;

    Long profileId;
}

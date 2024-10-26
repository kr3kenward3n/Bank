package com.bank.authorization.service;

import com.bank.authorization.dto.UserDto;
import com.bank.authorization.repository.entity.UserEntity;

import java.util.List;

/**
 * Сервис для {@link UserDto} и {@link UserEntity}
 */
public interface UserService {

    /**
     * @param id технический идентификатор {@link UserEntity}
     * @return {@link UserDto}
     */
    UserDto findById(Long id);

    /**
     * @param user {@link UserDto}
     * @return {@link UserDto}
     */
    UserDto save(UserDto user);

    /**
     * @param id   технический идентификатор {@link UserEntity}
     * @param user {@link UserDto}
     * @return {@link UserDto}
     */
    UserDto update(Long id, UserDto user);

    /**
     * @param ids лист технических идентификаторов {@link UserEntity}
     * @return {@link UserDto}
     */
    List<UserDto> findAllByIds(List<Long> ids);
}

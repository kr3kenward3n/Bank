package com.bank.authorization.service;

import com.bank.authorization.dto.UserDto;
import com.bank.authorization.repository.entity.UserEntity;
import com.bank.authorization.mapper.UserMapper;
import com.bank.authorization.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * Реализация {@link UserService}
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final static String ENTITY_CONFLICT_MESSAGE = "Не был найден пользователь с ID ";
    private final UserMapper mapper;
    private final UserRepository repository;

    /**
     * @param id технический идентификатор {@link UserEntity}
     * @return {@link UserDto}
     */
    @Override
    public UserDto findById(Long id) {
        final UserEntity user = repository.findById(id).
                orElseThrow(
                        () -> getEntityException(id)
                );
        return mapper.toDTO(user);
    }

    /**
     * @param userDto {@link UserDto}
     * @return {@link UserDto}
     */
    @Override
    @Transactional
    public UserDto save(UserDto userDto) {

        final UserEntity user = mapper.toEntity(userDto);

        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

        final UserEntity saved = repository.save(user);

        return mapper.toDTO(saved);
    }

    /**
     * @param id      технический идентификатор {@link UserEntity}
     * @param userDto {@link UserDto}
     * @return {@link UserDto}
     */
    @Override
    @Transactional
    public UserDto update(Long id, UserDto userDto) {

        final UserEntity user = repository.findById(id)
                .orElseThrow(
                        () -> getEntityException(id)
                );
        final UserEntity updatedUser = repository.save(mapper.mergeToEntity(userDto, user));
        return mapper.toDTO(updatedUser);
    }

    /**
     * @param ids лист технических идентификаторов {@link UserEntity}
     * @return {@link UserDto}
     */
    @Override
    public List<UserDto> findAllByIds(List<Long> ids) {
        final List<UserEntity> users = ids.stream()
                .map(id -> repository.findById(id)
                        .orElseThrow(
                                () -> getEntityException(id))
                )
                .toList();

        return mapper.toDtoList(users);
    }

    private EntityNotFoundException getEntityException(Long id) {
        return new EntityNotFoundException(ENTITY_CONFLICT_MESSAGE + id);
    }
}

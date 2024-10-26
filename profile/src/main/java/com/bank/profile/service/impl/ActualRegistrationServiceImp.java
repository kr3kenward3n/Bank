package com.bank.profile.service.impl;

import com.bank.profile.dto.ActualRegistrationDto;
import com.bank.profile.entity.AccountDetailsIdEntity;
import com.bank.profile.entity.ActualRegistrationEntity;
import com.bank.profile.mapper.ActualRegistrationMapper;
import com.bank.profile.repository.ActualRegistrationRepository;
import com.bank.profile.service.ActualRegistrationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Реализация для {@link ActualRegistrationService}
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ActualRegistrationServiceImp implements ActualRegistrationService {

    ActualRegistrationRepository repository;
    ActualRegistrationMapper mapper;

    /**
     * @param id технический идентификатор для {@link ActualRegistrationEntity}.
     * @return {@link ActualRegistrationDto}.
     */
    @Override
    public ActualRegistrationDto findById(Long id) {
        final ActualRegistrationEntity actualRegistration = repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("actualRegistration с данным id не найден!")
        );
        return mapper.toDto(actualRegistration);
    }

    /**
     * @param actualRegistrationDto {@link ActualRegistrationDto}.
     * @return {@link ActualRegistrationDto}.
     */
    @Override
    @Transactional
    public ActualRegistrationDto save(ActualRegistrationDto actualRegistrationDto) {

        final ActualRegistrationEntity actualRegistration = repository.save(mapper.toEntity(actualRegistrationDto));

        return mapper.toDto(actualRegistration);
    }

    /**
     * @param id   технический идентификатор для {@link AccountDetailsIdEntity}.
     * @param actualRegistrationDto {@link ActualRegistrationDto}.
     * @return {@link ActualRegistrationDto}.
     */
    @Override
    @Transactional
    public ActualRegistrationDto update(Long id, ActualRegistrationDto actualRegistrationDto) {

        final ActualRegistrationEntity registrationById = repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Обновление невозможно, ActualRegistration не найден!")
        );

        final ActualRegistrationEntity registration = repository.save(
                mapper.mergeToEntity(actualRegistrationDto, registrationById)
        );

        return mapper.toDto(registration);
    }

    /**
     * @param ids список технических идентификаторов {@link ActualRegistrationEntity}.
     * @return {@link List<ActualRegistrationDto>}.
     */
    @Override
    public List<ActualRegistrationDto> findAllById(List<Long> ids) {
        final List<ActualRegistrationEntity> actualRegistrationEntities = repository.findAllById(ids);

        return mapper.toDtoList(actualRegistrationEntities);
    }
}


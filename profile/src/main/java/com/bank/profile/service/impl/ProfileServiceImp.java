package com.bank.profile.service.impl;

import com.bank.profile.dto.ProfileDto;
import com.bank.profile.entity.ProfileEntity;
import com.bank.profile.mapper.ProfileMapper;
import com.bank.profile.repository.ProfileRepository;
import com.bank.profile.service.ProfileService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Реализация для {@link ProfileService}
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProfileServiceImp implements ProfileService {

    ProfileRepository repository;
    ProfileMapper mapper;

    /**
     * @param id технический идентификатор для {@link ProfileEntity}.
     * @return {@link ProfileDto}.
     */
    @Override
    public ProfileDto findById(Long id) {
        final ProfileEntity profile = repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("profile с данным id не найден!")
        );
        return mapper.toDto(profile);
    }

    /**
     * @param profileDto {@link ProfileDto}.
     * @return {@link ProfileDto}.
     */
    @Override
    @Transactional
    public ProfileDto save(ProfileDto profileDto) {
        final ProfileEntity profile = repository.save(mapper.toEntity(profileDto));

        return mapper.toDto(profile);
    }

    /**
     * @param profileDto {@link ProfileDto}.
     * @return {@link ProfileDto}.
     */
    @Override
    @Transactional
    public ProfileDto update(Long id, ProfileDto profileDto) {
        final ProfileEntity profileEntityById = repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Обновление невозможно, profile не найден!")
        );
        final ProfileEntity profile = repository.save(
                mapper.mergeToEntity(profileDto, profileEntityById)
        );

        return mapper.toDto(profile);
    }

    /**
     * @param ids список технических идентификаторов {@link ProfileEntity}.
     * @return {@link List<ProfileDto>}.
     */
    @Override
    public List<ProfileDto> findAllById(List<Long> ids) {
        final List<ProfileEntity> profileEntities = repository.findAllById(ids);


        return mapper.toDtoList(profileEntities);
    }
}

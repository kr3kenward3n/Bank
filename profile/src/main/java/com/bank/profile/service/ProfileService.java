package com.bank.profile.service;

import com.bank.profile.dto.ProfileDto;
import com.bank.profile.entity.ProfileEntity;

import java.util.List;

/**
 * Сервис {@link ProfileEntity} {@link ProfileDto}
 */
public interface ProfileService {

    /**
     * @param id технический идентификатор {@link ProfileEntity}
     * @return {@link ProfileDto}
     */
    ProfileDto findById(Long id);

    /**
     * @param ids лист технических идентификаторов {@link ProfileEntity}
     * @return {@link List<ProfileDto>}
     */
    List<ProfileDto> findAllById(List<Long> ids);

    /**
     * @param profile {@link ProfileDto}
     * @return {@link ProfileDto}
     */
    ProfileDto save(ProfileDto profile);

    /**
     * @param profile {@link ProfileDto}
     * @param id      технический идентификатор {@link ProfileEntity}
     * @return {@link ProfileDto}
     */
    ProfileDto update(Long id, ProfileDto profile);
}

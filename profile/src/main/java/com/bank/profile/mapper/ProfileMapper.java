package com.bank.profile.mapper;

import com.bank.profile.dto.ProfileDto;
import com.bank.profile.entity.ProfileEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

/**
 * Mapper для {@link ProfileEntity} и {@link ProfileDto}.
 */
@Mapper(componentModel = "spring")
public interface ProfileMapper {

    /**
     * @param profile {@link ProfileDto}
     * @return {@link ProfileEntity}
     */
    @Mapping(target = "id", ignore = true)
    ProfileEntity toEntity(ProfileDto profile);

    /**
     * @param profile {@link ProfileEntity}
     * @return {@link ProfileDto}
     */
    ProfileDto toDto(ProfileEntity profile);

    /**
     * @param profileDto {@link ProfileDto}
     * @param profile {@link ProfileEntity}
     * @return {@link ProfileEntity}
     */
    @Mapping(target = "id", ignore = true)
    ProfileEntity mergeToEntity(ProfileDto profileDto,
                                @MappingTarget ProfileEntity profile);

    /**
     * @param profiles список {@link ProfileEntity}
     * @return список {@link ProfileDto}
     */
    List<ProfileDto> toDtoList(List<ProfileEntity> profiles);
}

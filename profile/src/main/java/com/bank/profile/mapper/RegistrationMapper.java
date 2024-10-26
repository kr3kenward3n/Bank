package com.bank.profile.mapper;

import com.bank.profile.dto.RegistrationDto;
import com.bank.profile.entity.RegistrationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

/**
 * Mapper для {@link RegistrationEntity} и {@link RegistrationDto}.
 */
@Mapper(componentModel = "spring")
public interface RegistrationMapper {

    /**
     * @param registration {@link RegistrationDto}
     * @return {@link RegistrationEntity}
     */
    @Mapping(target = "id", ignore = true)
    RegistrationEntity toEntity(RegistrationDto registration);

    /**
     * @param registration {@link RegistrationEntity}
     * @return {@link RegistrationDto}
     */
    RegistrationDto toDto(RegistrationEntity registration);

    /**
     * @param registrationDto {@link RegistrationDto}
     * @param registration {@link RegistrationEntity}
     * @return {@link RegistrationEntity}
     */
    @Mapping(target = "id", ignore = true)
    RegistrationEntity mergeToEntity(RegistrationDto registrationDto,
                                     @MappingTarget RegistrationEntity registration);

    /**
     * @param registrations список {@link RegistrationEntity}
     * @return список {@link RegistrationDto}
     */
    List<RegistrationDto> toDtoList(List<RegistrationEntity> registrations);
}

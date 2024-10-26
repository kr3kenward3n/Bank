package com.bank.profile.mapper;

import com.bank.profile.dto.ActualRegistrationDto;
import com.bank.profile.entity.ActualRegistrationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

/**
 * Mapper для {@link ActualRegistrationEntity} и {@link ActualRegistrationDto}.
 */
@Mapper(componentModel = "spring")
public interface ActualRegistrationMapper {

    /**
     * @param actualRegistration {@link ActualRegistrationDto}
     * @return {@link ActualRegistrationEntity}
     */
    @Mapping(target = "id", ignore = true)
    ActualRegistrationEntity toEntity(ActualRegistrationDto actualRegistration);

    /**
     * @param actualRegistration {@link ActualRegistrationEntity}
     * @return {@link ActualRegistrationDto}
     */
    ActualRegistrationDto toDto(ActualRegistrationEntity actualRegistration);

    /**
     * @param actualRegistrationDto {@link ActualRegistrationDto}
     * @param actualRegistration {@link ActualRegistrationEntity}
     * @return {@link ActualRegistrationEntity}
     */
    @Mapping(target = "id", ignore = true)
    ActualRegistrationEntity mergeToEntity(ActualRegistrationDto actualRegistrationDto,
                                           @MappingTarget ActualRegistrationEntity actualRegistration);

    /**
     * @param actualRegistrations список {@link ActualRegistrationEntity}
     * @return список {@link ActualRegistrationDto}
     */
    List<ActualRegistrationDto> toDtoList(List<ActualRegistrationEntity> actualRegistrations);

}

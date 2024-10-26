package com.bank.profile.mapper;

import com.bank.profile.dto.PassportDto;
import com.bank.profile.entity.PassportEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

/**
 * Mapper для {@link PassportEntity} и {@link PassportDto}.
 */
@Mapper(componentModel = "spring")
public interface PassportMapper {

    /**
     * @param passport {@link PassportDto}
     * @return {@link PassportEntity}
     */
    @Mapping(target = "id", ignore = true)
    PassportEntity toEntity(PassportDto passport);

    /**
     * @param passport {@link PassportEntity}
     * @return {@link PassportDto}
     */
    PassportDto toDto(PassportEntity passport);

    /**
     * @param passportDto {@link PassportDto}
     * @param passport {@link PassportEntity}
     * @return {@link PassportEntity}
     */
    @Mapping(target = "id", ignore = true)
    PassportEntity mergeToEntity(PassportDto passportDto, @MappingTarget PassportEntity passport);

    /**
     * @param passports список {@link PassportEntity}
     * @return список {@link PassportDto}
     */
    List<PassportDto> toDtoList(List<PassportEntity> passports);
}

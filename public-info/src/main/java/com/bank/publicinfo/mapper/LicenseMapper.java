package com.bank.publicinfo.mapper;

import com.bank.publicinfo.dto.LicenseDto;
import com.bank.publicinfo.entity.LicenseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

/**
 * Mapper для {@link LicenseEntity} и {@link LicenseDto}
 */
@Mapper(componentModel = "spring")
public interface LicenseMapper {

    /**
     * @param license {@link LicenseDto}
     * @return {@link LicenseEntity}
     */
    @Mapping(target = "id", ignore = true)
    LicenseEntity toEntity(LicenseDto license);

    /**
     * @param license {@link LicenseEntity}
     * @return {@link LicenseDto}
     */
    LicenseDto toDto(LicenseEntity license);

    /**
     * @param licenseDto {@link LicenseDto}
     * @param license    {@link LicenseEntity}
     * @return {@link LicenseEntity}
     */
    @Mapping(target = "id", ignore = true)
    LicenseEntity mergeToEntity(LicenseDto licenseDto, @MappingTarget LicenseEntity license);

    /**
     * @param licenses список {@link LicenseEntity}
     * @return список {@link LicenseDto}
     */
    List<LicenseDto> toDtoList(List<LicenseEntity> licenses);
}

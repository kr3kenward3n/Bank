package com.bank.publicinfo.mapper;

import com.bank.publicinfo.dto.AtmDto;
import com.bank.publicinfo.entity.AtmEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

/**
 * Mapper для {@link AtmEntity} и {@link AtmDto}
 */
@Mapper(componentModel = "spring")
public interface AtmMapper {

    /**
     * @param atm {@link AtmDto}
     * @return {@link AtmEntity}
     */
    @Mapping(target = "id", ignore = true)
    AtmEntity toEntity(AtmDto atm);

    /**
     * @param atm {@link AtmEntity}
     * @return {@link AtmDto}
     */
    AtmDto toDto(AtmEntity atm);

    /**
     * @param atmDto {@link AtmDto}
     * @param atm    {@link AtmEntity}
     * @return {@link AtmEntity}
     */
    @Mapping(target = "id", ignore = true)
    AtmEntity mergeToEntity(AtmDto atmDto, @MappingTarget AtmEntity atm);

    /**
     * @param atms список{@link AtmEntity}
     * @return список {@link AtmDto}
     */
    List<AtmDto> toDtoList(List<AtmEntity> atms);
}

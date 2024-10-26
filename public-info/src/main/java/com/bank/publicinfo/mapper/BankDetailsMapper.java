package com.bank.publicinfo.mapper;

import com.bank.publicinfo.dto.BankDetailsDto;
import com.bank.publicinfo.entity.BankDetailsEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

/**
 * Mapper для {@link BankDetailsEntity} и {@link BankDetailsDto}
 */
@Mapper(componentModel = "spring")
public interface BankDetailsMapper {

    /**
     * @param bankDetails {@link BankDetailsDto}
     * @return {@link BankDetailsEntity}
     */
    @Mapping(target = "id", ignore = true)
    BankDetailsEntity toEntity(BankDetailsDto bankDetails);

    /**
     * @param bankDetails {@link BankDetailsEntity}
     * @return {@link BankDetailsDto}
     */
    BankDetailsDto toDto(BankDetailsEntity bankDetails);

    /**
     * @param bankDetailsDto {@link BankDetailsDto}
     * @param bankDetails    {@link BankDetailsEntity}
     * @return {@link BankDetailsEntity}
     */
    @Mapping(target = "id", ignore = true)
    BankDetailsEntity mergeToEntity(BankDetailsDto bankDetailsDto, @MappingTarget BankDetailsEntity bankDetails);

    /**
     * @param bankDetailsList список {@link BankDetailsEntity}
     * @return список {@link BankDetailsDto}
     */
    List<BankDetailsDto> toDtoList(List<BankDetailsEntity> bankDetailsList);
}

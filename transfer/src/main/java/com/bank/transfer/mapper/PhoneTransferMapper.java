package com.bank.transfer.mapper;

import com.bank.transfer.dto.PhoneTransferDto;
import com.bank.transfer.entity.PhoneTransferEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

/**
 * Mapper для {@link PhoneTransferEntity}
 */
@Mapper(componentModel = "spring")
public interface PhoneTransferMapper {

    /**
     * @param dto {@link PhoneTransferDto}
     * @return {@link PhoneTransferEntity}
     */
    @Mapping(target = "id", ignore = true)
    PhoneTransferEntity toEntity(PhoneTransferDto dto);

    /**
     * @param transfer {@link PhoneTransferEntity}
     * @return {@link PhoneTransferDto}
     */
    PhoneTransferDto toDto(PhoneTransferEntity transfer);

    /**
     * @param transferDto {@link PhoneTransferDto}
     * @param transfer {@link PhoneTransferEntity}
     * @return {@link PhoneTransferEntity}
     */
    @Mapping(target = "id", ignore = true)
    PhoneTransferEntity mergeToEntity (PhoneTransferDto transferDto,
                                      @MappingTarget PhoneTransferEntity transfer);

    /**
     * @param transfers {@link List<PhoneTransferEntity>}
     * @return {@link List<PhoneTransferDto>}
     */
    List<PhoneTransferDto> toDtoList(List<PhoneTransferEntity> transfers);
}

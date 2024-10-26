package com.bank.transfer.mapper;

import com.bank.transfer.dto.AccountTransferDto;
import com.bank.transfer.dto.CardTransferDto;
import com.bank.transfer.entity.AccountTransferEntity;
import com.bank.transfer.entity.CardTransferEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

/**
 * Mapper для {@link CardTransferEntity}
 */
@Mapper(componentModel = "spring")
public interface CardTransferMapper {

    /**
     * @param transfer {@link CardTransferDto}
     * @return {@link CardTransferEntity}
     */
    @Mapping(target = "id", ignore = true)
    CardTransferEntity toEntity(CardTransferDto transfer);

    /**
     * @param transfer {@link CardTransferEntity}
     * @return {@link CardTransferDto}
     */
    CardTransferDto toDto(CardTransferEntity transfer);

    /**
     * @param transferDto {@link AccountTransferDto}
     * @param transfer {@link AccountTransferEntity}
     * @return {@link AccountTransferEntity}
     */
    @Mapping(target = "id", ignore = true)
    CardTransferEntity mergeToEntity (CardTransferDto transferDto,
                                         @MappingTarget CardTransferEntity transfer);

    /**
     * @param transfers {@link List<CardTransferEntity>}
     * @return {@link List<CardTransferDto>}
     */
    List<CardTransferDto> toDtoList(List<CardTransferEntity> transfers);
}

package com.bank.transfer.mapper;

import com.bank.transfer.dto.AccountTransferDto;
import com.bank.transfer.entity.AccountTransferEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

/**
 * Mapper для {@link AccountTransferEntity}, {@link AccountTransferDto}
 */
@Mapper(componentModel = "spring")
public interface AccountTransferMapper {

    /**
     * @param transfer {@link AccountTransferDto}
     * @return {@link AccountTransferEntity}
    */
    @Mapping(target = "id", ignore = true)
    AccountTransferEntity toEntity(AccountTransferDto transfer);

    /**
     * @param transfer {@link AccountTransferEntity}
     * @return {@link AccountTransferDto}
     */
    AccountTransferDto toDto(AccountTransferEntity transfer);

    /**
     * @param transferDto {@link AccountTransferDto}
     * @param transfer {@link AccountTransferEntity}
     * @return {@link AccountTransferEntity}
     */
    @Mapping(target = "id", ignore = true)
    AccountTransferEntity mergeToEntity (AccountTransferDto transferDto,
                                         @MappingTarget AccountTransferEntity transfer);

    /**
     * @param transfers {@link List<AccountTransferEntity>}
     * @return {@link List<AccountTransferDto>}
     */
    List<AccountTransferDto> toDtoList(List<AccountTransferEntity> transfers);
}

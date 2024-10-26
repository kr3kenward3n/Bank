package com.bank.account.mapper;

import com.bank.account.dto.AccountDetailsDto;
import com.bank.account.entity.AccountDetailsEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

/**
 * Mapper для {@link AccountDetailsEntity} и {@link AccountDetailsDto}
 */
@Mapper(componentModel = "spring")
public interface AccountDetailsMapper {

    /**
     * @param accountDetails {@link AccountDetailsDto}
     * @return {@link AccountDetailsEntity}
     */
    @Mapping(target = "id", ignore = true)
    AccountDetailsEntity toEntity(AccountDetailsDto accountDetails);

    /**
     * @param accountDetails {@link AccountDetailsEntity}
     * @return {@link AccountDetailsDto}
     */
    AccountDetailsDto toDto(AccountDetailsEntity accountDetails);

    /**
     * @param accountDetailsList {@link List<AccountDetailsEntity>}
     * @return {@link List<AccountDetailsDto>}
     */
    List<AccountDetailsDto> toDtoList(List<AccountDetailsEntity> accountDetailsList);

    /**
     * @param accountDetails    {@link AccountDetailsEntity}
     * @param accountDetailsDto {@link AccountDetailsDto}
     * @return {@link AccountDetailsEntity}
     */
    @Mapping(target = "id", ignore = true)
    AccountDetailsEntity mergeToEntity(@MappingTarget AccountDetailsEntity accountDetails,
                                       AccountDetailsDto accountDetailsDto);
}

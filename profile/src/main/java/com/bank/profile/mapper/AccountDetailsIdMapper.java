package com.bank.profile.mapper;

import com.bank.profile.dto.AccountDetailsIdDto;
import com.bank.profile.entity.AccountDetailsIdEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

/**
 * Mapper для {@link AccountDetailsIdEntity} и {@link AccountDetailsIdDto}.
 */
@Mapper(componentModel = "spring")
public interface AccountDetailsIdMapper {

    /**
     * @param accountDetailsId {@link AccountDetailsIdDto}
     * @return {@link AccountDetailsIdEntity}
     */
    @Mapping(target = "id", ignore = true)
    AccountDetailsIdEntity toEntity(AccountDetailsIdDto accountDetailsId);

    /**
     * @param accountDetailsId {@link AccountDetailsIdEntity}
     * @return {@link AccountDetailsIdDto}
     */
    AccountDetailsIdDto toDto(AccountDetailsIdEntity accountDetailsId);

    /**
     * @param accountDetailsIdDto {@link AccountDetailsIdDto}
     * @param accountDetailsId {@link AccountDetailsIdEntity}
     * @return {@link AccountDetailsIdEntity}
     */
    @Mapping(target = "id", ignore = true)
    AccountDetailsIdEntity mergeToEntity(AccountDetailsIdDto accountDetailsIdDto,
                                         @MappingTarget AccountDetailsIdEntity accountDetailsId);

    /**
     * @param accountDetailsIds список {@link AccountDetailsIdEntity}
     * @return список {@link AccountDetailsIdDto}
     */
    List<AccountDetailsIdDto> toDtoList(List<AccountDetailsIdEntity> accountDetailsIds);
}

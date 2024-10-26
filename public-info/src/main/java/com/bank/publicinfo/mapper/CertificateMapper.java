package com.bank.publicinfo.mapper;

import com.bank.publicinfo.dto.CertificateDto;
import com.bank.publicinfo.entity.CertificateEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

/**
 * Mapper для {@link CertificateEntity} и {@link CertificateDto}
 */
@Mapper(componentModel = "spring")
public interface CertificateMapper {

    /**
     * @param certificate {@link CertificateDto}
     * @return {@link CertificateEntity}
     */
    @Mapping(target = "id", ignore = true)
    CertificateEntity toEntity(CertificateDto certificate);

    /**
     * @param certificate {@link CertificateEntity}
     * @return {@link CertificateDto}
     */
    CertificateDto toDto(CertificateEntity certificate);

    /**
     * @param certificateDto {@link CertificateDto}
     * @param certificate    {@link CertificateEntity}
     * @return {@link CertificateEntity}
     */
    @Mapping(target = "id", ignore = true)
    CertificateEntity mergeToEntity(CertificateDto certificateDto, @MappingTarget CertificateEntity certificate);

    /**
     * @param certificates список {@link CertificateEntity}
     * @return список {@link CertificateDto}
     */
    List<CertificateDto> toDtoList(List<CertificateEntity> certificates);
}

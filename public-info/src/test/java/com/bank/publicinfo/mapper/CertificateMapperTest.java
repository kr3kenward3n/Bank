package com.bank.publicinfo.mapper;

import com.bank.publicinfo.dto.CertificateDto;
import com.bank.publicinfo.entity.CertificateEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Тесты для {@link CertificateMapper}
 */
class CertificateMapperTest {
    private CertificateMapper mapper = new CertificateMapperImpl();

    @Test
    @DisplayName("Маппинг в энтити")
    void toEntityTest() {
        final CertificateDto certificateDto =
                new CertificateDto(1L, new Byte[3], null);

        CertificateEntity certificateEntity = mapper.toEntity(certificateDto);

        assertAll(
                () -> assertNotNull(certificateEntity),
                () -> assertEquals(null, certificateEntity.getId()),
                () -> assertEquals(certificateDto.getPhotoCertificate().length,
                        certificateEntity.getPhotoCertificate().length),
                () -> assertEquals(certificateDto.getBankDetails(), certificateEntity.getBankDetails())
        );
    }

    @Test
    @DisplayName("Маппинг в энтити, на вход передан null")
    void toEntityNullTest() {
        assertNull(mapper.toEntity(null));
    }

    @Test
    @DisplayName("Маппинг в дто")
    void toDtoTest() {
        final CertificateEntity certificateEntity =
                new CertificateEntity(1L, new Byte[3], null);

        CertificateDto certificateDto = mapper.toDto(certificateEntity);

        assertAll(
                () -> assertNotNull(certificateDto),
                () -> assertEquals(certificateEntity.getId(), certificateDto.getId()),
                () -> assertEquals(certificateEntity.getPhotoCertificate().length,
                        certificateDto.getPhotoCertificate().length),
                () -> assertEquals(certificateEntity.getBankDetails(), certificateDto.getBankDetails())
        );
    }

    @Test
    @DisplayName("Маппинг в дто, на вход передан null")
    void toDtoNullTest() {
        assertNull(mapper.toDto(null));
    }

    @Test
    @DisplayName("Слияние в энтити")
    void mergeToEntityTest() {
        final CertificateEntity certificateEntity =
                new CertificateEntity(1L, new Byte[3], null);
        final CertificateDto certificateDto =
                new CertificateDto(0L, new Byte[5], null);

        CertificateEntity mergedCertificateEntity = mapper.mergeToEntity(certificateDto, certificateEntity);

        assertAll(
                () -> assertNotNull(mergedCertificateEntity),
                () -> assertEquals(certificateEntity.getId(), mergedCertificateEntity.getId()),
                () -> assertEquals(certificateDto.getPhotoCertificate().length,
                        mergedCertificateEntity.getPhotoCertificate().length),
                () -> assertEquals(certificateDto.getBankDetails(), mergedCertificateEntity.getBankDetails())
        );
    }

    @Test
    @DisplayName("Слияние в энтити, на вход передан null")
    void mergeToEntityNullTest() {
        final CertificateEntity certificateEntity =
                new CertificateEntity(1L, new Byte[3], null);

        CertificateEntity mergedCertificateEntity = mapper.mergeToEntity(null, certificateEntity);

        assertAll(
                () -> assertNotNull(mergedCertificateEntity),
                () -> assertEquals(certificateEntity.getId(), mergedCertificateEntity.getId()),
                () -> assertEquals(certificateEntity.getPhotoCertificate().length,
                        mergedCertificateEntity.getPhotoCertificate().length),
                () -> assertEquals(certificateEntity.getBankDetails(), mergedCertificateEntity.getBankDetails())
        );
    }

    @Test
    @DisplayName("Маппинг в список дто")
    void toDtoListTest() {
        final CertificateEntity certificateEntity = new CertificateEntity();
        final List<CertificateEntity> certificateEntityList = List.of(certificateEntity, certificateEntity, certificateEntity);

        List<CertificateDto> certificateDtoList = mapper.toDtoList(certificateEntityList);

        assertEquals(certificateEntityList.size(), certificateDtoList.size());
    }

    @Test
    @DisplayName("Маппинг в список дто, на вход передан null")
    void toDtoListNullTest() {
        assertNull(mapper.toDtoList(null));
    }
}

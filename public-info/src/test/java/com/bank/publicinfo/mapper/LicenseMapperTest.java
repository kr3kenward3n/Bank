package com.bank.publicinfo.mapper;

import com.bank.publicinfo.dto.LicenseDto;
import com.bank.publicinfo.entity.LicenseEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Тесты для {@link LicenseMapper}
 */
class LicenseMapperTest {
    private LicenseMapper mapper = new LicenseMapperImpl();

    @Test
    @DisplayName("Маппинг в энтити")
    void toEntityTest() {
        final LicenseDto licenseDto =
                new LicenseDto(1L, new Byte[3], null);

        LicenseEntity licenseEntity = mapper.toEntity(licenseDto);

        assertAll(
                () -> assertNotNull(licenseEntity),
                () -> assertEquals(null, licenseEntity.getId()),
                () -> assertEquals(licenseDto.getPhotoLicense().length, licenseEntity.getPhotoLicense().length),
                () -> assertEquals(licenseDto.getBankDetails(), licenseEntity.getBankDetails())
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
        final LicenseEntity licenseEntity =
                new LicenseEntity(1L, new Byte[3], null);

        LicenseDto licenseDto = mapper.toDto(licenseEntity);

        assertAll(
                () -> assertNotNull(licenseDto),
                () -> assertEquals(licenseEntity.getId(), licenseDto.getId()),
                () -> assertEquals(licenseEntity.getPhotoLicense().length, licenseDto.getPhotoLicense().length),
                () -> assertEquals(licenseEntity.getBankDetails(), licenseDto.getBankDetails())
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
        final LicenseEntity licenseEntity =
                new LicenseEntity(1L, new Byte[3], null);
        final LicenseDto licenseDto =
                new LicenseDto(0L, new Byte[5], null);

        LicenseEntity mergedLicenseEntity = mapper.mergeToEntity(licenseDto, licenseEntity);

        assertAll(
                () -> assertNotNull(mergedLicenseEntity),
                () -> assertEquals(licenseEntity.getId(), mergedLicenseEntity.getId()),
                () -> assertEquals(licenseDto.getPhotoLicense().length, mergedLicenseEntity.getPhotoLicense().length),
                () -> assertEquals(licenseDto.getBankDetails(), licenseEntity.getBankDetails())
        );
    }

    @Test
    @DisplayName("Слияние в энтити, на вход передан null")
    void mergeToEntityNullTest() {
        final LicenseEntity licenseEntity =
                new LicenseEntity(1L, new Byte[3], null);

        LicenseEntity mergedLicenseEntity = mapper.mergeToEntity(null, licenseEntity);

        assertAll(
                () -> assertNotNull(mergedLicenseEntity),
                () -> assertEquals(licenseEntity.getId(), mergedLicenseEntity.getId()),
                () -> assertEquals(licenseEntity.getPhotoLicense().length,
                        mergedLicenseEntity.getPhotoLicense().length),
                () -> assertEquals(licenseEntity.getBankDetails(), mergedLicenseEntity.getBankDetails())
        );
    }

    @Test
    @DisplayName("Маппинг в список дто")
    void toDtoListTest() {
        final LicenseEntity licenseEntity = new LicenseEntity();
        final List<LicenseEntity> licenseEntityList = List.of(licenseEntity, licenseEntity, licenseEntity);

        List<LicenseDto> licenseDtoList = mapper.toDtoList(licenseEntityList);

        assertEquals(licenseEntityList.size(), licenseDtoList.size());
    }

    @Test
    @DisplayName("Маппинг в список дто, на вход передан null")
    void toDtoListNullTest() {
        assertNull(mapper.toDtoList(null));
    }
}

package com.bank.publicinfo.service;

import com.bank.publicinfo.dto.LicenseDto;
import com.bank.publicinfo.entity.LicenseEntity;
import com.bank.publicinfo.mapper.LicenseMapper;
import com.bank.publicinfo.repository.LicenseRepository;
import com.bank.publicinfo.service.impl.LicenseServiceImpl;
import com.bank.publicinfo.util.EntityNotFoundSupplier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

/**
 * Тесты для {@link LicenseServiceImpl}
 */
@ExtendWith(MockitoExtension.class)
class LicenseServiceTest {
    @Mock
    private LicenseRepository repository;
    @Mock
    private LicenseMapper mapper;
    @Spy
    private EntityNotFoundSupplier supplier;
    @InjectMocks
    private LicenseServiceImpl service;

    @Test
    @DisplayName("Чтение по нескольким id, позитивный сценарий")
    void findAllByIdPositiveTest() {
        final List<Long> ids = List.of(1L);
        final List<LicenseEntity> licenseEntities = List.of(new LicenseEntity());
        final List<LicenseDto> licenseDtos = List.of(new LicenseDto());
        when(repository.findAllById(ids)).thenReturn(licenseEntities);
        when(mapper.toDtoList(licenseEntities)).thenReturn(licenseDtos);

        List<LicenseDto> licenseDtosResult = service.findAllById(ids);

        assertAll(
                () -> assertNotNull(licenseDtosResult),
                () -> assertEquals(licenseDtos, licenseDtosResult)
        );
    }

    @Test
    @DisplayName("Чтение по нескольким несуществующим id, негативный сценарий")
    void findAllByNonExistIdNegativeTest() {
        final List<Long> ids = List.of(1L, 1L);
        final List<LicenseEntity> licenseEntities = List.of(new LicenseEntity());
        when(repository.findAllById(ids)).thenReturn(licenseEntities);

        assertThrows(EntityNotFoundException.class, () -> service.findAllById(ids));
    }

    @Test
    @DisplayName("Создание, позитивный сценарий")
    void createPositiveTest() {
        final LicenseDto dto = new LicenseDto();
        final LicenseEntity entity = new LicenseEntity();
        when(mapper.toEntity(dto)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(entity);
        when(mapper.toDto(entity)).thenReturn(dto);

        LicenseDto dtoResult = service.create(dto);

        assertAll(
                () -> assertNotNull(dtoResult),
                () -> assertEquals(dto, dtoResult)
        );
    }

    @Test
    @DisplayName("Создание, передан null, негативный сценарий")
    void createWithNullNegativeTest() {
        assertNull(service.create(null));
    }

    @Test
    @DisplayName("Обновление, позитивный сценарий")
    void updatePositiveTest() {
        final LicenseDto dto =
                new LicenseDto(1L, new Byte[1], null);
        final LicenseEntity entity =
                new LicenseEntity(1L, null, null);
        final LicenseEntity mergedEntity =
                new LicenseEntity(1L, new Byte[1], null);
        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        when(mapper.mergeToEntity(dto, entity)).thenReturn(mergedEntity);
        when(mapper.toDto(mergedEntity)).thenReturn(dto);

        LicenseDto updatedDto = service.update(1L, dto);

        assertAll(
                () -> assertNotNull(updatedDto),
                () -> assertEquals(dto.getPhotoLicense(), updatedDto.getPhotoLicense())
        );
    }

    @Test
    @DisplayName("Обновление, передан null, негативный сценарий")
    void updateWithNullNegativeTest() {
        assertThrows(EntityNotFoundException.class,
                () -> service.update(1L, null));
    }

    @Test
    @DisplayName("Чтение по id, позитивный сценарий")
    void findByIdPositiveTest() {
        final LicenseEntity entity = new LicenseEntity();
        final LicenseDto dto = new LicenseDto();
        when(mapper.toDto(entity)).thenReturn(dto);
        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        LicenseDto dtoResult = service.findById(1L);

        assertAll(
                () -> assertNotNull(dtoResult),
                () -> assertEquals(dto, dtoResult)
        );
    }

    @Test
    @DisplayName("Чтение по несуществующему id, негативный сценарий")
    void findByNonExistIdNegativeTest() {
        assertThrows(EntityNotFoundException.class, () -> service.findById(1L));
    }
}

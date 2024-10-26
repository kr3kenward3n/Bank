package com.bank.publicinfo.service;

import com.bank.publicinfo.dto.CertificateDto;
import com.bank.publicinfo.entity.CertificateEntity;
import com.bank.publicinfo.mapper.CertificateMapper;
import com.bank.publicinfo.repository.CertificateRepository;
import com.bank.publicinfo.service.impl.CertificateServiceImpl;
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
 * Тесты для {@link CertificateServiceImpl}
 */
@ExtendWith(MockitoExtension.class)
class CertificateServiceTest {
    @Mock
    private CertificateRepository repository;
    @Mock
    private CertificateMapper mapper;
    @Spy
    private EntityNotFoundSupplier supplier;
    @InjectMocks
    private CertificateServiceImpl service;

    @Test
    @DisplayName("Чтение по нескольким id, позитивный сценарий")
    void findAllByIdPositiveTest() {
        final List<Long> ids = List.of(1L);
        final List<CertificateEntity> certificateEntities = List.of(new CertificateEntity());
        final List<CertificateDto> certificateDtos = List.of(new CertificateDto());
        when(repository.findAllById(ids)).thenReturn(certificateEntities);
        when(mapper.toDtoList(certificateEntities)).thenReturn(certificateDtos);

        List<CertificateDto> certificateDtosResult = service.findAllById(ids);

        assertAll(
                () -> assertNotNull(certificateDtosResult),
                () -> assertEquals(certificateDtos, certificateDtosResult)
        );
    }

    @Test
    @DisplayName("Чтение по нескольким несуществующим id, негативный сценарий")
    void findAllByNonExistIdNegativeTest() {
        final List<Long> ids = List.of(1L, 1L);
        final List<CertificateEntity> certificateEntities = List.of(new CertificateEntity());
        when(repository.findAllById(ids)).thenReturn(certificateEntities);

        assertThrows(EntityNotFoundException.class, () -> service.findAllById(ids));
    }

    @Test
    @DisplayName("Создание, позитивный сценарий")
    void createPositiveTest() {
        final CertificateDto dto = new CertificateDto();
        final CertificateEntity entity = new CertificateEntity();
        when(mapper.toEntity(dto)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(entity);
        when(mapper.toDto(entity)).thenReturn(dto);

        CertificateDto dtoResult = service.create(dto);

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
        final CertificateDto dto =
                new CertificateDto(1L, new Byte[1], null);
        final CertificateEntity entity =
                new CertificateEntity(1L, null, null);
        final CertificateEntity mergedEntity =
                new CertificateEntity(1L, new Byte[1], null);
        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        when(mapper.mergeToEntity(dto, entity)).thenReturn(mergedEntity);
        when(mapper.toDto(mergedEntity)).thenReturn(dto);

        CertificateDto updatedDto = service.update(1L, dto);

        assertAll(
                () -> assertNotNull(updatedDto),
                () -> assertEquals(dto.getPhotoCertificate(), updatedDto.getPhotoCertificate())
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
        final CertificateEntity entity = new CertificateEntity();
        final CertificateDto dto = new CertificateDto();
        when(mapper.toDto(entity)).thenReturn(dto);
        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        CertificateDto dtoResult = service.findById(1L);

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

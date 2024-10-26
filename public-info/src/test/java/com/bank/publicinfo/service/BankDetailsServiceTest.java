package com.bank.publicinfo.service;

import com.bank.publicinfo.dto.BankDetailsDto;
import com.bank.publicinfo.entity.BankDetailsEntity;
import com.bank.publicinfo.mapper.BankDetailsMapper;
import com.bank.publicinfo.repository.BankDetailsRepository;
import com.bank.publicinfo.service.impl.BankDetailsServiceImpl;
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
 * Тесты для {@link BankDetailsServiceImpl}
 */
@ExtendWith(MockitoExtension.class)
class BankDetailsServiceTest {
    @Mock
    private BankDetailsRepository repository;
    @Mock
    private BankDetailsMapper mapper;
    @Spy
    private EntityNotFoundSupplier supplier;
    @InjectMocks
    private BankDetailsServiceImpl service;

    @Test
    @DisplayName("Чтение по нескольким id, позитивный сценарий")
    void findAllByIdPositiveTest() {
        final List<Long> ids = List.of(1L);
        final List<BankDetailsEntity> bankDetailsEntities = List.of(new BankDetailsEntity());
        final List<BankDetailsDto> bankDetailsDtos = List.of(new BankDetailsDto());
        when(repository.findAllById(ids)).thenReturn(bankDetailsEntities);
        when(mapper.toDtoList(bankDetailsEntities)).thenReturn(bankDetailsDtos);

        List<BankDetailsDto> bankDetailsDtosResult = service.findAllById(ids);

        assertAll(
                () -> assertNotNull(bankDetailsDtosResult),
                () -> assertEquals(bankDetailsDtos, bankDetailsDtosResult)
        );
    }

    @Test
    @DisplayName("Чтение по нескольким несуществующим id, негативный сценарий")
    void findAllByNonExistIdNegativeTest() {
        final List<Long> ids = List.of(1L, 1L);
        final List<BankDetailsEntity> bankDetailsEntities = List.of(new BankDetailsEntity());
        when(repository.findAllById(ids)).thenReturn(bankDetailsEntities);

        assertThrows(EntityNotFoundException.class, () -> service.findAllById(ids));
    }

    @Test
    @DisplayName("Создание, позитивный сценарий")
    void createPositiveTest() {
        final BankDetailsDto dto = new BankDetailsDto();
        final BankDetailsEntity entity = new BankDetailsEntity();
        when(mapper.toEntity(dto)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(entity);
        when(mapper.toDto(entity)).thenReturn(dto);

        BankDetailsDto dtoResult = service.create(dto);

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
        final BankDetailsDto dto =
                new BankDetailsDto(1L, null, null, null, null,
                        "city", null, null);
        final BankDetailsEntity entity =
                new BankDetailsEntity(1L, null, null, null, null,
                        null, null, null);
        final BankDetailsEntity mergedEntity =
                new BankDetailsEntity(1L, null, null, null, null,
                        "city", null, null);
        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        when(mapper.mergeToEntity(dto, entity)).thenReturn(mergedEntity);
        when(mapper.toDto(mergedEntity)).thenReturn(dto);

        BankDetailsDto updatedDto = service.update(1L, dto);

        assertAll(
                () -> assertNotNull(updatedDto),
                () -> assertEquals(dto.getCity(), updatedDto.getCity())
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
        final BankDetailsEntity entity = new BankDetailsEntity();
        final BankDetailsDto dto = new BankDetailsDto();
        when(mapper.toDto(entity)).thenReturn(dto);
        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        BankDetailsDto dtoResult = service.findById(1L);

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

package com.bank.publicinfo.service;

import com.bank.publicinfo.dto.AtmDto;
import com.bank.publicinfo.entity.AtmEntity;
import com.bank.publicinfo.mapper.AtmMapper;
import com.bank.publicinfo.repository.AtmRepository;
import com.bank.publicinfo.service.impl.AtmServiceImpl;
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
 * Тесты для {@link AtmServiceImpl}
 */
@ExtendWith(MockitoExtension.class)
class AtmServiceTest {
    @Mock
    private AtmRepository repository;
    @Mock
    private AtmMapper mapper;
    @Spy
    private EntityNotFoundSupplier supplier;
    @InjectMocks
    private AtmServiceImpl service;

    @Test
    @DisplayName("Чтение по нескольким id, позитивный сценарий")
    void findAllByIdPositiveTest() {
        final List<Long> ids = List.of(1L);
        final List<AtmEntity> atmEntities = List.of(new AtmEntity());
        final List<AtmDto> atmDtos = List.of(new AtmDto());
        when(repository.findAllById(ids)).thenReturn(atmEntities);
        when(mapper.toDtoList(atmEntities)).thenReturn(atmDtos);

        List<AtmDto> atmDtosResult = service.findAllById(ids);

        assertAll(
                () -> assertNotNull(atmDtosResult),
                () -> assertEquals(atmDtos, atmDtosResult)
        );
    }

    @Test
    @DisplayName("Чтение по нескольким несуществующим id, негативный сценарий")
    void findAllByNonExistIdNegativeTest() {
        final List<Long> ids = List.of(1L, 1L);
        final List<AtmEntity> atmEntities = List.of(new AtmEntity());
        when(repository.findAllById(ids)).thenReturn(atmEntities);

        assertThrows(EntityNotFoundException.class, () -> service.findAllById(ids));
    }

    @Test
    @DisplayName("Создание, позитивный сценарий")
    void createPositiveTest() {
        final AtmDto dto = new AtmDto();
        final AtmEntity entity = new AtmEntity();
        when(mapper.toEntity(dto)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(entity);
        when(mapper.toDto(entity)).thenReturn(dto);

        AtmDto dtoResult = service.create(dto);

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
        final AtmDto dto =
                new AtmDto(1L, "address", null, null, null, null);
        final AtmEntity entity =
                new AtmEntity(1L, null, null, null, null, null);
        final AtmEntity mergedEntity =
                new AtmEntity(1L, "address", null, null, null, null);
        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        when(mapper.mergeToEntity(dto, entity)).thenReturn(mergedEntity);
        when(mapper.toDto(mergedEntity)).thenReturn(dto);

        AtmDto updatedDto = service.update(1L, dto);

        assertAll(
                () -> assertNotNull(updatedDto),
                () -> assertEquals(dto.getAddress(), updatedDto.getAddress())
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
        final AtmEntity entity = new AtmEntity();
        final AtmDto dto = new AtmDto();
        when(mapper.toDto(entity)).thenReturn(dto);
        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        AtmDto dtoResult = service.findById(1L);

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

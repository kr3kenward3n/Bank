package com.bank.publicinfo.service;

import com.bank.publicinfo.dto.BranchDto;
import com.bank.publicinfo.entity.BranchEntity;
import com.bank.publicinfo.mapper.BranchMapper;
import com.bank.publicinfo.repository.BranchRepository;
import com.bank.publicinfo.service.impl.BranchServiceImpl;
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
 * Тесты для {@link BranchServiceImpl}
 */
@ExtendWith(MockitoExtension.class)
class BranchServiceTest {
    @Mock
    private BranchRepository repository;
    @Mock
    private BranchMapper mapper;
    @Spy
    private EntityNotFoundSupplier supplier;
    @InjectMocks
    private BranchServiceImpl service;

    @Test
    @DisplayName("Чтение по нескольким id, позитивный сценарий")
    void findAllByIdPositiveTest() {
        final List<Long> ids = List.of(1L);
        final List<BranchEntity> branchEntities = List.of(new BranchEntity());
        final List<BranchDto> branchDtos = List.of(new BranchDto());
        when(repository.findAllById(ids)).thenReturn(branchEntities);
        when(mapper.toDtoList(branchEntities)).thenReturn(branchDtos);

        List<BranchDto> branchDtosResult = service.findAllById(ids);

        assertAll(
                () -> assertNotNull(branchDtosResult),
                () -> assertEquals(branchDtos, branchDtosResult)
        );
    }

    @Test
    @DisplayName("Чтение по нескольким несуществующим id, негативный сценарий")
    void findAllByNonExistIdNegativeTest() {
        final List<Long> ids = List.of(1L, 1L);
        final List<BranchEntity> branchEntities = List.of(new BranchEntity());
        when(repository.findAllById(ids)).thenReturn(branchEntities);

        assertThrows(EntityNotFoundException.class, () -> service.findAllById(ids));
    }

    @Test
    @DisplayName("Создание, позитивный сценарий")
    void createPositiveTest() {
        final BranchDto dto = new BranchDto();
        final BranchEntity entity = new BranchEntity();
        when(mapper.toEntity(dto)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(entity);
        when(mapper.toDto(entity)).thenReturn(dto);

        BranchDto dtoResult = service.create(dto);

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
        final BranchDto dto =
                new BranchDto(1L, "address", null, null, null, null);
        final BranchEntity entity =
                new BranchEntity(1L, null, null, null, null, null);
        final BranchEntity mergedEntity =
                new BranchEntity(1L, "address", null, null, null, null);
        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        when(mapper.mergeToEntity(dto, entity)).thenReturn(mergedEntity);
        when(mapper.toDto(mergedEntity)).thenReturn(dto);

        BranchDto updatedDto = service.update(1L, dto);

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
        final BranchEntity entity = new BranchEntity();
        final BranchDto dto = new BranchDto();
        when(mapper.toDto(entity)).thenReturn(dto);
        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        BranchDto dtoResult = service.findById(1L);

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

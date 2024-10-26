package com.bank.history.service;

import com.bank.history.dto.HistoryDto;
import com.bank.history.entity.HistoryEntity;
import com.bank.history.mapper.HistoryMapper;
import com.bank.history.repository.HistoryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

/**
 * Модульные тесты для реазиаци {@link HistoryService}
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("Тесты HistoryService")
public class HistoryServiceTest {

    @Mock
    private HistoryMapper mapper;

    @Mock
    private HistoryRepository repository;

    @InjectMocks
    private HistoryServiceImpl service;

    private final Long id = 1L;
    private final HistoryEntity historyEntity = new HistoryEntity(id, id, id, id, id, id, id);
    private final HistoryEntity historyEntity1 = new HistoryEntity(id + 1, id, id, id, id, id, id);
    private final HistoryEntity historyEntity2 = new HistoryEntity(id + 2, id, id, id, id, id, id);
    private final HistoryDto historyDto = new HistoryDto(id, id, id, id, id, id, id);
    private final HistoryDto historyDto1 = new HistoryDto(id + 1, id, id, id, id, id, id);
    private final HistoryDto historyDto2 = new HistoryDto(id + 2, id, id, id, id, id, id);

    @Test
    @DisplayName("Чтение по id, позитивный сценарий")
    public void readByIdPositiveTest() {
        given(repository.findById(ArgumentMatchers.any())).willReturn(Optional.of(historyEntity));
        given(mapper.toDto(historyEntity)).willReturn(historyDto);
        final HistoryDto historyDto = service.readById(id);
        assertAll(
                () -> assertNotNull(historyDto),
                () -> assertEquals(historyDto.getId(), id),
                () -> assertEquals(historyDto.getAccountAuditId(), id)
        );
    }

    @Test
    @DisplayName("Чтение по не существующему id, негативный сценарий")
    public void readByNonExistIdNegativeTest() {
        given(repository.findById(ArgumentMatchers.any())).willReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> service.readById(id));
    }

    @Test
    @DisplayName("Чтение по нескольким id, позитивный сценарий")
    public void readAllByIdPositiveTest() {
        final List<Long> ids = List.of(1L, 2L, 3L);
        given(repository.findAllById(ids)).willReturn(List.of(historyEntity, historyEntity1, historyEntity2));
        given(mapper.toListDto(ArgumentMatchers.anyList())).willReturn(List.of(historyDto, historyDto1, historyDto2));
        final List<HistoryDto> historyDtos = service.readAllById(ids);
        assertAll(
                () -> assertNotNull(historyDtos),
                () -> assertEquals(historyDtos.get(0), historyDto)
        );
    }

    @Test
    @DisplayName("Чтение по нескольким не существующим id, негативный сценарий")
    public void readAllByNonExistIdNegativeTest() {
        final List<Long> ids = List.of(1L, 2L, 3L);
        given(repository.findAllById(ids)).willReturn(List.of(historyEntity));
        assertThrows(EntityNotFoundException.class, () -> service.readAllById(ids));
    }

    @Test
    @DisplayName("Создание, позитивный сценарий")
    public void createPositiveTest() {
        given(repository.save(historyEntity)).willReturn(historyEntity);
        given(mapper.toEntity(historyDto)).willReturn(historyEntity);
        given(mapper.toDto(historyEntity)).willReturn(historyDto);
        final HistoryDto history = service.create(historyDto);
        assertAll(
                () -> assertNotNull(history),
                () -> assertEquals(history, historyDto)
        );
    }

    @Test
    @DisplayName("Создание null, негативный сценарий")
    public void createNullNegativeTest() {
        given(repository.save(historyEntity)).willReturn(null);
        given(mapper.toEntity(historyDto)).willReturn(historyEntity);
        given(mapper.toDto(null)).willReturn(null);
        final HistoryDto history = service.create(historyDto);
        assertNull(history);
    }

    @Test
    @DisplayName("Обновление по id, позитивный сценарий")
    public void updatePositiveTest() {
        given(repository.findById(id)).willReturn(Optional.of(historyEntity));
        given(repository.save(historyEntity)).willReturn(historyEntity);
        given(mapper.mergeToEntity(historyDto, historyEntity)).willReturn(historyEntity);
        given(mapper.toDto(historyEntity)).willReturn(historyDto);
        final HistoryDto history = service.update(id, historyDto);
        assertAll(
                () -> assertNotNull(history),
                () -> assertEquals(history, historyDto)
        );
    }

    @Test
    @DisplayName("Обновление по не существующему id, негативный сценарий")
    public void updateByNonExistIdNegativeTest() {
        given(repository.findById(id)).willReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> service.update(id, historyDto));
    }

}

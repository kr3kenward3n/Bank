package com.bank.transfer.service.Impl;

import com.bank.transfer.dto.CardTransferDto;
import com.bank.transfer.entity.CardTransferEntity;
import com.bank.transfer.mapper.CardTransferMapper;
import com.bank.transfer.repository.CardTransferRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.never;

@ExtendWith(MockitoExtension.class)
class CardTransferServiceImplTest {

    @Mock
    private CardTransferRepository repository;

    @Mock
    private CardTransferMapper mapper;

    @InjectMocks
    private CardTransferServiceImpl service;

    CardTransferDto dto;

    CardTransferEntity entity;

    @BeforeEach
    void init() {
        dto = new CardTransferDto();
        dto.setId(1L);
        dto.setAmount(new BigDecimal("100.00"));
        dto.setCardNumber(1234567890L);
        dto.setPurpose("Test transfer");
        dto.setAccountDetailsId(1L);

        entity = new CardTransferEntity();
        entity.setId(1L);
        entity.setAmount(new BigDecimal("200.00"));
        entity.setCardNumber(9876543210L);
        entity.setPurpose("Another test transfer");
        entity.setAccountDetailsId(1L);
    }

    @Test
    @DisplayName("поиск по нескольким id, позитивный сценарий")
    void findAllByIdPositiveTest() {
        List<Long> ids = Arrays.asList(1L, 2L, 3L);

        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        when(repository.findById(2L)).thenReturn(Optional.of(entity));
        when(repository.findById(3L)).thenReturn(Optional.of(entity));

        when(mapper.toDto(entity)).thenReturn(dto);
        when(mapper.toDto(entity)).thenReturn(dto);
        when(mapper.toDto(entity)).thenReturn(dto);

        List<CardTransferDto> result = service.findAllById(ids);

        Assertions.assertEquals(ids.size(), result.size());
        Assertions.assertEquals(dto, result.get(0));
        Assertions.assertEquals(dto, result.get(1));
        Assertions.assertEquals(dto, result.get(2));
    }

    @Test
    @DisplayName("поиск по нескольким несуществующим id, негативный сценарий")
    void findAllByNonExistIdNegativeTest() {
        List<Long> ids = List.of(1L, 2L, 3L);

        when(repository.findById(anyLong())).thenThrow(EntityNotFoundException.class);

        assertThrows(javax.persistence.EntityNotFoundException.class, () -> {
            service.findAllById(ids);
        });
    }

    @Test
    @DisplayName("поиск по id, позитивный сценарий")
    void findByIdPositiveTest() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(entity));
        when(mapper.toDto(entity)).thenReturn(dto);

        CardTransferDto result = service.findById(anyLong());

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(dto.getId(), result.getId()),
                () -> assertEquals(dto.getAmount(), result.getAmount()),
                () -> assertEquals(dto.getCardNumber(), result.getCardNumber()),
                () -> assertEquals(dto.getPurpose(), result.getPurpose()),
                () -> assertEquals(dto.getAccountDetailsId(), result.getAccountDetailsId())
        );
    }

    @Test
    @DisplayName("поиск по несуществующему id, негативный сценарий")
    void findByNonExistIdNegativeTest() {
        when(repository.findById(anyLong())).thenThrow(EntityNotFoundException.class);

        assertThrows(javax.persistence.EntityNotFoundException.class, () -> {
            service.findById(anyLong());
        });

        verify(mapper, never()).toDto(any(CardTransferEntity.class));
    }


    @Test
    @DisplayName("сохранение по id, позитивный сценарий")
    void saveByIdPositiveTest() {
        when(repository.save(entity)).thenReturn(entity);
        when(mapper.toEntity(dto)).thenReturn(entity);
        when(mapper.toDto(entity)).thenReturn(dto);

        CardTransferDto result = service.save(dto);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(dto.getId(), result.getId()),
                () -> assertEquals(dto.getAmount(), result.getAmount()),
                () -> assertEquals(dto.getCardNumber(), result.getCardNumber()),
                () -> assertEquals(dto.getPurpose(), result.getPurpose()),
                () -> assertEquals(dto.getAccountDetailsId(), result.getAccountDetailsId())
        );
    }

    @Test
    @DisplayName("сохранение null, негативный сценарий")
    void saveNullNegativeTest() {
        when(repository.save(entity)).thenReturn(null);
        when(mapper.toEntity(dto)).thenReturn(entity);

        CardTransferDto result = service.save(dto);

        assertNull(result);
    }

    @Test
    @DisplayName("обновление по id, позитивный сценарий")
    void updateByIdPositiveTest() {
        when(repository.findById(eq(1L))).thenReturn(Optional.of(entity));
        when(mapper.mergeToEntity(dto, entity)).thenReturn(entity);
        when(mapper.toDto(entity)).thenReturn(dto);
        when(repository.save(entity)).thenReturn(entity);

        CardTransferDto result = service.update(1L, dto);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(dto.getId(), result.getId()),
                () -> assertEquals(dto.getAmount(), result.getAmount()),
                () -> assertEquals(dto.getCardNumber(), result.getCardNumber()),
                () -> assertEquals(dto.getPurpose(), result.getPurpose()),
                () -> assertEquals(dto.getAccountDetailsId(), result.getAccountDetailsId())
        );

        verify(repository).findById(eq(1L));
        verify(mapper).mergeToEntity(dto, entity);
        verify(repository).save(entity);
        verify(mapper).toDto(entity);

    }

    @Test
    @DisplayName("обновление по несуществующему id, негативный сценарий")
    void updateByNonExistIdNegativeTest() {
        when(repository.findById(anyLong())).thenThrow(EntityNotFoundException.class);

        assertThrows(javax.persistence.EntityNotFoundException.class, () -> {
            service.update(1L, dto);
        });
        verify(mapper, never()).toDto(any(CardTransferEntity.class));
    }
}
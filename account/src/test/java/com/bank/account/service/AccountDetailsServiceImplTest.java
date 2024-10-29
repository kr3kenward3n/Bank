package com.bank.account.service;

import com.bank.account.dto.AccountDetailsDto;
import com.bank.account.entity.AccountDetailsEntity;
import com.bank.account.mapper.AccountDetailsMapper;
import com.bank.account.repository.AccountDetailsRepository;
import com.bank.account.service.common.ExceptionReturner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class AccountDetailsServiceImplTest {

    @InjectMocks
    private AccountDetailsServiceImpl accountDetailsService; // Correct reference

    @Mock
    private AccountDetailsRepository repository;

    @Mock
    private AccountDetailsMapper mapper;

    @Mock
    private ExceptionReturner exceptionReturner;

    private static final Long ACCOUNT_ID = 1L;
    private static final AccountDetailsEntity ACCOUNT_DETAILS_ENTITY = new AccountDetailsEntity();
    private static final AccountDetailsDto ACCOUNT_DETAILS_DTO = new AccountDetailsDto();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findById_ShouldReturnAccountDetailsDto_WhenEntityExists() {
        when(repository.findById(ACCOUNT_ID)).thenReturn(Optional.of(ACCOUNT_DETAILS_ENTITY));
        when(mapper.toDto(ACCOUNT_DETAILS_ENTITY)).thenReturn(ACCOUNT_DETAILS_DTO);

        AccountDetailsDto result = accountDetailsService.findById(ACCOUNT_ID);

        assertEquals(ACCOUNT_DETAILS_DTO, result);
    }

    @Test
    void findById_ShouldThrowException_WhenEntityDoesNotExist() {
        when(repository.findById(ACCOUNT_ID)).thenReturn(Optional.empty());
        when(exceptionReturner.getEntityNotFoundException("Не существующий id = " + ACCOUNT_ID))
                .thenThrow(new RuntimeException("Не существующий id = " + ACCOUNT_ID));

        assertThrows(RuntimeException.class, () -> accountDetailsService.findById(ACCOUNT_ID));
    }

    @Test
    void findAllById_ShouldReturnListOfAccountDetailsDto_WhenEntitiesExist() {
        List<Long> ids = List.of(ACCOUNT_ID);
        when(repository.findById(ACCOUNT_ID)).thenReturn(Optional.of(ACCOUNT_DETAILS_ENTITY));
        when(mapper.toDtoList(List.of(ACCOUNT_DETAILS_ENTITY))).thenReturn(List.of(ACCOUNT_DETAILS_DTO));

        List<AccountDetailsDto> result = accountDetailsService.findAllById(ids);

        assertEquals(List.of(ACCOUNT_DETAILS_DTO), result);
    }

    @Test
    void save_ShouldReturnSavedAccountDetailsDto() {
        when(mapper.toEntity(ACCOUNT_DETAILS_DTO)).thenReturn(ACCOUNT_DETAILS_ENTITY);
        when(repository.save(ACCOUNT_DETAILS_ENTITY)).thenReturn(ACCOUNT_DETAILS_ENTITY);
        when(mapper.toDto(ACCOUNT_DETAILS_ENTITY)).thenReturn(ACCOUNT_DETAILS_DTO);

        AccountDetailsDto result = accountDetailsService.save(ACCOUNT_DETAILS_DTO);

        assertEquals(ACCOUNT_DETAILS_DTO, result);
    }

    @Test
    @Transactional
    void update_ShouldReturnUpdatedAccountDetailsDto() {
        when(repository.findById(ACCOUNT_ID)).thenReturn(Optional.of(ACCOUNT_DETAILS_ENTITY));
        when(mapper.mergeToEntity(ACCOUNT_DETAILS_ENTITY, ACCOUNT_DETAILS_DTO)).thenReturn(ACCOUNT_DETAILS_ENTITY);
        when(repository.save(ACCOUNT_DETAILS_ENTITY)).thenReturn(ACCOUNT_DETAILS_ENTITY);
        when(mapper.toDto(ACCOUNT_DETAILS_ENTITY)).thenReturn(ACCOUNT_DETAILS_DTO);

        AccountDetailsDto result = accountDetailsService.update(ACCOUNT_ID, ACCOUNT_DETAILS_DTO);

        assertEquals(ACCOUNT_DETAILS_DTO, result);
    }
}
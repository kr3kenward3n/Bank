package com.bank.account.service;

import com.bank.account.dto.AuditDto;
import com.bank.account.entity.AuditEntity;
import com.bank.account.mapper.AccountAuditMapper;
import com.bank.account.repository.AccountAuditRepository;
import com.bank.account.service.common.ExceptionReturner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class AccountAuditServiceImplTest {

    @InjectMocks
    private AccountAuditServiceImpl accountAuditService;

    @Mock
    private AccountAuditRepository repository;

    @Mock
    private AccountAuditMapper mapper;

    @Mock
    private ExceptionReturner exceptionReturner;

    private static final Long AUDIT_ID = 1L;
    private static final AuditEntity AUDIT_ENTITY = new AuditEntity();
    private static final AuditDto AUDIT_DTO = new AuditDto();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findById_ShouldReturnAuditDto_WhenEntityExists() {
        when(repository.findById(AUDIT_ID)).thenReturn(Optional.of(AUDIT_ENTITY));
        when(mapper.toDto(AUDIT_ENTITY)).thenReturn(AUDIT_DTO);

        AuditDto result = accountAuditService.findById(AUDIT_ID);

        assertEquals(AUDIT_DTO, result);
    }

    @Test
    void findById_ShouldThrowException_WhenEntityDoesNotExist() {
        when(repository.findById(AUDIT_ID)).thenReturn(Optional.empty());
        when(exceptionReturner.getEntityNotFoundException("Не существующий id = " + AUDIT_ID))
                .thenThrow(new RuntimeException("Не существующий id = " + AUDIT_ID));

        assertThrows(RuntimeException.class, () -> accountAuditService.findById(AUDIT_ID));
    }
}
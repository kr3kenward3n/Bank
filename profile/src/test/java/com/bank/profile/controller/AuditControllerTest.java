package com.bank.profile.controller;

import com.bank.profile.dto.AuditDto;
import com.bank.profile.service.AuditService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import javax.persistence.EntityNotFoundException;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Тесты для {@link AuditController}
 */
@WebMvcTest(AuditController.class)
@DisplayName("Тесты для AuditController")
class AuditControllerTest {
    private final Long testId = 1L;
    private AuditDto auditDto;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuditService service;

    @BeforeEach
    void createDto() {
        auditDto = new AuditDto();
        auditDto.setId(testId);
        auditDto.setCreatedBy("REST");
        auditDto.setModifiedBy("REST");
        auditDto.setEntityType("REST");
    }


    @Test
    @SneakyThrows
    @DisplayName("Чтение по id, позитивный сценарий")
    void readByIdPositiveTest() {
        when(service.findById(testId)).thenReturn(auditDto);

        mockMvc.perform(get("/audit/{id}", testId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(testId))
                .andExpect(jsonPath("$.createdBy").value("REST"))
                .andExpect(jsonPath("$.modifiedBy").value("REST"))
                .andExpect(jsonPath("$.entityType").value("REST"));
    }


    @Test
    @SneakyThrows
    @DisplayName("Чтение по несуществующему id, негативный сценарий")
    void readByNonExistIdNegativeTest() {
        when(service.findById(testId)).thenThrow(
                new EntityNotFoundException("accountDetailsId с данным id не найден!")
        );

        mockMvc.perform(get("/audit/{id}", testId))
                .andExpect(status().isNotFound());
    }

}


package com.bank.transfer.controller;

import com.bank.transfer.dto.AuditDto;
import com.bank.transfer.service.Impl.AuditServiceImpl;
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

@WebMvcTest(AuditController.class)
class AuditControllerTest {

    AuditDto dto;

    private final long id = 1L;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuditServiceImpl service;

    @BeforeEach
    void init() {
        dto = new AuditDto();
        dto.setId(1L);
        dto.setEntityType("W");
        dto.setOperationType("W");
        dto.setCreatedBy("W");
        dto.setModifiedBy("W");
        dto.setNewEntityJson("Entity");
        dto.setEntityJson("Entity");
    }

    @Test
    @SneakyThrows
    @DisplayName("поиск по id, позитивный сценарий")
    void readByIdPositiveTest() {
        when(service.findById(id)).thenReturn(dto);

        mockMvc.perform(get("/audit/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(dto.getId()))
                .andExpect(jsonPath("$.entityType").value(dto.getEntityType()))
                .andExpect(jsonPath("$.operationType").value(dto.getOperationType()))
                .andExpect(jsonPath("$.createdBy").value(dto.getCreatedBy()))
                .andExpect(jsonPath("$.modifiedBy").value(dto.getModifiedBy()))
                .andExpect(jsonPath("$.newEntityJson").value(dto.getNewEntityJson()))
                .andExpect(jsonPath("$.entityJson").value(dto.getEntityJson()));
    }

    @Test
    @SneakyThrows
    @DisplayName("поиск по несуществующему id, негативный сценарий")
    void readByNonExistIdNegativeTest() {
        when(service.findById(id)).thenThrow(new EntityNotFoundException("Entity not found"));

        mockMvc.perform(get("/audit/{id}", id))
                .andExpect(status().isNotFound());
    }
}
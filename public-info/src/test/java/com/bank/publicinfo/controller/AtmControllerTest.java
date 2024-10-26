package com.bank.publicinfo.controller;

import com.bank.publicinfo.dto.AtmDto;
import com.bank.publicinfo.service.impl.AtmServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.EntityNotFoundException;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Тесты для {@link AtmController}
 */
@WebMvcTest(AtmController.class)
class AtmControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private AtmServiceImpl service;

    @Test
    @DisplayName("Чтение по id, позитивный сценарий")
    void readByIdPositiveTest() throws Exception {
        AtmDto dto = new AtmDto(1L, null, null, null, null, null);
        when(service.findById(1L)).thenReturn(dto);

        mockMvc.perform(get("/atm/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(dto.getId()));

    }

    @Test
    @DisplayName("Чтение по несуществующему id, негативный сценарий")
    void readByNonExistIdNegativeTest() throws Exception {
        when(service.findById(1L)).thenThrow(new EntityNotFoundException());

        mockMvc.perform(get("/atm/{id}", 1L))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Чтение по нескольким id, позитивный сценарий")
    void readAllByIdPositiveTest() throws Exception {
        List<Long> ids = List.of(1L);
        List<AtmDto> dtos = List.of(new AtmDto(1L, null, null,
                null, null, null));
        when(service.findAllById(ids)).thenReturn(dtos);

        mockMvc.perform(get("/atm/read/all")
                        .param("ids", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(dtos)));
    }

    @Test
    @DisplayName("Чтение по нескольким несуществующим id, негативный сценарий")
    void readAllByNonExistIdNegativeTest() throws Exception {
        List<Long> ids = List.of(1L);
        when(service.findAllById(ids)).thenThrow(new EntityNotFoundException());

        mockMvc.perform(get("/atm/read/all")
                        .param("ids", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Создание, позитивный сценарий")
    void createPositiveTest() throws Exception {
        AtmDto dto = new AtmDto(1L, "address", null, null, null, null);
        when(service.create(dto)).thenReturn(dto);

        mockMvc.perform(post("/atm/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(dto.getId()))
                .andExpect(jsonPath("$.address").value(dto.getAddress()));
    }

    @Test
    @DisplayName("Создание, передан null, негативный сценарий")
    void createWithNullNegativeTest() throws Exception {
        mockMvc.perform(post("/atm/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(null)))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @DisplayName("Обновление, позитивный сценарий")
    void updatePositiveTest() throws Exception {
        AtmDto dto = new AtmDto(1L, "address", null, null, null, null);
        when(service.update(1L, dto)).thenReturn(dto);

        mockMvc.perform(put("/atm/update/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(dto.getId()))
                .andExpect(jsonPath("$.address").value(dto.getAddress()));
    }

    @Test
    @DisplayName("Обновление, передан null, негативный сценарий")
    void updateWithNullNegativeTest() throws Exception {
        mockMvc.perform(put("/atm/update/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(null)))
                .andExpect(status().is4xxClientError());
    }
}

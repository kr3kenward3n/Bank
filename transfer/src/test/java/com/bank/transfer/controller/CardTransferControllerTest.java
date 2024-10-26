package com.bank.transfer.controller;

import com.bank.transfer.dto.CardTransferDto;
import com.bank.transfer.service.Impl.CardTransferServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@WebMvcTest(CardTransferController.class)
class CardTransferControllerTest {

    CardTransferDto dto;

    private final long id = 1L;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CardTransferServiceImpl service;

    @BeforeEach
    void init() {
        dto = new CardTransferDto();
        dto.setId(id);
        dto.setAmount(new BigDecimal("753426"));
        dto.setCardNumber(1234567890L);
        dto.setPurpose("Test transfer");
        dto.setAccountDetailsId(1L);
    }

    @Test
    @SneakyThrows
    @DisplayName("поиск по нескольким id, позитивный сценарий")
    void readAllByIdPositiveTest() {
        List<Long> listEntity = new ArrayList<>(List.of(id, id, id));
        List<CardTransferDto> result = new ArrayList<>(List.of(dto, dto, dto));

        when(service.findAllById(listEntity)).thenReturn(result);

        mockMvc.perform(get("/card/read/all").param("ids", "" + id, "" + id, "" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(listEntity.size())));
    }

    @Test
    @SneakyThrows
    @DisplayName("поиск по нескольким несуществующим id, негативный сценарий")
    void readAllByNonExistIdNegativeTest() {
        when(service.findAllById(new ArrayList<>(List.of(id, id, id))))
                .thenReturn(Collections.emptyList());

        mockMvc.perform(get("/card/read/all").param("ids", "" + id, "" + id, "" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    @DisplayName("поиск по id, позитивный сценарий")
    void readByIdPositiveTest() {
        when(service.findById(id)).thenReturn(dto);

        mockMvc.perform(get("/card/read/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(dto.getId()))
                .andExpect(jsonPath("$.amount").value(dto.getAmount()))
                .andExpect(jsonPath("$.cardNumber").value(dto.getCardNumber()))
                .andExpect(jsonPath("$.purpose").value(dto.getPurpose()))
                .andExpect(jsonPath("$.accountDetailsId").value(dto.getAccountDetailsId()));
    }

    @Test
    @SneakyThrows
    @DisplayName("поиск по несуществующему id, негативный сценарий")
    void readByNonExistIdNegativeTest() {
        when(service.findById(id)).thenThrow(new EntityNotFoundException("Entity not found"));

        mockMvc.perform(get("/card/read/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    @SneakyThrows
    @DisplayName("создание пользователя, позитивный сценарий")
    void createPositiveTest() {
        when(service.save(dto)).thenReturn(dto);

        mockMvc.perform(post("/card/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(dto.getId()))
                .andExpect(jsonPath("$.amount").value(dto.getAmount()))
                .andExpect(jsonPath("$.cardNumber").value(dto.getCardNumber()))
                .andExpect(jsonPath("$.purpose").value(dto.getPurpose()))
                .andExpect(jsonPath("$.accountDetailsId").value(dto.getAccountDetailsId()));
    }

    @Test
    @SneakyThrows
    @DisplayName("создание пользователя по несуществующему id, негативный сценарий")
    void createByNonExistIdNegativeTest() {
        when(service.save(null)).thenReturn(null);

        mockMvc.perform(post("/card/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(null)))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @SneakyThrows
    @DisplayName("обновление пользователя, позитивный сценарий")
    void updatePositiveTest() {
        CardTransferDto result = new CardTransferDto();
        result.setId(id);
        result.setAmount(new BigDecimal("200"));
        result.setCardNumber(1234567890L);
        result.setPurpose("Test transfer two");
        result.setAccountDetailsId(id);

        when(service.update(id, dto)).thenReturn(result);

        mockMvc.perform(put("/card/update/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(result.getId()))
                .andExpect(jsonPath("$.amount").value(result.getAmount()))
                .andExpect(jsonPath("$.cardNumber").value(result.getCardNumber()))
                .andExpect(jsonPath("$.purpose").value(result.getPurpose()))
                .andExpect(jsonPath("$.accountDetailsId").value(result.getAccountDetailsId()));
    }

    @Test
    @SneakyThrows
    @DisplayName("обновление несуществующего  пользователя, негативный сценарий")
    void updateByNonExistIdNegativeTest() {
        when(service.update(id, dto)).thenThrow
                (new EntityNotFoundException("update impossible, entity not found"));

        mockMvc.perform(put("/card/details/update/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isNotFound());
    }
}
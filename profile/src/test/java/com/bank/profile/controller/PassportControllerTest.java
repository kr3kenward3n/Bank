package com.bank.profile.controller;

import com.bank.profile.dto.PassportDto;
import com.bank.profile.service.impl.PassportServiceImp;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Тесты для {@link PassportController}
 */
@WebMvcTest(PassportController.class)
@DisplayName("Тесты для PassportController")
class PassportControllerTest {

    private final long testId = 1L;

    private PassportDto passportDto;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PassportServiceImp service;

    @BeforeEach
    void createDto() {
        passportDto = new PassportDto();
        passportDto.setId(testId);
        passportDto.setGender("man");
        passportDto.setFirstName("Aleksandr");

    }

    @Test
    @SneakyThrows
    @DisplayName("Чтение по id, позитивный сценарий")
    void readByIdPositiveTest() {
        when(service.findById(testId)).thenReturn(passportDto);

        mockMvc.perform(get("/passport/read/{id}", testId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(passportDto.getId()))
                .andExpect(jsonPath("$.gender").value(passportDto.getGender()))
                .andExpect(jsonPath("$.firstName").value(passportDto.getFirstName()));
    }


    @Test
    @SneakyThrows
    @DisplayName("Чтение по несуществующему id, негативный сценарий")
    void readByNonExistIdNegativeTest() {
        when(service.findById(testId))
                .thenThrow(new EntityNotFoundException("accountDetailsId с данным id не найден!"));

        mockMvc.perform(get("/passport/read/{id}", testId))
                .andExpect(status().isNotFound());
    }


    @Test
    @SneakyThrows
    @DisplayName("Создание, позитивный сценарий")
    void createPositiveTest() {
        when(service.save(passportDto)).thenReturn(passportDto);

        mockMvc.perform(post("/passport/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(passportDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(passportDto.getId()))
                .andExpect(jsonPath("$.gender").value(passportDto.getGender()))
                .andExpect(jsonPath("$.firstName").value(passportDto.getFirstName()));
    }


    @Test
    @SneakyThrows
    @DisplayName("Создание по id, передан null, негативный сценарий")
    void createNullNegativeTest() {
        when(service.save(null)).thenReturn(null);

        mockMvc.perform(post("/passport/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(null)))
                .andExpect(status().is4xxClientError());
    }


    @Test
    @SneakyThrows
    @DisplayName("Обновление по id, позитивный сценарий")
    void updateByIdPositiveTest() {
        PassportDto result = new PassportDto();
        result.setId(testId);
        result.setGender("Woman");
        result.setFirstName("Nastya");

        when(service.update(testId, passportDto)).thenReturn(result);

        mockMvc.perform(put("/passport/update/{id}", testId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(passportDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(result.getId()))
                .andExpect(jsonPath("$.gender").value(result.getGender()))
                .andExpect(jsonPath("$.firstName").value(result.getFirstName()));
    }


    @Test
    @SneakyThrows
    @DisplayName("Обновление по несуществующему id, негативный сценарий")
    void updateByNonExistIdNegativeTest() {
        when(service.update(testId, passportDto))
                .thenThrow(new EntityNotFoundException("Обновление невозможно, accountDetailsId не найден!"));

        mockMvc.perform(put("/passport/update/{id}", testId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(passportDto)))
                .andExpect(status().isNotFound());
    }


    @Test
    @SneakyThrows
    @DisplayName("Чтение по нескольким id, позитивный сценарий")
    void readAllByIdPositiveTestTest() {
        List<Long> longList = new ArrayList<>(List.of(testId, testId, testId));
        List<PassportDto> result = new ArrayList<>(List.of(passportDto, passportDto, passportDto));

        when(service.findAllById(longList)).thenReturn(result);

        mockMvc.perform(get("/passport/read/all")
                        .param("ids", "" + testId, "" + testId, "" + testId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(passportDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(longList.size())));
    }


    @Test
    @SneakyThrows
    @DisplayName("Чтение по нескольким несуществующим id, негативный сценарий")
    void readAllByNonExistIdNegativeTest() {
        when(service.findAllById(new ArrayList<>(List.of(testId, testId, testId))))
                .thenReturn(Collections.emptyList());

        mockMvc.perform(get("/passport/read/all")
                        .param("ids", "" + testId, "" + testId, "" + testId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
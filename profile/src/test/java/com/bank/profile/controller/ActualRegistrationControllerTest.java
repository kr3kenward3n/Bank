package com.bank.profile.controller;


import com.bank.profile.dto.ActualRegistrationDto;
import com.bank.profile.service.impl.ActualRegistrationServiceImp;
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
 * Тесты для {@link ActualRegistrationController}
 */
@WebMvcTest(ActualRegistrationController.class)
@DisplayName("Тесты для ActualRegistrationController")
class ActualRegistrationControllerTest {

    private final long testId = 1L;

    private ActualRegistrationDto actualRegistrationDto;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ActualRegistrationServiceImp service;

    @BeforeEach
    void createDto() {
        actualRegistrationDto = new ActualRegistrationDto();
        actualRegistrationDto.setId(testId);
        actualRegistrationDto.setCity("Moskva");
        actualRegistrationDto.setHouseBlock("33");

    }

    @Test
    @SneakyThrows
    @DisplayName("Чтение по id, позитивный сценарий")
    void readByIdPositiveTest() {
        when(service.findById(testId)).thenReturn(actualRegistrationDto);

        mockMvc.perform(get("/actual/registration/read/{id}", testId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(actualRegistrationDto.getId()))
                .andExpect(jsonPath("$.city").value(actualRegistrationDto.getCity()))
                .andExpect(jsonPath("$.houseBlock").value(actualRegistrationDto.getHouseBlock()));
    }


    @Test
    @SneakyThrows
    @DisplayName("Чтение по несуществующему id, негативный сценарий")
    void readByNonExistIdNegativeTest() {
        when(service.findById(testId))
                .thenThrow(new EntityNotFoundException("accountDetailsId с данным id не найден!"));

        mockMvc.perform(get("/actual/registration/read/{id}", testId))
                .andExpect(status().isNotFound());
    }


    @Test
    @SneakyThrows
    @DisplayName("Создание, позитивный сценарий")
    void createPositiveTest() {
        when(service.save(actualRegistrationDto)).thenReturn(actualRegistrationDto);

        mockMvc.perform(post("/actual/registration/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(actualRegistrationDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(actualRegistrationDto.getId()))
                .andExpect(jsonPath("$.city").value(actualRegistrationDto.getCity()))
                .andExpect(jsonPath("$.houseBlock").value(actualRegistrationDto.getHouseBlock()));
    }


    @Test
    @SneakyThrows
    @DisplayName("Создание по id, передан null, негативный сценарий")
    void createNullNegativeTest() {
        when(service.save(null)).thenReturn(null);

        mockMvc.perform(post("/actual/registration/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(null)))
                .andExpect(status().is4xxClientError());
    }


    @Test
    @SneakyThrows
    @DisplayName("Обновление по id, позитивный сценарий")
    void updateByIdPositiveTest() {
        ActualRegistrationDto result = new ActualRegistrationDto();
        result.setId(testId);
        result.setCity("Piter");
        result.setHouseBlock("78");

        when(service.update(testId, actualRegistrationDto)).thenReturn(result);

        mockMvc.perform(put("/actual/registration/update/{id}", testId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(actualRegistrationDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(result.getId()))
                .andExpect(jsonPath("$.city").value(result.getCity()))
                .andExpect(jsonPath("$.houseBlock").value(result.getHouseBlock()));
    }


    @Test
    @SneakyThrows
    @DisplayName("Обновление по несуществующему id, негативный сценарий")
    void updateByNonExistIdNegativeTest() {
        when(service.update(testId, actualRegistrationDto))
                .thenThrow(new EntityNotFoundException("Обновление невозможно, accountDetailsId не найден!"));

        mockMvc.perform(put("/actual/registration/update/{id}", testId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(actualRegistrationDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    @SneakyThrows
    @DisplayName("Чтение по нескольким id, позитивный сценарий")
    void readAllByIdPositiveTestTest() {
        List<Long> longList = new ArrayList<>(List.of(testId, testId, testId));
        List<ActualRegistrationDto> result = new ArrayList<>(List.of(
                actualRegistrationDto, actualRegistrationDto, actualRegistrationDto)
        );

        when(service.findAllById(longList)).thenReturn(result);

        mockMvc.perform(get("/actual/registration/read/all")
                        .param("ids", "" + testId, "" + testId, "" + testId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(actualRegistrationDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(longList.size())));
    }


    @Test
    @SneakyThrows
    @DisplayName("Чтение по нескольким несуществующим id, негативный сценарий")
    void readAllByNonExistIdNegativeTest() {
        when(service.findAllById(new ArrayList<>(List.of(testId, testId, testId)))).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/actual/registration/read/all")
                        .param("ids", "" + testId, "" + testId, "" + testId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


}
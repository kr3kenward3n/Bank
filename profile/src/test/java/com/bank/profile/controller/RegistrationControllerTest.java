package com.bank.profile.controller;

import com.bank.profile.dto.RegistrationDto;
import com.bank.profile.service.impl.RegistrationServiceImp;
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
 * Тесты для {@link RegistrationController}
 */
@WebMvcTest(RegistrationController.class)
@DisplayName("Тесты для RegistrationController")
class RegistrationControllerTest {

    private final long testId = 1L;

    private RegistrationDto registrationDto;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RegistrationServiceImp service;

    @BeforeEach
    void createDto() {
        registrationDto = new RegistrationDto();
        registrationDto.setId(testId);
        registrationDto.setRegion("Mosk.obl");
        registrationDto.setStreet("Severnaya");
    }


    @Test
    @SneakyThrows
    @DisplayName("Чтение по id, позитивный сценарий")
    void readByIdPositiveTest() {
        when(service.findById(testId)).thenReturn(registrationDto);

        mockMvc.perform(get("/registration/read/{id}", testId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(registrationDto.getId()))
                .andExpect(jsonPath("$.region").value(registrationDto.getRegion()))
                .andExpect(jsonPath("$.street").value(registrationDto.getStreet()));
    }


    @Test
    @SneakyThrows
    @DisplayName("Чтение по несуществующему id, негативный сценарий")
    void readByNonExistIdNegativeTest() {
        when(service.findById(testId))
                .thenThrow(new EntityNotFoundException("accountDetailsId с данным id не найден!"));

        mockMvc.perform(get("/account/details/read/{id}", testId))
                .andExpect(status().isNotFound());
    }


    @Test
    @SneakyThrows
    @DisplayName("Создание, позитивный сценарий")
    void createPositiveTest() {
        when(service.save(registrationDto)).thenReturn(registrationDto);

        mockMvc.perform(post("/registration/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registrationDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(registrationDto.getId()))
                .andExpect(jsonPath("$.region").value(registrationDto.getRegion()))
                .andExpect(jsonPath("$.street").value(registrationDto.getStreet()));
    }


    @Test
    @SneakyThrows
    @DisplayName("Создание по id, передан null, негативный сценарий")
    void createNullNegativeTest() {
        when(service.save(null)).thenReturn(null);

        mockMvc.perform(post("/registration/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(null)))
                .andExpect(status().is4xxClientError());
    }


    @Test
    @SneakyThrows
    @DisplayName("Обновление по id, позитивный сценарий")
    void updateByIdPositiveTest() {
        RegistrationDto result = new RegistrationDto();
        result.setId(testId);
        result.setRegion("Kazan.obl");
        result.setStreet("Mongola");

        when(service.update(testId, registrationDto)).thenReturn(result);

        mockMvc.perform(put("/registration/update/{id}", testId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registrationDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(result.getId()))
                .andExpect(jsonPath("$.region").value(result.getRegion()))
                .andExpect(jsonPath("$.street").value(result.getStreet()));
    }


    @Test
    @SneakyThrows
    @DisplayName("Обновление по несуществующему id, негативный сценарий")
    void updateByNonExistIdNegativeTest() {
        when(service.update(testId, registrationDto))
                .thenThrow(new EntityNotFoundException("Обновление невозможно, accountDetailsId не найден!"));

        mockMvc.perform(put("/registration/update/{id}", testId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registrationDto)))
                .andExpect(status().isNotFound());
    }


    @Test
    @SneakyThrows
    @DisplayName("Чтение по нескольким id, позитивный сценарий")
    void readAllByIdPositiveTestTest() {
        List<Long> longList = new ArrayList<>(List.of(testId, testId, testId));
        List<RegistrationDto> result = new ArrayList<>(List.of(registrationDto, registrationDto, registrationDto));

        when(service.findAllById(longList)).thenReturn(result);

        mockMvc.perform(get("/registration/read/all")
                        .param("ids", "" + testId, "" + testId, "" + testId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registrationDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(longList.size())));
    }


    @Test
    @SneakyThrows
    @DisplayName("Чтение по нескольким несуществующим id, негативный сценарий")
    void readAllByNonExistIdNegativeTest() {
        when(service.findAllById(new ArrayList<>(List.of(testId, testId, testId))))
                .thenReturn(Collections.emptyList());

        mockMvc.perform(get("/registration/read/all")
                        .param("ids", "" + testId, "" + testId, "" + testId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
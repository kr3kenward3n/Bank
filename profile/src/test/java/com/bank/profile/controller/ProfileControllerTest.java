package com.bank.profile.controller;

import com.bank.profile.dto.ProfileDto;
import com.bank.profile.service.impl.ProfileServiceImp;
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
 * Тесты для {@link ProfileController}
 */
@WebMvcTest(ProfileController.class)
@DisplayName("Тесты для ProfileController")
class ProfileControllerTest {


    private final long testId = 1L;

    private ProfileDto profileDto;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProfileServiceImp service;

    @BeforeEach
    void createDto() {
        profileDto = new ProfileDto();
        profileDto.setId(testId);
        profileDto.setSnils(344544L);
        profileDto.setInn(123456789000L);
    }


    @Test
    @SneakyThrows
    @DisplayName("Чтение по id, позитивный сценарий")
    void readByIdPositiveTest() {
        when(service.findById(testId)).thenReturn(profileDto);

        mockMvc.perform(get("/profile/read/{id}", testId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(profileDto.getId()))
                .andExpect(jsonPath("$.snils").value(profileDto.getSnils()))
                .andExpect(jsonPath("$.inn").value(profileDto.getInn()));
    }


    @Test
    @SneakyThrows
    @DisplayName("Чтение по несуществующему id, негативный сценарий")
    void readByNonExistIdNegativeTest() {
        when(service.findById(testId))
                .thenThrow(new EntityNotFoundException("accountDetailsId с данным id не найден!"));

        mockMvc.perform(get("/profile/read/{id}", testId))
                .andExpect(status().isNotFound());
    }


    @Test
    @SneakyThrows
    @DisplayName("Создание, позитивный сценарий")
    void createPositiveTest() {
        when(service.save(profileDto)).thenReturn(profileDto);

        mockMvc.perform(post("/profile/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(profileDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(profileDto.getId()))
                .andExpect(jsonPath("$.snils").value(profileDto.getSnils()))
                .andExpect(jsonPath("$.inn").value(profileDto.getInn()));
    }


    @Test
    @SneakyThrows
    @DisplayName("Создание по id, передан null, негативный сценарий")
    void createNullNegativeTest() {
        when(service.save(null)).thenReturn(null);

        mockMvc.perform(post("/profile/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(null)))
                .andExpect(status().is4xxClientError());
    }


    @Test
    @SneakyThrows
    @DisplayName("Обновление по id, позитивный сценарий")
    void updateByIdPositiveTest() {
        ProfileDto result = new ProfileDto();
        result.setId(testId);
        result.setSnils(9965813L);
        result.setInn(47862186745L);

        when(service.update(testId, profileDto)).thenReturn(result);

        mockMvc.perform(put("/profile/update/{id}", testId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(profileDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(result.getId()))
                .andExpect(jsonPath("$.snils").value(result.getSnils()))
                .andExpect(jsonPath("$.inn").value(result.getInn()));
    }


    @Test
    @SneakyThrows
    @DisplayName("Обновление по несуществующему id, негативный сценарий")
    void updateByNonExistIdNegativeTest() {
        when(service.update(testId, profileDto))
                .thenThrow(new EntityNotFoundException("Обновление невозможно, accountDetailsId не найден!"));

        mockMvc.perform(put("/profile/update/{id}", testId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(profileDto)))
                .andExpect(status().isNotFound());
    }


    @Test
    @SneakyThrows
    @DisplayName("Чтение по нескольким id, позитивный сценарий")
    void readAllByIdPositiveTestTest() {
        List<Long> longList = new ArrayList<>(List.of(testId, testId, testId));
        List<ProfileDto> result = new ArrayList<>(List.of(profileDto, profileDto, profileDto));

        when(service.findAllById(longList)).thenReturn(result);

        mockMvc.perform(get("/profile/read/all")
                        .param("ids", "" + testId, "" + testId, "" + testId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(profileDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(longList.size())));
    }


    @Test
    @SneakyThrows
    @DisplayName("Чтение по нескольким несуществующим id, негативный сценарий")
    void readAllByNonExistIdNegativeTest() {
        when(service.findAllById(new ArrayList<>(List.of(testId, testId, testId))))
                .thenReturn(Collections.emptyList());

        mockMvc.perform(get("/profile/read/all")
                        .param("ids", "" + testId, "" + testId, "" + testId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


}
package com.bank.profile.controller;

import com.bank.profile.dto.AccountDetailsIdDto;
import com.bank.profile.dto.ProfileDto;
import com.bank.profile.service.impl.AccountDetailsIdServiceImp;
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
 * Тесты для {@link AccountDetailsIdController}
 */
@WebMvcTest(AccountDetailsIdController.class)
@DisplayName("Тесты для AccountDetailsIdController")
class AccountDetailsIdControllerTest {

    private final long testId = 1L;

    private AccountDetailsIdDto detailsIdDto;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AccountDetailsIdServiceImp service;

    @BeforeEach
    void createDto() {
        detailsIdDto = new AccountDetailsIdDto();
        detailsIdDto.setId(testId);
        detailsIdDto.setAccountId(testId);
        detailsIdDto.setProfile(new ProfileDto());
    }

    @Test
    @SneakyThrows
    @DisplayName("Чтение по id, позитивный сценарий")
    void readByIdPositiveTest() {
        when(service.findById(testId)).thenReturn(detailsIdDto);

        mockMvc.perform(get("/account/details/read/{id}", testId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(detailsIdDto.getId()))
                .andExpect(jsonPath("$.accountId").value(detailsIdDto.getAccountId()))
                .andExpect(jsonPath("$.profile").value(detailsIdDto.getProfile()));
    }


    @Test
    @SneakyThrows
    @DisplayName("Чтение по несуществующему id, негативный сценарий")
    void readByNonExistIdNegativeTest() {
        when(service.findById(testId)).thenThrow(
                new EntityNotFoundException("accountDetailsId с данным id не найден!")
        );

        mockMvc.perform(get("/account/details/read/{id}", testId))
                .andExpect(status().isNotFound());
    }


    @Test
    @SneakyThrows
    @DisplayName("Создание, позитивный сценарий")
    void createPositiveTest() {
        when(service.save(detailsIdDto)).thenReturn(detailsIdDto);

        mockMvc.perform(post("/account/details/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(detailsIdDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(detailsIdDto.getId()))
                .andExpect(jsonPath("$.accountId").value(detailsIdDto.getAccountId()))
                .andExpect(jsonPath("$.profile").value(detailsIdDto.getProfile()));
    }


    @Test
    @SneakyThrows
    @DisplayName("Создание по id, передан null, негативный сценарий")
    void createNullNegativeTest() {
        when(service.save(null)).thenReturn(null);

        mockMvc.perform(post("/account/details/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(null)))
                .andExpect(status().is4xxClientError());
    }


    @Test
    @SneakyThrows
    @DisplayName("Обновление по id, позитивный сценарий")
    void updateByIdPositiveTest() {
        AccountDetailsIdDto result = new AccountDetailsIdDto();
        result.setId(testId);
        result.setAccountId(testId + 1);
        result.setProfile(new ProfileDto());

        when(service.update(testId, detailsIdDto)).thenReturn(result);

        mockMvc.perform(put("/account/details/update/{id}", testId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(detailsIdDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(result.getId()))
                .andExpect(jsonPath("$.accountId").value(result.getAccountId()))
                .andExpect(jsonPath("$.profile").value(result.getProfile()));
    }


    @Test
    @SneakyThrows
    @DisplayName("Обновление по несуществующему id, негативный сценарий")
    void updateByNonExistIdNegativeTest() {
        when(service.update(testId, detailsIdDto))
                .thenThrow(new EntityNotFoundException("Обновление невозможно, accountDetailsId не найден!"));

        mockMvc.perform(put("/account/details/update/{id}", testId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(detailsIdDto)))
                .andExpect(status().isNotFound());
    }


    @Test
    @SneakyThrows
    @DisplayName("Чтение по нескольким id, позитивный сценарий")
    void readAllByIdPositiveTestTest() {
        List<Long> longList = new ArrayList<>(List.of(testId, testId, testId));
        List<AccountDetailsIdDto> result = new ArrayList<>(List.of(detailsIdDto, detailsIdDto, detailsIdDto));

        when(service.findAllById(longList)).thenReturn(result);

        mockMvc.perform(get("/account/details/read/all")
                        .param("ids", "" + testId, "" + testId, "" + testId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(longList.size())));
    }


    @Test
    @SneakyThrows
    @DisplayName("Чтение по нескольким несуществующим id, негативный сценарий")
    void readAllByNonExistIdNegativeTest() {
        when(service.findAllById(new ArrayList<>(List.of(testId, testId, testId))))
                .thenReturn(Collections.emptyList());

        mockMvc.perform(get("/account/details/read/all")
                        .param("ids", "" + testId, "" + testId, "" + testId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
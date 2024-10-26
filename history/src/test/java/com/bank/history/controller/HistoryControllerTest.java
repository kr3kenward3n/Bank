package com.bank.history.controller;

import com.bank.history.dto.HistoryDto;
import com.bank.history.service.HistoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

/**
 * Тесты для {@link HistoryController}
 */
@WebMvcTest(HistoryController.class)
@DisplayName("Тесты HistoryController")
public class HistoryControllerTest {

    private final Long id = 1L;

    private final HistoryDto history_1 = new HistoryDto(1L, 1L, 1L, 1L,
            1L, 1L, 1L);
    private final HistoryDto history_2 = new HistoryDto(2L, 2L, 2L, 2L,
            2L, 2L, 2L);
    private final HistoryDto history_3 = new HistoryDto(3L, 3L, 3L, 3L,
            3L, 3L, 3L);


    final ObjectMapper mapper = new ObjectMapper();
    private final List<HistoryDto> histories = List.of(history_1, history_2, history_3);

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HistoryService service;


    @Test
    @DisplayName("Чтение по id, позитивный сценарий")
    public void readByIdPositiveTest() throws Exception {
        Mockito.when(service.readById(ArgumentMatchers.any())).thenReturn(history_1);
        mockMvc.perform(get("/api/history/" + id))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.id").value(history_1.getId()),
                        jsonPath("$.transferAuditId").value(history_1.getTransferAuditId()),
                        jsonPath("$.profileAuditId").value(history_1.getProfileAuditId())
                );
    }

    @Test
    @DisplayName("Чтение по не существующему id, негативный сценарий")
    public void readByNonExistIdNegativeTest() throws Exception {
        final String message = "история по указанному id не найдена";
        Mockito.when(service.readById(ArgumentMatchers.any())).thenThrow(new EntityNotFoundException(message));
        mockMvc.perform(get("/api/history/" + id))
                .andExpectAll(
                        status().is(404),
                        content().string(message)
                );
    }

    @Test
    @DisplayName("Чтение по списку id, позитивный сценарий")
    public void readAllByIdPositiveTest() throws Exception {
        Mockito.when(service.readAllById(List.of(1L, 2L, 3L))).thenReturn(histories);
        mockMvc.perform(get("/api/history").param("id", "1", "2", "3"))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$", Matchers.hasSize(3)),
                        jsonPath("$[0].id").value(history_1.getId()),
                        jsonPath("$[0].transferAuditId").value(history_1.getTransferAuditId()),
                        jsonPath("$[0].profileAuditId").value(history_1.getProfileAuditId())
                );
    }

    @Test
    @DisplayName("Чтение по нескольким не существующим id, негативный сценарий")
    public void readAllByNonExistIdNegativeTest() throws Exception {
        final String message = "истории по указанным id не найдены";
        Mockito.when(service.readAllById(ArgumentMatchers.any())).thenThrow(new EntityNotFoundException(message));
        mockMvc.perform(get("/api/history").param("id", "1", "2", "3"))
                .andExpectAll(
                        status().is(404),
                        content().string(message)
                );
    }

    @Test
    @DisplayName("Создание, позитивный сценарий")
    public void createPositiveTest() throws Exception {
        Mockito.when(service.create(ArgumentMatchers.any())).thenReturn(history_1);
        final String jsonHistory = mapper.writeValueAsString(history_1);
        mockMvc.perform(
                        post("/api/history")
                                .contentType(MediaType.APPLICATION_JSON)
                                .characterEncoding("utf-8").content(jsonHistory)
                )
                .andExpectAll(
                        status().isOk(),
                        content().json(jsonHistory)
                );
    }

    @Test
    @DisplayName("Создание null, негативный сценарий")
    public void createNullNegativeTest() throws Exception {
        Mockito.when(service.create(null)).thenReturn(null);
        final String jsonHistory = mapper.writeValueAsString(null);
        mockMvc.perform(
                        post("/api/history")
                                .contentType(MediaType.APPLICATION_JSON)
                                .characterEncoding("utf-8").content(jsonHistory)
                )
                .andExpect(status().is4xxClientError());
    }

    @Test
    @DisplayName("Обновление по id, позитивный сценарий")
    public void updatePositiveTest() throws Exception {
        Mockito.when(service.update(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(history_1);
        final String jsonHistory = mapper.writeValueAsString(history_1);
        mockMvc.perform(
                        put("/api/history/" + id)
                                .contentType(MediaType.APPLICATION_JSON)
                                .characterEncoding("utf-8").content(jsonHistory)
                )
                .andExpectAll(
                        status().isOk(),
                        content().json(jsonHistory)
                );
    }

    @Test
    @DisplayName("Обновление по id, негативный сценарий")
    public void updateByNonExistIdNegativeTest() throws Exception {
        final String message = "указанная история не найдена" + id;
        Mockito.when(service.update(ArgumentMatchers.any(), ArgumentMatchers.any()))
                .thenThrow(new EntityNotFoundException(message));
        final String jsonHistory = mapper.writeValueAsString(history_1);
        mockMvc.perform(
                        put("/api/history/" + id)
                                .contentType(MediaType.APPLICATION_JSON)
                                .characterEncoding("utf-8").content(jsonHistory))
                .andExpectAll(
                        status().is(404),
                        content().string(message)
                );
    }

}

package com.bank.account.controller;

import com.bank.account.dto.AccountDetailsDto;
import com.bank.account.service.AccountDetailsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AccountDetailsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private AccountDetailsService service;

    @InjectMocks
    private AccountDetailsController controller;

    private AccountDetailsDto accountDetailsDto;

    @BeforeEach
    public void setUp() {
        accountDetailsDto = new AccountDetailsDto();
        accountDetailsDto.setId(1L);
        accountDetailsDto.setPassportId(123456789L);
        accountDetailsDto.setAccountNumber(9876543210L);
        accountDetailsDto.setBankDetailsId(2L);
        accountDetailsDto.setMoney(BigDecimal.valueOf(1000));
        accountDetailsDto.setNegativeBalance(false);
        accountDetailsDto.setProfileId(3L);
    }

    @Test
    public void testCreateAccountDetails() throws Exception {
        when(service.save(any(AccountDetailsDto.class))).thenReturn(accountDetailsDto);

        mockMvc.perform(post("/details/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(accountDetailsDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    public void testReadAccountDetails() throws Exception {
        when(service.findById(anyLong())).thenReturn(accountDetailsDto);

        mockMvc.perform(get("/details/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    public void testUpdateAccountDetails() throws Exception {
        when(service.update(anyLong(), any(AccountDetailsDto.class))).thenReturn(accountDetailsDto);

        mockMvc.perform(put("/details/update/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(accountDetailsDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    public void testReadAllAccountDetails() throws Exception {
        List<AccountDetailsDto> accountDetailsList = Arrays.asList(accountDetailsDto);
        when(service.findAllById(any())).thenReturn(accountDetailsList);

        mockMvc.perform(get("/details/read/all")
                        .param("ids", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L));
    }
}
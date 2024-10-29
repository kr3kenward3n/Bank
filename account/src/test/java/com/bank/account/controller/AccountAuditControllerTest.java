package com.bank.account.controller;

import com.bank.account.dto.AuditDto;
import com.bank.account.service.AccountAuditService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Timestamp;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AccountAuditControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountAuditService service;

    @Test
    public void testReadAuditById() throws Exception {
        AuditDto auditDto = new AuditDto();
        auditDto.setId(1L);
        auditDto.setEntityType("TestEntity");
        auditDto.setOperationType("CREATE");
        auditDto.setCreatedBy("user1");
        auditDto.setModifiedBy("user1");
        auditDto.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        auditDto.setModifiedAt(new Timestamp(System.currentTimeMillis()));
        auditDto.setNewEntityJson("{}");
        auditDto.setEntityJson("{}");

        when(service.findById(anyLong())).thenReturn(auditDto);

        mockMvc.perform(get("/audit/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.entityType").value("TestEntity"))
                .andExpect(jsonPath("$.operationType").value("CREATE"))
                .andExpect(jsonPath("$.createdBy").value("user1"))
                .andExpect(jsonPath("$.modifiedBy").value("user1"));
    }
}
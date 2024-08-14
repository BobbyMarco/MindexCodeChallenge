package com.mindex.challenge;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.mindex.challenge.controller.CompensationController;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(CompensationController.class)
public class CompensationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CompensationService compensationService;

    @InjectMocks
    private CompensationController compensationController;

    private Compensation compensation;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        // Setup a mock Compensation object
        compensation = new Compensation();
        compensation.setEmployeeId("1");
        compensation.setSalary("50000");
        compensation.setEffectiveDate("2024-08-01");

        // Mocking the compensationService methods
        when(compensationService.createComp(compensation)).thenReturn(compensation);
        when(compensationService.readComp("1")).thenReturn(compensation);
    }

    @Test
    public void testCreateComp() throws Exception {
        mockMvc.perform(post("/compensation/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(compensation)))
                .andExpect(status().isOk());
    }

    @Test
    public void testReadComp() throws Exception {
        mockMvc.perform(get("/compensation/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.employeeId").value("1"))
                .andExpect(jsonPath("$.salary").value("50000"))
                .andExpect(jsonPath("$.effectiveDate").value("2024-08-01"));
    }
}
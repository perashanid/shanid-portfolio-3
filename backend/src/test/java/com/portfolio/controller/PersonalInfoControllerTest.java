package com.portfolio.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.portfolio.model.PersonalInfo;
import com.portfolio.service.PersonalInfoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PersonalInfoController.class)
class PersonalInfoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonalInfoService personalInfoService;

    @Autowired
    private ObjectMapper objectMapper;

    private PersonalInfo testPersonalInfo;

    @BeforeEach
    void setUp() {
        testPersonalInfo = new PersonalInfo();
        testPersonalInfo.setId(1L);
        testPersonalInfo.setName("Test User");
        testPersonalInfo.setTitle("Test Developer");
        testPersonalInfo.setBio("Test bio");
        testPersonalInfo.setEmail("test@example.com");
    }

    @Test
    void getPersonalInfo_ShouldReturnPersonalInfo_WhenExists() throws Exception {
        // Given
        when(personalInfoService.getPersonalInfo()).thenReturn(Optional.of(testPersonalInfo));

        // When & Then
        mockMvc.perform(get("/api/personal-info"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Test User"))
                .andExpect(jsonPath("$.email").value("test@example.com"));
    }

    @Test
    void getPersonalInfo_ShouldReturnNotFound_WhenNotExists() throws Exception {
        // Given
        when(personalInfoService.getPersonalInfo()).thenReturn(Optional.empty());

        // When & Then
        mockMvc.perform(get("/api/personal-info"))
                .andExpect(status().isNotFound());
    }

    @Test
    void savePersonalInfo_ShouldReturnSavedInfo_WhenValidInput() throws Exception {
        // Given
        when(personalInfoService.savePersonalInfo(any(PersonalInfo.class))).thenReturn(testPersonalInfo);

        // When & Then
        mockMvc.perform(put("/api/personal-info")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testPersonalInfo)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Test User"));
    }

    @Test
    void savePersonalInfo_ShouldReturnBadRequest_WhenInvalidInput() throws Exception {
        // Given
        PersonalInfo invalidInfo = new PersonalInfo();
        // Missing required fields

        // When & Then
        mockMvc.perform(put("/api/personal-info")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidInfo)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void checkEmailExists_ShouldReturnTrue_WhenEmailExists() throws Exception {
        // Given
        when(personalInfoService.existsByEmail("test@example.com")).thenReturn(true);

        // When & Then
        mockMvc.perform(get("/api/personal-info/check-email")
                .param("email", "test@example.com"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }
}
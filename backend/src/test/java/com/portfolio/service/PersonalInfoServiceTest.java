package com.portfolio.service;

import com.portfolio.model.PersonalInfo;
import com.portfolio.repository.PersonalInfoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonalInfoServiceTest {

    @Mock
    private PersonalInfoRepository personalInfoRepository;

    @InjectMocks
    private PersonalInfoService personalInfoService;

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
    void getPersonalInfo_ShouldReturnPersonalInfo_WhenExists() {
        // Given
        when(personalInfoRepository.findFirstPersonalInfo()).thenReturn(Optional.of(testPersonalInfo));

        // When
        Optional<PersonalInfo> result = personalInfoService.getPersonalInfo();

        // Then
        assertThat(result).isPresent();
        assertThat(result.get().getName()).isEqualTo("Test User");
        verify(personalInfoRepository).findFirstPersonalInfo();
    }

    @Test
    void getPersonalInfo_ShouldReturnEmpty_WhenNotExists() {
        // Given
        when(personalInfoRepository.findFirstPersonalInfo()).thenReturn(Optional.empty());

        // When
        Optional<PersonalInfo> result = personalInfoService.getPersonalInfo();

        // Then
        assertThat(result).isEmpty();
        verify(personalInfoRepository).findFirstPersonalInfo();
    }

    @Test
    void savePersonalInfo_ShouldUpdateExisting_WhenPersonalInfoExists() {
        // Given
        PersonalInfo existingInfo = new PersonalInfo();
        existingInfo.setId(1L);
        existingInfo.setName("Old Name");
        existingInfo.setEmail("old@example.com");

        PersonalInfo newInfo = new PersonalInfo();
        newInfo.setName("New Name");
        newInfo.setEmail("new@example.com");

        when(personalInfoRepository.findFirstPersonalInfo()).thenReturn(Optional.of(existingInfo));
        when(personalInfoRepository.save(any(PersonalInfo.class))).thenReturn(existingInfo);

        // When
        PersonalInfo result = personalInfoService.savePersonalInfo(newInfo);

        // Then
        assertThat(result.getName()).isEqualTo("New Name");
        assertThat(result.getEmail()).isEqualTo("new@example.com");
        verify(personalInfoRepository).findFirstPersonalInfo();
        verify(personalInfoRepository).save(existingInfo);
    }

    @Test
    void savePersonalInfo_ShouldCreateNew_WhenPersonalInfoNotExists() {
        // Given
        when(personalInfoRepository.findFirstPersonalInfo()).thenReturn(Optional.empty());
        when(personalInfoRepository.save(any(PersonalInfo.class))).thenReturn(testPersonalInfo);

        // When
        PersonalInfo result = personalInfoService.savePersonalInfo(testPersonalInfo);

        // Then
        assertThat(result).isEqualTo(testPersonalInfo);
        verify(personalInfoRepository).findFirstPersonalInfo();
        verify(personalInfoRepository).save(testPersonalInfo);
    }

    @Test
    void existsByEmail_ShouldReturnTrue_WhenEmailExists() {
        // Given
        when(personalInfoRepository.existsByEmail("test@example.com")).thenReturn(true);

        // When
        boolean result = personalInfoService.existsByEmail("test@example.com");

        // Then
        assertThat(result).isTrue();
        verify(personalInfoRepository).existsByEmail("test@example.com");
    }

    @Test
    void deletePersonalInfo_ShouldReturnTrue_WhenPersonalInfoExists() {
        // Given
        when(personalInfoRepository.existsById(1L)).thenReturn(true);

        // When
        boolean result = personalInfoService.deletePersonalInfo(1L);

        // Then
        assertThat(result).isTrue();
        verify(personalInfoRepository).existsById(1L);
        verify(personalInfoRepository).deleteById(1L);
    }

    @Test
    void deletePersonalInfo_ShouldReturnFalse_WhenPersonalInfoNotExists() {
        // Given
        when(personalInfoRepository.existsById(1L)).thenReturn(false);

        // When
        boolean result = personalInfoService.deletePersonalInfo(1L);

        // Then
        assertThat(result).isFalse();
        verify(personalInfoRepository).existsById(1L);
        verify(personalInfoRepository, never()).deleteById(1L);
    }
}
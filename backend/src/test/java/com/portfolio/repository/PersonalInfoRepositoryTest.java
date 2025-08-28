package com.portfolio.repository;

import com.portfolio.model.PersonalInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class PersonalInfoRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PersonalInfoRepository personalInfoRepository;

    private PersonalInfo testPersonalInfo;

    @BeforeEach
    void setUp() {
        testPersonalInfo = new PersonalInfo();
        testPersonalInfo.setName("Test User");
        testPersonalInfo.setTitle("Test Developer");
        testPersonalInfo.setBio("Test bio");
        testPersonalInfo.setEmail("test@example.com");
        testPersonalInfo.setPhone("+1-555-0123");
    }

    @Test
    void findByEmail_ShouldReturnPersonalInfo_WhenEmailExists() {
        // Given
        entityManager.persistAndFlush(testPersonalInfo);

        // When
        Optional<PersonalInfo> result = personalInfoRepository.findByEmail("test@example.com");

        // Then
        assertThat(result).isPresent();
        assertThat(result.get().getName()).isEqualTo("Test User");
        assertThat(result.get().getEmail()).isEqualTo("test@example.com");
    }

    @Test
    void findByEmail_ShouldReturnEmpty_WhenEmailDoesNotExist() {
        // When
        Optional<PersonalInfo> result = personalInfoRepository.findByEmail("nonexistent@example.com");

        // Then
        assertThat(result).isEmpty();
    }

    @Test
    void existsByEmail_ShouldReturnTrue_WhenEmailExists() {
        // Given
        entityManager.persistAndFlush(testPersonalInfo);

        // When
        boolean exists = personalInfoRepository.existsByEmail("test@example.com");

        // Then
        assertThat(exists).isTrue();
    }

    @Test
    void existsByEmail_ShouldReturnFalse_WhenEmailDoesNotExist() {
        // When
        boolean exists = personalInfoRepository.existsByEmail("nonexistent@example.com");

        // Then
        assertThat(exists).isFalse();
    }

    @Test
    void findFirstPersonalInfo_ShouldReturnFirstRecord_WhenRecordsExist() {
        // Given
        PersonalInfo firstInfo = new PersonalInfo("First User", "First Title", "First bio", "first@example.com");
        PersonalInfo secondInfo = new PersonalInfo("Second User", "Second Title", "Second bio", "second@example.com");
        
        entityManager.persistAndFlush(firstInfo);
        entityManager.persistAndFlush(secondInfo);

        // When
        Optional<PersonalInfo> result = personalInfoRepository.findFirstPersonalInfo();

        // Then
        assertThat(result).isPresent();
        assertThat(result.get().getName()).isEqualTo("First User");
    }

    @Test
    void findFirstPersonalInfo_ShouldReturnEmpty_WhenNoRecordsExist() {
        // When
        Optional<PersonalInfo> result = personalInfoRepository.findFirstPersonalInfo();

        // Then
        assertThat(result).isEmpty();
    }
}
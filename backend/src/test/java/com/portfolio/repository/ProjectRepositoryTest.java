package com.portfolio.repository;

import com.portfolio.model.Project;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ProjectRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProjectRepository projectRepository;

    private Project featuredProject;
    private Project regularProject;

    @BeforeEach
    void setUp() {
        featuredProject = new Project();
        featuredProject.setTitle("Featured Project");
        featuredProject.setDescription("A featured project");
        featuredProject.setCategory("Web Application");
        featuredProject.setFeatured(true);
        featuredProject.setTechnologies(Arrays.asList("Java", "Spring Boot", "React"));

        regularProject = new Project();
        regularProject.setTitle("Regular Project");
        regularProject.setDescription("A regular project");
        regularProject.setCategory("Mobile Application");
        regularProject.setFeatured(false);
        regularProject.setTechnologies(Arrays.asList("React Native", "Node.js"));
    }

    @Test
    void findByFeaturedTrueOrderByCreatedDateDesc_ShouldReturnFeaturedProjects() {
        // Given
        entityManager.persistAndFlush(featuredProject);
        entityManager.persistAndFlush(regularProject);

        // When
        List<Project> result = projectRepository.findByFeaturedTrueOrderByCreatedDateDesc();

        // Then
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getTitle()).isEqualTo("Featured Project");
        assertThat(result.get(0).getFeatured()).isTrue();
    }

    @Test
    void findByCategoryOrderByCreatedDateDesc_ShouldReturnProjectsByCategory() {
        // Given
        entityManager.persistAndFlush(featuredProject);
        entityManager.persistAndFlush(regularProject);

        // When
        List<Project> result = projectRepository.findByCategoryOrderByCreatedDateDesc("Web Application");

        // Then
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getTitle()).isEqualTo("Featured Project");
        assertThat(result.get(0).getCategory()).isEqualTo("Web Application");
    }

    @Test
    void findByTechnologyContainingIgnoreCase_ShouldReturnProjectsWithTechnology() {
        // Given
        entityManager.persistAndFlush(featuredProject);
        entityManager.persistAndFlush(regularProject);

        // When
        List<Project> result = projectRepository.findByTechnologyContainingIgnoreCase("react");

        // Then
        assertThat(result).hasSize(2); // Both projects contain React/React Native
    }

    @Test
    void findDistinctCategories_ShouldReturnAllCategories() {
        // Given
        entityManager.persistAndFlush(featuredProject);
        entityManager.persistAndFlush(regularProject);

        // When
        List<String> result = projectRepository.findDistinctCategories();

        // Then
        assertThat(result).hasSize(2);
        assertThat(result).contains("Web Application", "Mobile Application");
    }

    @Test
    void findDistinctTechnologies_ShouldReturnAllTechnologies() {
        // Given
        entityManager.persistAndFlush(featuredProject);
        entityManager.persistAndFlush(regularProject);

        // When
        List<String> result = projectRepository.findDistinctTechnologies();

        // Then
        assertThat(result).contains("Java", "Spring Boot", "React", "React Native", "Node.js");
    }

    @Test
    void countByFeaturedTrue_ShouldReturnFeaturedProjectCount() {
        // Given
        entityManager.persistAndFlush(featuredProject);
        entityManager.persistAndFlush(regularProject);

        // When
        long count = projectRepository.countByFeaturedTrue();

        // Then
        assertThat(count).isEqualTo(1);
    }

    @Test
    void countByCategory_ShouldReturnProjectCountByCategory() {
        // Given
        entityManager.persistAndFlush(featuredProject);
        entityManager.persistAndFlush(regularProject);

        // When
        long count = projectRepository.countByCategory("Web Application");

        // Then
        assertThat(count).isEqualTo(1);
    }
}
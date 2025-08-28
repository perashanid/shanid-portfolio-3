package com.portfolio.service;

import com.portfolio.model.Project;
import com.portfolio.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    /**
     * Get all projects ordered by creation date
     */
    @Transactional(readOnly = true)
    public List<Project> getAllProjects() {
        return projectRepository.findAllByOrderByCreatedDateDesc();
    }

    /**
     * Get project by ID
     */
    @Transactional(readOnly = true)
    public Optional<Project> getProjectById(Long id) {
        return projectRepository.findById(id);
    }

    /**
     * Get all featured projects
     */
    @Transactional(readOnly = true)
    public List<Project> getFeaturedProjects() {
        return projectRepository.findByFeaturedTrueOrderByCreatedDateDesc();
    }

    /**
     * Get projects by category
     */
    @Transactional(readOnly = true)
    public List<Project> getProjectsByCategory(String category) {
        return projectRepository.findByCategoryOrderByCreatedDateDesc(category);
    }

    /**
     * Get projects by technology
     */
    @Transactional(readOnly = true)
    public List<Project> getProjectsByTechnology(String technology) {
        return projectRepository.findByTechnologyContainingIgnoreCase(technology);
    }

    /**
     * Get projects by multiple technologies
     */
    @Transactional(readOnly = true)
    public List<Project> getProjectsByTechnologies(List<String> technologies) {
        // Convert to lowercase for case-insensitive search
        List<String> lowerCaseTechnologies = technologies.stream()
                .map(String::toLowerCase)
                .toList();
        return projectRepository.findByTechnologiesIn(lowerCaseTechnologies);
    }

    /**
     * Get projects by category and featured status
     */
    @Transactional(readOnly = true)
    public List<Project> getProjectsByCategoryAndFeatured(String category, Boolean featured) {
        return projectRepository.findByCategoryAndFeaturedOrderByCreatedDateDesc(category, featured);
    }

    /**
     * Get all distinct categories
     */
    @Transactional(readOnly = true)
    public List<String> getAllCategories() {
        return projectRepository.findDistinctCategories();
    }

    /**
     * Get all distinct technologies
     */
    @Transactional(readOnly = true)
    public List<String> getAllTechnologies() {
        return projectRepository.findDistinctTechnologies();
    }

    /**
     * Create new project
     */
    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    /**
     * Update existing project
     */
    public Optional<Project> updateProject(Long id, Project updatedProject) {
        return projectRepository.findById(id)
                .map(existing -> {
                    updateProjectFields(existing, updatedProject);
                    return projectRepository.save(existing);
                });
    }

    /**
     * Delete project by ID
     */
    public boolean deleteProject(Long id) {
        if (projectRepository.existsById(id)) {
            projectRepository.deleteById(id);
            return true;
        }
        return false;
    }

    /**
     * Toggle featured status of a project
     */
    public Optional<Project> toggleFeaturedStatus(Long id) {
        return projectRepository.findById(id)
                .map(project -> {
                    project.setFeatured(!project.getFeatured());
                    return projectRepository.save(project);
                });
    }

    /**
     * Get project count by category
     */
    @Transactional(readOnly = true)
    public long getProjectCountByCategory(String category) {
        return projectRepository.countByCategory(category);
    }

    /**
     * Get featured project count
     */
    @Transactional(readOnly = true)
    public long getFeaturedProjectCount() {
        return projectRepository.countByFeaturedTrue();
    }

    /**
     * Check if project exists by ID
     */
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return projectRepository.existsById(id);
    }

    /**
     * Helper method to update project fields
     */
    private void updateProjectFields(Project existing, Project updated) {
        if (updated.getTitle() != null) {
            existing.setTitle(updated.getTitle());
        }
        if (updated.getDescription() != null) {
            existing.setDescription(updated.getDescription());
        }
        if (updated.getDetailedDescription() != null) {
            existing.setDetailedDescription(updated.getDetailedDescription());
        }
        if (updated.getTechnologies() != null) {
            existing.setTechnologies(updated.getTechnologies());
        }
        if (updated.getImageUrls() != null) {
            existing.setImageUrls(updated.getImageUrls());
        }
        if (updated.getLiveUrl() != null) {
            existing.setLiveUrl(updated.getLiveUrl());
        }
        if (updated.getRepositoryUrl() != null) {
            existing.setRepositoryUrl(updated.getRepositoryUrl());
        }
        if (updated.getCategory() != null) {
            existing.setCategory(updated.getCategory());
        }
        if (updated.getFeatured() != null) {
            existing.setFeatured(updated.getFeatured());
        }
    }
}
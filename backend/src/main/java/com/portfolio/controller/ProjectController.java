package com.portfolio.controller;

import com.portfolio.model.Project;
import com.portfolio.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/projects")
@CrossOrigin(origins = "*")
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable String id) {
        Optional<Project> project = projectRepository.findById(id);
        return project.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/category/{category}")
    public List<Project> getProjectsByCategory(@PathVariable String category) {
        try {
            Project.ProjectCategory projectCategory = Project.ProjectCategory.valueOf(category.toUpperCase());
            return projectRepository.findByCategory(projectCategory);
        } catch (IllegalArgumentException e) {
            return List.of();
        }
    }

    @GetMapping("/featured")
    public List<Project> getFeaturedProjects() {
        return projectRepository.findByFeaturedTrue();
    }

    @GetMapping("/category/{category}/featured")
    public List<Project> getFeaturedProjectsByCategory(@PathVariable String category) {
        try {
            Project.ProjectCategory projectCategory = Project.ProjectCategory.valueOf(category.toUpperCase());
            return projectRepository.findByCategoryAndFeaturedTrue(projectCategory);
        } catch (IllegalArgumentException e) {
            return List.of();
        }
    }
}
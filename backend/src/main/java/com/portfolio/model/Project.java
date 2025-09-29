package com.portfolio.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "projects")
public class Project {
    @Id
    private String id;

    @NotBlank
    private String title;

    @NotBlank
    @Column(length = 1000)
    private String description;

    @ElementCollection
    @CollectionTable(name = "project_technologies", joinColumns = @JoinColumn(name = "project_id"))
    @Column(name = "technology")
    private List<String> technologies;

    private String liveUrl;
    private String githubUrl;

    @ElementCollection
    @CollectionTable(name = "project_images", joinColumns = @JoinColumn(name = "project_id"))
    @Column(name = "image_url")
    private List<String> images;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ProjectCategory category;

    private Boolean featured = false;

    // Constructors
    public Project() {}

    public Project(String id, String title, String description, List<String> technologies, 
                   String liveUrl, String githubUrl, List<String> images, 
                   ProjectCategory category, Boolean featured) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.technologies = technologies;
        this.liveUrl = liveUrl;
        this.githubUrl = githubUrl;
        this.images = images;
        this.category = category;
        this.featured = featured;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public List<String> getTechnologies() { return technologies; }
    public void setTechnologies(List<String> technologies) { this.technologies = technologies; }

    public String getLiveUrl() { return liveUrl; }
    public void setLiveUrl(String liveUrl) { this.liveUrl = liveUrl; }

    public String getGithubUrl() { return githubUrl; }
    public void setGithubUrl(String githubUrl) { this.githubUrl = githubUrl; }

    public List<String> getImages() { return images; }
    public void setImages(List<String> images) { this.images = images; }

    public ProjectCategory getCategory() { return category; }
    public void setCategory(ProjectCategory category) { this.category = category; }

    public Boolean getFeatured() { return featured; }
    public void setFeatured(Boolean featured) { this.featured = featured; }

    public enum ProjectCategory {
        WEB_APPLICATION,
        FRONTEND,
        BACKEND,
        DATA_SCIENCE,
        GAME_DEVELOPMENT,
        OTHERS
    }
}
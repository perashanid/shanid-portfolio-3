package com.portfolio.repository;

import com.portfolio.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, String> {
    List<Project> findByCategory(Project.ProjectCategory category);
    List<Project> findByFeaturedTrue();
    List<Project> findByCategoryAndFeaturedTrue(Project.ProjectCategory category);
}
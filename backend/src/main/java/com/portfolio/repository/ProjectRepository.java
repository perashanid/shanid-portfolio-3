package com.portfolio.repository;

import com.portfolio.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    
    /**
     * Find all featured projects
     */
    List<Project> findByFeaturedTrueOrderByCreatedDateDesc();
    
    /**
     * Find projects by category
     */
    List<Project> findByCategoryOrderByCreatedDateDesc(String category);
    
    /**
     * Find projects by category and featured status
     */
    List<Project> findByCategoryAndFeaturedOrderByCreatedDateDesc(String category, Boolean featured);
    
    /**
     * Find all projects ordered by creation date (newest first)
     */
    List<Project> findAllByOrderByCreatedDateDesc();
    
    /**
     * Find projects containing specific technology
     */
    @Query("SELECT DISTINCT p FROM Project p JOIN p.technologies t WHERE LOWER(t) LIKE LOWER(CONCAT('%', :technology, '%')) ORDER BY p.createdDate DESC")
    List<Project> findByTechnologyContainingIgnoreCase(@Param("technology") String technology);
    
    /**
     * Find projects by multiple technologies
     */
    @Query("SELECT DISTINCT p FROM Project p JOIN p.technologies t WHERE LOWER(t) IN :technologies ORDER BY p.createdDate DESC")
    List<Project> findByTechnologiesIn(@Param("technologies") List<String> technologies);
    
    /**
     * Get all distinct categories
     */
    @Query("SELECT DISTINCT p.category FROM Project p ORDER BY p.category")
    List<String> findDistinctCategories();
    
    /**
     * Get all distinct technologies
     */
    @Query("SELECT DISTINCT t FROM Project p JOIN p.technologies t ORDER BY t")
    List<String> findDistinctTechnologies();
    
    /**
     * Count projects by category
     */
    long countByCategory(String category);
    
    /**
     * Count featured projects
     */
    long countByFeaturedTrue();
}
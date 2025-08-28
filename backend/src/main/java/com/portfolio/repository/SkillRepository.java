package com.portfolio.repository;

import com.portfolio.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {
    
    /**
     * Find skills by category ordered by proficiency level (highest first)
     */
    List<Skill> findByCategoryOrderByProficiencyLevelDescNameAsc(String category);
    
    /**
     * Find all skills ordered by category and proficiency level
     */
    List<Skill> findAllByOrderByCategoryAscProficiencyLevelDescNameAsc();
    
    /**
     * Find skills with proficiency level greater than or equal to specified level
     */
    List<Skill> findByProficiencyLevelGreaterThanEqualOrderByProficiencyLevelDescNameAsc(Integer proficiencyLevel);
    
    /**
     * Find skills by years of experience range
     */
    List<Skill> findByYearsOfExperienceBetweenOrderByYearsOfExperienceDescNameAsc(Integer minYears, Integer maxYears);
    
    /**
     * Get all distinct categories
     */
    @Query("SELECT DISTINCT s.category FROM Skill s ORDER BY s.category")
    List<String> findDistinctCategories();
    
    /**
     * Find top skills by proficiency level
     */
    @Query("SELECT s FROM Skill s WHERE s.proficiencyLevel = :level ORDER BY s.yearsOfExperience DESC, s.name ASC")
    List<Skill> findTopSkillsByProficiencyLevel(@Param("level") Integer level);
    
    /**
     * Count skills by category
     */
    long countByCategory(String category);
    
    /**
     * Find skills by name containing (case insensitive)
     */
    List<Skill> findByNameContainingIgnoreCaseOrderByProficiencyLevelDescNameAsc(String name);
    
    /**
     * Check if skill exists by name and category
     */
    boolean existsByNameAndCategory(String name, String category);
    
    /**
     * Get average proficiency level by category
     */
    @Query("SELECT AVG(s.proficiencyLevel) FROM Skill s WHERE s.category = :category")
    Double getAverageProficiencyByCategory(@Param("category") String category);
}
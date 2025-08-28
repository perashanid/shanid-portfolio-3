package com.portfolio.service;

import com.portfolio.model.Skill;
import com.portfolio.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class SkillService {

    private final SkillRepository skillRepository;

    @Autowired
    public SkillService(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    /**
     * Get all skills ordered by category and proficiency
     */
    @Transactional(readOnly = true)
    public List<Skill> getAllSkills() {
        return skillRepository.findAllByOrderByCategoryAscProficiencyLevelDescNameAsc();
    }

    /**
     * Get skill by ID
     */
    @Transactional(readOnly = true)
    public Optional<Skill> getSkillById(Long id) {
        return skillRepository.findById(id);
    }

    /**
     * Get skills by category
     */
    @Transactional(readOnly = true)
    public List<Skill> getSkillsByCategory(String category) {
        return skillRepository.findByCategoryOrderByProficiencyLevelDescNameAsc(category);
    }

    /**
     * Get skills grouped by category
     */
    @Transactional(readOnly = true)
    public Map<String, List<Skill>> getSkillsGroupedByCategory() {
        List<Skill> allSkills = getAllSkills();
        return allSkills.stream()
                .collect(Collectors.groupingBy(Skill::getCategory));
    }

    /**
     * Get skills with minimum proficiency level
     */
    @Transactional(readOnly = true)
    public List<Skill> getSkillsByMinimumProficiency(Integer minProficiency) {
        return skillRepository.findByProficiencyLevelGreaterThanEqualOrderByProficiencyLevelDescNameAsc(minProficiency);
    }

    /**
     * Get skills by experience range
     */
    @Transactional(readOnly = true)
    public List<Skill> getSkillsByExperienceRange(Integer minYears, Integer maxYears) {
        return skillRepository.findByYearsOfExperienceBetweenOrderByYearsOfExperienceDescNameAsc(minYears, maxYears);
    }

    /**
     * Get top skills by proficiency level
     */
    @Transactional(readOnly = true)
    public List<Skill> getTopSkillsByProficiency(Integer proficiencyLevel) {
        return skillRepository.findTopSkillsByProficiencyLevel(proficiencyLevel);
    }

    /**
     * Search skills by name
     */
    @Transactional(readOnly = true)
    public List<Skill> searchSkillsByName(String name) {
        return skillRepository.findByNameContainingIgnoreCaseOrderByProficiencyLevelDescNameAsc(name);
    }

    /**
     * Get all distinct categories
     */
    @Transactional(readOnly = true)
    public List<String> getAllCategories() {
        return skillRepository.findDistinctCategories();
    }

    /**
     * Create new skill
     */
    public Skill createSkill(Skill skill) {
        return skillRepository.save(skill);
    }

    /**
     * Update existing skill
     */
    public Optional<Skill> updateSkill(Long id, Skill updatedSkill) {
        return skillRepository.findById(id)
                .map(existing -> {
                    updateSkillFields(existing, updatedSkill);
                    return skillRepository.save(existing);
                });
    }

    /**
     * Delete skill by ID
     */
    public boolean deleteSkill(Long id) {
        if (skillRepository.existsById(id)) {
            skillRepository.deleteById(id);
            return true;
        }
        return false;
    }

    /**
     * Check if skill exists by name and category
     */
    @Transactional(readOnly = true)
    public boolean existsByNameAndCategory(String name, String category) {
        return skillRepository.existsByNameAndCategory(name, category);
    }

    /**
     * Get skill count by category
     */
    @Transactional(readOnly = true)
    public long getSkillCountByCategory(String category) {
        return skillRepository.countByCategory(category);
    }

    /**
     * Get average proficiency by category
     */
    @Transactional(readOnly = true)
    public Double getAverageProficiencyByCategory(String category) {
        return skillRepository.getAverageProficiencyByCategory(category);
    }

    /**
     * Get category statistics
     */
    @Transactional(readOnly = true)
    public Map<String, CategoryStats> getCategoryStatistics() {
        List<String> categories = getAllCategories();
        return categories.stream()
                .collect(Collectors.toMap(
                        category -> category,
                        category -> new CategoryStats(
                                getSkillCountByCategory(category),
                                getAverageProficiencyByCategory(category)
                        )
                ));
    }

    /**
     * Check if skill exists by ID
     */
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return skillRepository.existsById(id);
    }

    /**
     * Helper method to update skill fields
     */
    private void updateSkillFields(Skill existing, Skill updated) {
        if (updated.getName() != null) {
            existing.setName(updated.getName());
        }
        if (updated.getCategory() != null) {
            existing.setCategory(updated.getCategory());
        }
        if (updated.getProficiencyLevel() != null) {
            existing.setProficiencyLevel(updated.getProficiencyLevel());
        }
        if (updated.getYearsOfExperience() != null) {
            existing.setYearsOfExperience(updated.getYearsOfExperience());
        }
        if (updated.getIconUrl() != null) {
            existing.setIconUrl(updated.getIconUrl());
        }
    }

    /**
     * Inner class for category statistics
     */
    public static class CategoryStats {
        private final long skillCount;
        private final Double averageProficiency;

        public CategoryStats(long skillCount, Double averageProficiency) {
            this.skillCount = skillCount;
            this.averageProficiency = averageProficiency;
        }

        public long getSkillCount() {
            return skillCount;
        }

        public Double getAverageProficiency() {
            return averageProficiency;
        }
    }
}
package com.portfolio.service;

import com.portfolio.model.PersonalInfo;
import com.portfolio.repository.PersonalInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class PersonalInfoService {

    private final PersonalInfoRepository personalInfoRepository;

    @Autowired
    public PersonalInfoService(PersonalInfoRepository personalInfoRepository) {
        this.personalInfoRepository = personalInfoRepository;
    }

    /**
     * Get personal information (should be only one record)
     */
    @Transactional(readOnly = true)
    public Optional<PersonalInfo> getPersonalInfo() {
        return personalInfoRepository.findFirstPersonalInfo();
    }

    /**
     * Get personal information by ID
     */
    @Transactional(readOnly = true)
    public Optional<PersonalInfo> getPersonalInfoById(Long id) {
        return personalInfoRepository.findById(id);
    }

    /**
     * Create or update personal information
     * If personal info already exists, update it; otherwise create new
     */
    public PersonalInfo savePersonalInfo(PersonalInfo personalInfo) {
        // Check if personal info already exists
        Optional<PersonalInfo> existingInfo = personalInfoRepository.findFirstPersonalInfo();
        
        if (existingInfo.isPresent()) {
            // Update existing record
            PersonalInfo existing = existingInfo.get();
            updatePersonalInfoFields(existing, personalInfo);
            return personalInfoRepository.save(existing);
        } else {
            // Create new record
            return personalInfoRepository.save(personalInfo);
        }
    }

    /**
     * Update personal information by ID
     */
    public Optional<PersonalInfo> updatePersonalInfo(Long id, PersonalInfo updatedInfo) {
        return personalInfoRepository.findById(id)
                .map(existing -> {
                    updatePersonalInfoFields(existing, updatedInfo);
                    return personalInfoRepository.save(existing);
                });
    }

    /**
     * Check if personal info exists by email
     */
    @Transactional(readOnly = true)
    public boolean existsByEmail(String email) {
        return personalInfoRepository.existsByEmail(email);
    }

    /**
     * Find personal info by email
     */
    @Transactional(readOnly = true)
    public Optional<PersonalInfo> findByEmail(String email) {
        return personalInfoRepository.findByEmail(email);
    }

    /**
     * Delete personal information by ID
     */
    public boolean deletePersonalInfo(Long id) {
        if (personalInfoRepository.existsById(id)) {
            personalInfoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    /**
     * Helper method to update personal info fields
     */
    private void updatePersonalInfoFields(PersonalInfo existing, PersonalInfo updated) {
        if (updated.getName() != null) {
            existing.setName(updated.getName());
        }
        if (updated.getTitle() != null) {
            existing.setTitle(updated.getTitle());
        }
        if (updated.getBio() != null) {
            existing.setBio(updated.getBio());
        }
        if (updated.getDetailedBio() != null) {
            existing.setDetailedBio(updated.getDetailedBio());
        }
        if (updated.getEmail() != null) {
            existing.setEmail(updated.getEmail());
        }
        if (updated.getPhone() != null) {
            existing.setPhone(updated.getPhone());
        }
        if (updated.getLinkedinUrl() != null) {
            existing.setLinkedinUrl(updated.getLinkedinUrl());
        }
        if (updated.getGithubUrl() != null) {
            existing.setGithubUrl(updated.getGithubUrl());
        }
        if (updated.getResumeUrl() != null) {
            existing.setResumeUrl(updated.getResumeUrl());
        }
        if (updated.getProfileImageUrl() != null) {
            existing.setProfileImageUrl(updated.getProfileImageUrl());
        }
    }
}
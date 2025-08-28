package com.portfolio.repository;

import com.portfolio.model.PersonalInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonalInfoRepository extends JpaRepository<PersonalInfo, Long> {
    
    /**
     * Find personal info by email address
     */
    Optional<PersonalInfo> findByEmail(String email);
    
    /**
     * Check if personal info exists by email
     */
    boolean existsByEmail(String email);
    
    /**
     * Get the first (and should be only) personal info record
     */
    @Query("SELECT p FROM PersonalInfo p ORDER BY p.id ASC LIMIT 1")
    Optional<PersonalInfo> findFirstPersonalInfo();
}
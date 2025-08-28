package com.portfolio.repository;

import com.portfolio.model.ContactMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ContactMessageRepository extends JpaRepository<ContactMessage, Long> {
    
    /**
     * Find all messages ordered by submission date (newest first)
     */
    List<ContactMessage> findAllByOrderBySubmittedAtDesc();
    
    /**
     * Find unread messages ordered by submission date
     */
    List<ContactMessage> findByIsReadFalseOrderBySubmittedAtDesc();
    
    /**
     * Find read messages ordered by submission date
     */
    List<ContactMessage> findByIsReadTrueOrderBySubmittedAtDesc();
    
    /**
     * Find messages by email address
     */
    List<ContactMessage> findByEmailOrderBySubmittedAtDesc(String email);
    
    /**
     * Find messages submitted after a specific date
     */
    List<ContactMessage> findBySubmittedAtAfterOrderBySubmittedAtDesc(LocalDateTime date);
    
    /**
     * Find messages submitted between dates
     */
    List<ContactMessage> findBySubmittedAtBetweenOrderBySubmittedAtDesc(LocalDateTime startDate, LocalDateTime endDate);
    
    /**
     * Count unread messages
     */
    long countByIsReadFalse();
    
    /**
     * Count messages by email
     */
    long countByEmail(String email);
    
    /**
     * Mark message as read
     */
    @Modifying
    @Query("UPDATE ContactMessage c SET c.isRead = true WHERE c.id = :id")
    void markAsRead(@Param("id") Long id);
    
    /**
     * Mark message as unread
     */
    @Modifying
    @Query("UPDATE ContactMessage c SET c.isRead = false WHERE c.id = :id")
    void markAsUnread(@Param("id") Long id);
    
    /**
     * Mark all messages as read
     */
    @Modifying
    @Query("UPDATE ContactMessage c SET c.isRead = true WHERE c.isRead = false")
    void markAllAsRead();
    
    /**
     * Find messages containing text in subject or message (case insensitive)
     */
    @Query("SELECT c FROM ContactMessage c WHERE LOWER(c.subject) LIKE LOWER(CONCAT('%', :searchText, '%')) OR LOWER(c.message) LIKE LOWER(CONCAT('%', :searchText, '%')) ORDER BY c.submittedAt DESC")
    List<ContactMessage> findBySubjectOrMessageContainingIgnoreCase(@Param("searchText") String searchText);
    
    /**
     * Delete messages older than specified date
     */
    @Modifying
    @Query("DELETE FROM ContactMessage c WHERE c.submittedAt < :date")
    void deleteMessagesOlderThan(@Param("date") LocalDateTime date);
}
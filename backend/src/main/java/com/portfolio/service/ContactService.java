package com.portfolio.service;

import com.portfolio.model.ContactMessage;
import com.portfolio.repository.ContactMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ContactService {

    private final ContactMessageRepository contactMessageRepository;
    private final EmailService emailService;

    @Value("${portfolio.contact.recipient-email:john.doe@example.com}")
    private String recipientEmail;

    @Value("${portfolio.contact.send-auto-reply:true}")
    private boolean sendAutoReply;

    @Value("${portfolio.contact.rate-limit.max-messages-per-email:5}")
    private int maxMessagesPerEmail;

    @Value("${portfolio.contact.rate-limit.time-window-hours:24}")
    private int timeWindowHours;

    @Autowired
    public ContactService(ContactMessageRepository contactMessageRepository, EmailService emailService) {
        this.contactMessageRepository = contactMessageRepository;
        this.emailService = emailService;
    }

    /**
     * Submit contact form and send notifications
     */
    public ContactMessage submitContactForm(ContactMessage contactMessage) {
        // Check rate limiting
        if (isRateLimited(contactMessage.getEmail())) {
            throw new RuntimeException("Too many messages sent from this email address. Please try again later.");
        }

        // Save the contact message
        ContactMessage savedMessage = contactMessageRepository.save(contactMessage);

        // Send email notifications if email service is configured
        if (emailService.isEmailConfigured()) {
            try {
                // Send notification to portfolio owner
                emailService.sendContactFormNotification(
                    contactMessage.getName(),
                    contactMessage.getEmail(),
                    contactMessage.getSubject(),
                    contactMessage.getMessage(),
                    recipientEmail
                );

                // Send auto-reply to sender if enabled
                if (sendAutoReply) {
                    emailService.sendContactFormAutoReply(
                        contactMessage.getEmail(),
                        contactMessage.getName()
                    );
                }
            } catch (Exception e) {
                // Log error but don't fail the contact form submission
                System.err.println("Failed to send email notifications: " + e.getMessage());
            }
        }

        return savedMessage;
    }

    /**
     * Get all contact messages
     */
    @Transactional(readOnly = true)
    public List<ContactMessage> getAllMessages() {
        return contactMessageRepository.findAllByOrderBySubmittedAtDesc();
    }

    /**
     * Get contact message by ID
     */
    @Transactional(readOnly = true)
    public Optional<ContactMessage> getMessageById(Long id) {
        return contactMessageRepository.findById(id);
    }

    /**
     * Get unread messages
     */
    @Transactional(readOnly = true)
    public List<ContactMessage> getUnreadMessages() {
        return contactMessageRepository.findByIsReadFalseOrderBySubmittedAtDesc();
    }

    /**
     * Get read messages
     */
    @Transactional(readOnly = true)
    public List<ContactMessage> getReadMessages() {
        return contactMessageRepository.findByIsReadTrueOrderBySubmittedAtDesc();
    }

    /**
     * Get messages by email address
     */
    @Transactional(readOnly = true)
    public List<ContactMessage> getMessagesByEmail(String email) {
        return contactMessageRepository.findByEmailOrderBySubmittedAtDesc(email);
    }

    /**
     * Get messages submitted after a specific date
     */
    @Transactional(readOnly = true)
    public List<ContactMessage> getMessagesAfterDate(LocalDateTime date) {
        return contactMessageRepository.findBySubmittedAtAfterOrderBySubmittedAtDesc(date);
    }

    /**
     * Get messages submitted between dates
     */
    @Transactional(readOnly = true)
    public List<ContactMessage> getMessagesBetweenDates(LocalDateTime startDate, LocalDateTime endDate) {
        return contactMessageRepository.findBySubmittedAtBetweenOrderBySubmittedAtDesc(startDate, endDate);
    }

    /**
     * Search messages by content
     */
    @Transactional(readOnly = true)
    public List<ContactMessage> searchMessages(String searchText) {
        return contactMessageRepository.findBySubjectOrMessageContainingIgnoreCase(searchText);
    }

    /**
     * Mark message as read
     */
    public boolean markAsRead(Long id) {
        if (contactMessageRepository.existsById(id)) {
            contactMessageRepository.markAsRead(id);
            return true;
        }
        return false;
    }

    /**
     * Mark message as unread
     */
    public boolean markAsUnread(Long id) {
        if (contactMessageRepository.existsById(id)) {
            contactMessageRepository.markAsUnread(id);
            return true;
        }
        return false;
    }

    /**
     * Mark all messages as read
     */
    public void markAllAsRead() {
        contactMessageRepository.markAllAsRead();
    }

    /**
     * Delete message by ID
     */
    public boolean deleteMessage(Long id) {
        if (contactMessageRepository.existsById(id)) {
            contactMessageRepository.deleteById(id);
            return true;
        }
        return false;
    }

    /**
     * Get unread message count
     */
    @Transactional(readOnly = true)
    public long getUnreadMessageCount() {
        return contactMessageRepository.countByIsReadFalse();
    }

    /**
     * Get message count by email
     */
    @Transactional(readOnly = true)
    public long getMessageCountByEmail(String email) {
        return contactMessageRepository.countByEmail(email);
    }

    /**
     * Delete old messages (cleanup)
     */
    public void deleteMessagesOlderThan(LocalDateTime date) {
        contactMessageRepository.deleteMessagesOlderThan(date);
    }

    /**
     * Check if email is rate limited
     */
    private boolean isRateLimited(String email) {
        LocalDateTime timeWindow = LocalDateTime.now().minusHours(timeWindowHours);
        List<ContactMessage> recentMessages = contactMessageRepository
                .findByEmailOrderBySubmittedAtDesc(email)
                .stream()
                .filter(message -> message.getSubmittedAt().isAfter(timeWindow))
                .toList();
        
        return recentMessages.size() >= maxMessagesPerEmail;
    }

    /**
     * Check if message exists by ID
     */
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return contactMessageRepository.existsById(id);
    }
}
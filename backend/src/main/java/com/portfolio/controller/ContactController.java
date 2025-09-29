package com.portfolio.controller;

import com.portfolio.model.ContactMessage;
import com.portfolio.repository.ContactMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/contact")
@CrossOrigin(origins = "*")
public class ContactController {

    @Autowired
    private ContactMessageRepository contactMessageRepository;

    @PostMapping("/message")
    public ResponseEntity<String> sendMessage(@Valid @RequestBody ContactMessage contactMessage) {
        try {
            contactMessageRepository.save(contactMessage);
            return ResponseEntity.ok("Message sent successfully!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to send message: " + e.getMessage());
        }
    }

    @GetMapping("/messages")
    public List<ContactMessage> getAllMessages() {
        return contactMessageRepository.findAllByOrderByCreatedAtDesc();
    }

    @GetMapping("/messages/unread")
    public List<ContactMessage> getUnreadMessages() {
        return contactMessageRepository.findByIsReadFalse();
    }

    @PutMapping("/messages/{id}/read")
    public ResponseEntity<String> markAsRead(@PathVariable Long id) {
        return contactMessageRepository.findById(id)
                .map(message -> {
                    message.setIsRead(true);
                    contactMessageRepository.save(message);
                    return ResponseEntity.ok("Message marked as read");
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
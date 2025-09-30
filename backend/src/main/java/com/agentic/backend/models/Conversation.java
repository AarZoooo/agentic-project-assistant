package com.agentic.backend.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection = "conversations")
public class Conversation {
    @Id
    private String id;
    private String userId;
    private String title;
    private List<Message> messages;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

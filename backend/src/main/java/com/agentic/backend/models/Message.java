package com.agentic.backend.models;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Message {
    private senderType sender;
    private String message;
    private LocalDateTime timestamp;

    private enum senderType {
        USER, ASSISTANT
    }
}


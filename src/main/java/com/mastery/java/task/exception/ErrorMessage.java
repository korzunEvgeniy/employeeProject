package com.mastery.java.task.exception;

import java.time.LocalDateTime;

public class ErrorMessage {
    private final LocalDateTime timestamp;
    private final String message;
    private final String description;

    public ErrorMessage(LocalDateTime timestamp, String message, String description) {
        this.timestamp = timestamp;
        this.message = message;
        this.description = description;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }
}

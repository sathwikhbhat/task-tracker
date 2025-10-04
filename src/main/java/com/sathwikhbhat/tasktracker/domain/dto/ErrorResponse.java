package com.sathwikhbhat.tasktracker.domain.dto;

public record ErrorResponse(
        int status,
        String message,
        String details
) {
}
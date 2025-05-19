package com.eventhub.dto.event;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EventRequest {
    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Date is required")
    @Future(message = "Event date must be in the future")
    private LocalDateTime date;

    @NotBlank(message = "Location is required")
    private String location;

    @NotNull(message = "Maximum participants is required")
    @Min(value = 1, message = "Maximum participants must be at least 1")
    private Integer maxParticipants;

    @NotBlank(message = "Event type is required")
    private String eventType;

    private String imageUrl;
} 
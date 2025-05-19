package com.eventhub.dto.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventResponse {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime date;
    private String location;
    private Integer maxParticipants;
    private Integer currentParticipants;
    private String eventType;
    private String imageUrl;
    private String organizerName;
    private boolean isActive;
    private LocalDateTime createdAt;
} 
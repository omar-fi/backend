package com.eventhub.repository;

import com.eventhub.model.Event;
import com.eventhub.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    Page<Event> findByIsActiveTrue(Pageable pageable);
    List<Event> findByOrganizer(User organizer);
    List<Event> findByDateAfterAndIsActiveTrue(LocalDateTime date);
    
    @Query("SELECT e FROM Event e WHERE e.isActive = true AND " +
           "LOWER(e.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(e.description) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(e.location) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<Event> searchEvents(String keyword, Pageable pageable);
    
    List<Event> findByEventTypeAndIsActiveTrue(String eventType);
} 
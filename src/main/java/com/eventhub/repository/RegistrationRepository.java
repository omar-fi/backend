package com.eventhub.repository;

import com.eventhub.model.Event;
import com.eventhub.model.Registration;
import com.eventhub.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    List<Registration> findByUser(User user);
    List<Registration> findByEvent(Event event);
    Optional<Registration> findByUserAndEvent(User user, Event event);
    
    @Query("SELECT COUNT(r) FROM Registration r WHERE r.event = :event AND r.isConfirmed = true")
    long countConfirmedRegistrations(Event event);
    
    boolean existsByUserAndEvent(User user, Event event);
} 
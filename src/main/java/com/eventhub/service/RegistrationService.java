package com.eventhub.service;

import com.eventhub.model.Event;
import com.eventhub.model.Registration;
import com.eventhub.model.User;
import com.eventhub.repository.EventRepository;
import com.eventhub.repository.RegistrationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final RegistrationRepository registrationRepository;
    private final EventRepository eventRepository;

    @Transactional
    public void registerForEvent(Long eventId, User user) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        if (!event.isActive()) {
            throw new RuntimeException("Event is not active");
        }

        if (registrationRepository.existsByUserAndEvent(user, event)) {
            throw new RuntimeException("User is already registered for this event");
        }

        long currentParticipants = registrationRepository.countConfirmedRegistrations(event);
        if (currentParticipants >= event.getMaxParticipants()) {
            throw new RuntimeException("Event is full");
        }

        Registration registration = new Registration();
        registration.setUser(user);
        registration.setEvent(event);
        registration.setConfirmed(true);

        registrationRepository.save(registration);
    }

    @Transactional
    public void unregisterFromEvent(Long eventId, User user) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        Registration registration = registrationRepository.findByUserAndEvent(user, event)
                .orElseThrow(() -> new RuntimeException("Registration not found"));

        registrationRepository.delete(registration);
    }

    public List<Registration> getUserRegistrations(User user) {
        return registrationRepository.findByUser(user);
    }

    public List<Registration> getEventRegistrations(Event event) {
        return registrationRepository.findByEvent(event);
    }

    public boolean isUserRegistered(Long eventId, User user) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));
        return registrationRepository.existsByUserAndEvent(user, event);
    }
} 
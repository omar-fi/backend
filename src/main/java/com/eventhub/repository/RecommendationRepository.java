package com.eventhub.repository;

import com.eventhub.model.Recommendation;
import com.eventhub.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {
    List<Recommendation> findByUserOrderByScoreDesc(User user);
    
    @Query("SELECT r FROM Recommendation r WHERE r.user = :user AND r.createdAt >= :date ORDER BY r.score DESC")
    List<Recommendation> findRecentRecommendations(User user, java.time.LocalDateTime date);
    
    void deleteByUser(User user);
} 
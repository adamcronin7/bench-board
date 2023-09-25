package com.example.benchboard.repository;

import com.example.benchboard.entity.Leaderboard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LeaderboardRepository extends JpaRepository<Leaderboard, Long> {
    Optional<Leaderboard> findByUsername(String username);
}

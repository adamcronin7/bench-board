package com.example.benchboard.service;

import com.example.benchboard.entity.Leaderboard;
import com.example.benchboard.repository.LeaderboardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class LeaderboardService {
    private final LeaderboardRepository leaderboardRepository;

    @Autowired
    public LeaderboardService(LeaderboardRepository leaderboardRepository){
        this.leaderboardRepository = leaderboardRepository;
    }

    public List<Leaderboard> getAllLeaderboardEntries(){
        return leaderboardRepository.findAll();
    }

    public void addOrUpdateEntry(Leaderboard entry, String currentUsername) {
        Optional<Leaderboard> existingEntry = leaderboardRepository.findByUsername(currentUsername);
        if (existingEntry.isPresent()) {
            Leaderboard updatedEntry = existingEntry.get();
            updatedEntry.setSquat(entry.getSquat());
            updatedEntry.setBench(entry.getBench());
            updatedEntry.setDeadlift(entry.getDeadlift());
            leaderboardRepository.save(updatedEntry);
        } else {
            entry.setUsername(currentUsername);
            leaderboardRepository.save(entry);
        }
    }

    public Leaderboard getLeaderboardEntryByUsername(String username) {
        return leaderboardRepository.findByUsername(username).orElse(null);
    }
}

package com.example.benchboard.controller;

import com.example.benchboard.entity.Leaderboard;
import com.example.benchboard.service.LeaderboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class LeaderboardController {
    private final LeaderboardService leaderboardService;

    @Autowired
    public LeaderboardController(LeaderboardService leaderboardService){
        this.leaderboardService = leaderboardService;
    }

    @GetMapping("/leaderboard")
    public String showLeaderboard(Model model, Authentication authentication){
        //List<Leaderboard> leaderboardEntries = leaderboardService.getAllLeaderboardEntries();
        model.addAttribute("leaderboardEntries", leaderboardService.getAllLeaderboardEntries());
        model.addAttribute("newEntry", new Leaderboard());

        if (authentication != null) {
            String currentUsername = authentication.getName();
            model.addAttribute("currentUsername", currentUsername);
            model.addAttribute("leaderboard", leaderboardService.getLeaderboardEntryByUsername(currentUsername));
        }
        return "leaderboard";
    }

    @PostMapping("/leaderboard")
    public String addOrUpdateEntry(@Validated @ModelAttribute("newEntry") Leaderboard newEntry, BindingResult bindingResult, Authentication authentication) {
        if (bindingResult.hasErrors()) {
            return ("redirect:/leaderboard?error");
        }
        String currentUsername = authentication.getName();
        Leaderboard existingEntry = leaderboardService.getLeaderboardEntryByUsername(currentUsername);
        if (existingEntry != null) {
            existingEntry.setSquat(newEntry.getSquat());
            existingEntry.setBench(newEntry.getBench());
            existingEntry.setDeadlift(newEntry.getDeadlift());
            leaderboardService.addOrUpdateEntry(existingEntry, currentUsername);
        } else {
            leaderboardService.addOrUpdateEntry(newEntry, currentUsername);
        }

        return "redirect:/leaderboard";
    }

}

package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
public class StreakService {
    @Autowired
    private StreakRepository streakRepository;

    public Streak updateStreak(String userId) {
        Streak streak = streakRepository.findByUserId(userId)
                .orElseGet(() -> {
                    Streak s = new Streak();
                    s.setUserId(userId);
                    s.setCurrentStreak(0);
                    s.setWeeklyStreak(0);
                    s.setLastVisit(LocalDate.now().minusDays(1));
                    return s;
                });

        LocalDate today = LocalDate.now();
        LocalDate lastVisit = streak.getLastVisit();
        long diff = ChronoUnit.DAYS.between(lastVisit, today);

        if (diff == 1) {
            streak.setCurrentStreak(streak.getCurrentStreak() + 1);
            streak.setWeeklyStreak(streak.getWeeklyStreak() + 1);
        } else if (diff > 1) {
            streak.setCurrentStreak(1);
            streak.setWeeklyStreak(1);
        }

        if (today.getDayOfWeek() == DayOfWeek.MONDAY) {
            streak.setWeeklyStreak(1);
        }

        streak.setLastVisit(today);
        return streakRepository.save(streak);
    }

    public Optional<Streak> getStreak(String userId) {
        return streakRepository.findByUserId(userId);
    }
}

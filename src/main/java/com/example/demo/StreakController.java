package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/streak")
@CrossOrigin(origins = "*")
public class StreakController {
    @Autowired
    private StreakService streakService;

    @GetMapping("/{userId}")
    public ResponseEntity<Streak> getStreak(@PathVariable String userId) {
        return streakService.getStreak(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Streak> updateStreak(@PathVariable String userId) {
        Streak updated = streakService.updateStreak(userId);
        return ResponseEntity.ok(updated);
    }
}


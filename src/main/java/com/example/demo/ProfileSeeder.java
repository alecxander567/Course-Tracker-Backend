package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProfileSeeder {
    @Bean
    CommandLineRunner run(ProfileRepository repo) {
        return args -> {
            if (!repo.existsById(1L)) {
                Profile profile = new Profile();
                profile.setName("Alec Xander Espaldon");
                profile.setCourse("BS Information Technology - 2nd Year");
                profile.setAddress("San Carlos City, Negros Occidental");
                profile.setSchool("Colegio de Sta. Rita de San Carlos, Inc.");
                profile.setBio("Passionate IT student who enjoys building apps, exploring new technologies, and aiming to become a front-end developer.");
                repo.save(profile);
                System.out.println("✅ Default profile created.");
            }
        };
    }
}

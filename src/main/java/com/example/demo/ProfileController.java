package com.example.demo;

import com.example.demo.Profile;
import com.example.demo.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Optional;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    @Autowired
    private ProfileRepository profileRepository;

    @GetMapping("/{id}")
    public Optional<Profile> getProfile(@PathVariable Long id) {
        return profileRepository.findById(id);
    }

    @PutMapping("/{id}")
    public Profile updateProfile(@PathVariable Long id, @RequestBody Profile updatedProfile) {
        return profileRepository.findById(id).map(profile -> {
            profile.setName(updatedProfile.getName());
            profile.setCourse(updatedProfile.getCourse());
            profile.setAddress(updatedProfile.getAddress());
            profile.setSchool(updatedProfile.getSchool());
            profile.setBio(updatedProfile.getBio());
            return profileRepository.save(profile);
        }).orElseThrow(() -> new RuntimeException("Profile not found"));
    }

    @PostMapping
    public Profile createProfile(@RequestBody Profile profile) {
        return profileRepository.save(profile);
    }

    @PutMapping("/upload/{id}")
    public Profile updateProfileWithImage(
            @PathVariable Long id,
            @RequestParam("name") String name,
            @RequestParam("course") String course,
            @RequestParam("address") String address,
            @RequestParam("school") String school,
            @RequestParam("bio") String bio,
            @RequestParam(value = "image", required = false) MultipartFile image
    ) {
        return profileRepository.findById(id).map(profile -> {
            profile.setName(name);
            profile.setCourse(course);
            profile.setAddress(address);
            profile.setSchool(school);
            profile.setBio(bio);

            if (image != null && !image.isEmpty()) {
                try {
                    String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
                    Path uploadPath = Paths.get("uploads");
                    if (!Files.exists(uploadPath)) {
                        Files.createDirectories(uploadPath);
                    }

                    Path filePath = uploadPath.resolve(fileName);
                    Files.write(filePath, image.getBytes());

                    profile.setImageUrl("http://localhost:8080/uploads/" + fileName);
                } catch (IOException e) {
                    throw new RuntimeException("Failed to upload image", e);
                }
            }

            return profileRepository.save(profile);
        }).orElseThrow(() -> new RuntimeException("Profile not found"));
    }
}

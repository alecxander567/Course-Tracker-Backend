package com.example.demo;

import jakarta.persistence.*;

@Entity
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String imageUrl;
    private String name;
    private String course;
    private String address;
    private String school;

    @Column(length = 1000)
    private String bio;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getCourse() { return course; }

    public void setCourse(String course) { this.course = course; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public String getSchool() { return school; }

    public void setSchool(String school) { this.school = school; }

    public String getBio() { return bio; }

    public void setBio(String bio) { this.bio = bio; }
}

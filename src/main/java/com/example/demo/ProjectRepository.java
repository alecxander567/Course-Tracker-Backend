package com.example.demo;

import com.example.demo.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByYearLevel(int yearLevel);
}
package com.example.demo;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("/{yearLevel}")
    public List<Course> getByYear(@PathVariable int yearLevel) {
        return courseRepository.findByYearLevel(yearLevel);
    }

    @PostMapping
    public Course addCourse(@RequestBody Course course) {
        return courseRepository.save(course);
    }

    @PutMapping("/{id}")
    public Course updateNote(@PathVariable Long id, @RequestBody Course updatedCourse) {
        Course course = courseRepository.findById(id).orElse(null);
        if (course != null) {
            course.setNote(updatedCourse.getNote());
            return courseRepository.save(course);
        } else {
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

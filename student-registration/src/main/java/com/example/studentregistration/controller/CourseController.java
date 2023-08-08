package com.example.studentregistration.controller;

import com.example.studentregistration.model.Course;
import com.example.studentregistration.model.CourseDto;
import com.example.studentregistration.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    CourseRepository courseRepository;

    @GetMapping
    public ResponseEntity<List<CourseDto>> getAllCourses() {
        return ResponseEntity.status(HttpStatus.OK).body(courseRepository.findAll().stream().map(CourseDto::new).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCourseById(@PathVariable Long id) {
        Course courseValidation = courseRepository.findById(id).orElse(null);
        if (courseValidation == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(new CourseDto(courseValidation));
    }

    @PostMapping
    public ResponseEntity<CourseDto> postCourse(@RequestBody Course course) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new CourseDto(courseRepository.save(course)));
    }

    @PutMapping
    public ResponseEntity<CourseDto> putCourse(@RequestBody Course course) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new CourseDto(courseRepository.save(course)));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteCourse(@RequestBody Course course) {
        courseRepository.delete(course);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted!");
    }
}

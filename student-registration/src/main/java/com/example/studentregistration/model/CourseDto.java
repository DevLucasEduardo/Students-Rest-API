package com.example.studentregistration.model;

import java.util.List;

public record CourseDto(Long id, Integer duration, String name, List<Student> student) {

    public CourseDto(Course course) {
        this(course.getId(), course.getDuration(), course.getName(), course.getStudents());
    }
}

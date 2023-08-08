package com.example.studentregistration.model;

import java.time.LocalDate;

public record StudentDto(Long id, String name, LocalDate birthDate, Course course) {

    public StudentDto (Student student){
        this(student.getId(), student.getName(), student.getBirthDate(), student.getCourse());
    }
}

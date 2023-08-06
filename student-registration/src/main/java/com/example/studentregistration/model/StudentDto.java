package com.example.studentregistration.model;

import java.time.LocalDate;

public record StudentDto(Long id, String name, LocalDate birthDate) {

    public StudentDto (Student student){
        this(student.getId(), student.getName(), student.getBirthDate());
    }
}

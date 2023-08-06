package com.example.studentregistration.controller;

import com.example.studentregistration.model.Student;
import com.example.studentregistration.model.StudentDto;
import com.example.studentregistration.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class ControllerStudent {

    @Autowired
    StudentRepository studentRepository;

    @GetMapping
    public List<StudentDto> getStudents() {
        return studentRepository.findAll().stream().map(StudentDto::new).toList();
    }

    @GetMapping("/{id}")
    public StudentDto getStudentById(@PathVariable Long id) {
        Student studentValidation = studentRepository.findById(id).orElse(null);
        if (studentValidation == null) {
            return null;
        }
        return new StudentDto(studentValidation);
    }

    @PostMapping
    public StudentDto postStudent(@RequestBody Student student) {
        return new StudentDto(studentRepository.save(student));
    }

    @PutMapping
    public StudentDto putStudent(@RequestBody Student student) {
        return new StudentDto(studentRepository.save(student));
    }

    @DeleteMapping
    public void deleteStudent(@RequestBody Student student) {
        studentRepository.delete(student);
    }


}

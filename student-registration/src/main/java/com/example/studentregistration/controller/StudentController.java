package com.example.studentregistration.controller;

import com.example.studentregistration.model.Student;
import com.example.studentregistration.model.StudentDto;
import com.example.studentregistration.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @GetMapping
    public ResponseEntity<List<StudentDto>> getAllStudents() {
        return ResponseEntity.status(HttpStatus.OK).body(studentRepository.findAll().stream().map(StudentDto::new).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getStudentById(@PathVariable Long id) {
        Student studentValidation = studentRepository.findById(id).orElse(null);
        if (studentValidation == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(new StudentDto(studentValidation));
    }

    @PostMapping
    public ResponseEntity<StudentDto> postStudent(@RequestBody Student student) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new StudentDto(studentRepository.save(student)));
    }

    @PutMapping
    public ResponseEntity<StudentDto> putStudent(@RequestBody Student student) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new StudentDto(studentRepository.save(student)));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteStudent(@RequestBody Student student) {
        studentRepository.delete(student);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted!");
    }


}

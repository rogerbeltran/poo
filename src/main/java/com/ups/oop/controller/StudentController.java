package com.ups.oop.controller;

import com.ups.oop.dto.StudentDTO;
import com.ups.oop.repository.StudentRepository;
import com.ups.oop.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {
    private final StudentService studentService;
    private final StudentRepository studentRepository;

    public StudentController(StudentService studentService, StudentRepository studentRepository) {
        this.studentService = studentService;
        this.studentRepository = studentRepository;
    }

    @PostMapping("/create-student")
    public ResponseEntity createStudent(@RequestBody StudentDTO student) {
        return this.studentService.createStudent(student);
    }

    @GetMapping("/get-all-student")
    public ResponseEntity getAllStudents() {
        return this.studentService.getAllStudent();
    }

    @GetMapping("/get-student")
    public ResponseEntity getStudentById(@RequestParam String id) {
        return this.studentService.getStudentById(id);
    }

    @PutMapping("/update-student")
    public StudentDTO updateStudent(@RequestParam String id, @RequestBody StudentDTO student) {
        return this.studentService.updateStudent(id, student);
    }

    @DeleteMapping("/remove-student")
    public String deleteStudent(@RequestParam String id) {
        return this.studentService.deleteStudent(id);
    }
}
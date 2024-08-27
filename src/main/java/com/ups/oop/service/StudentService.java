package com.ups.oop.service;

import com.ups.oop.dto.StudentDTO;
import com.ups.oop.entity.Student;
import com.ups.oop.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private List<StudentDTO> studentList = new ArrayList<>();
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public ResponseEntity createStudent(StudentDTO student) {
        String studentId = student.getId();
        boolean wasFound = findStudent(studentId);
        if (wasFound) {
            String errorMessage = "Student with id " + studentId + " already exists";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(errorMessage);
        } else {
            studentList.add(student);
            return ResponseEntity.status(HttpStatus.OK).body(student);
        }
    }

    private boolean findStudent(String id) {
        for (StudentDTO student : studentList) {
            if (id.equalsIgnoreCase(student.getId())) {
                return true;
            }
        }
        return false;
    }

    public ResponseEntity getAllStudent() {
        Iterable<Student> studentIterable = studentRepository.findAll();
        List<StudentDTO> students = new ArrayList<>();
        for (Student s : studentIterable) {
            StudentDTO student = new StudentDTO();
            student.setId(s.getId().toString());
            student.setStudentCode(s.getStudentCode());
            student.setName(s.getName());
            student.setLastName(s.getLastName());
            students.add(student);
        }
        if (students.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student List Not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(students);
    }

    public ResponseEntity getStudentById(String id) {
        Optional<Student> studentOptional = studentRepository.findById(Long.valueOf(id));
        if (studentOptional.isPresent()) {
            Student studentFound = studentOptional.get();
            StudentDTO student = new StudentDTO(studentFound.getId().toString(),
                    studentFound.getStudentCode(), studentFound.getName(), studentFound.getLastName());
            return ResponseEntity.status(HttpStatus.OK).body(student);
        } else {
            String errorMessage = "Student with id " + id + " not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

    public StudentDTO updateStudent(String id, StudentDTO student) {
        int index = findIndex(id);
        if (index != -1) {
            studentList.set(index, student);
            return student;
        }
        return new StudentDTO();
    }

    private int findIndex(String id) {
        int index = 0;
        for (StudentDTO s : studentList) {
            if (id.equalsIgnoreCase(s.getId())) {
                return index;
            }
            index++;
        }
        return -1;
    }

    public String deleteStudent(String id) {
        String message = "Student with id " + id;
        for (StudentDTO student : studentList) {
            if (id.equalsIgnoreCase(student.getId())) {
                studentList.remove(student);
                return message + " removed successfully";
            }
        }
        return message + " not found";
    }
}
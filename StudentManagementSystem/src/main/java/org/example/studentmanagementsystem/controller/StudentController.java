package org.example.studentmanagementsystem.controller;

import org.example.studentmanagementsystem.model.Student;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    // Temporary storage
    private List<Student> students = new ArrayList<>();

    // (1) Add a new student
    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {

        students.add(student);

        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    // (2) Get all students
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {

        return ResponseEntity.ok(students);
    }

    // (3) Get student by ID
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable int id) {

        for (Student s : students) {
            if (s.getId() == id) {
                return ResponseEntity.ok(s);
            }
        }

        return ResponseEntity.notFound().build();
    }

    // (4) Update student
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(
            @PathVariable int id,
            @RequestBody Student updatedStudent) {

        for (Student s : students) {
            if (s.getId() == id) {

                s.setName(updatedStudent.getName());
                s.setCourse(updatedStudent.getCourse());
                s.setMarks(updatedStudent.getMarks());

                return ResponseEntity.ok(s);
            }
        }

        return ResponseEntity.notFound().build();
    }

    // (5) Delete student
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable int id) {

        for (Student s : students) {
            if (s.getId() == id) {

                students.remove(s);

                return ResponseEntity.ok("Student deleted successfully");
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Student not found");
    }
}


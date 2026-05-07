package com.example.StudentManagementSystem.StudentManagementSystem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import com.example.StudentManagementSystem.StudentManagementSystem.dto.StudentRequestDTO;
import com.example.StudentManagementSystem.StudentManagementSystem.dto.StudentResponseDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/stu")
public class StudentController {

    @Autowired
    private StudentService ser;

    @PostMapping
    public StudentResponseDTO addStudent(
            @Valid
            @RequestBody StudentRequestDTO dto) {

        return ser.addStudent(dto);
    }

    @GetMapping
    public List<Student> getAllStudents() {

        return ser.getAllStudents();
    }

    @GetMapping("/{id}")
    public Student getStudentById(
            @PathVariable Long id) {

        return ser.getStudentById(id);
    }

    @PutMapping("/{id}")
    public Student updateStudent(
            @PathVariable Long id,

            @Valid
            @RequestBody Student student) {

        return ser.updateStudent(id, student);
    }
    @DeleteMapping("/{id}")
    public void deleteStudent(
            @PathVariable Long id) {

        ser.deleteStudent(id);
    }

    @GetMapping("/paginated")
    public Page<Student> getStudentsPaginated(

            @RequestParam int page,

            @RequestParam int size,

            @RequestParam String sortField) {

        return ser.getStudentsPaginated(
                page,
                size,
                sortField);
    }
    @GetMapping("/search/name")
    public List<Student> searchByName(

            @RequestParam String name) {

        return ser.searchByName(name);
    }

    @GetMapping("/search/course")
    public List<Student> searchByCourse(

            @RequestParam String course) {

        return ser.searchByCourse(course);
    }
}
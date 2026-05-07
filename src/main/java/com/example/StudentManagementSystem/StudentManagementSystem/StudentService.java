package com.example.StudentManagementSystem.StudentManagementSystem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;
import com.example.StudentManagementSystem.StudentManagementSystem.dto.StudentRequestDTO;
import com.example.StudentManagementSystem.StudentManagementSystem.dto.StudentResponseDTO;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repo;

    private StudentResponseDTO mapToResponseDTO(Student student) {

        StudentResponseDTO dto =
                new StudentResponseDTO();

        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setEmail(student.getEmail());
        dto.setCourse(student.getCourse());

        return dto;
    }


    public StudentResponseDTO addStudent(
            StudentRequestDTO dto) {

        Student student = new Student();

        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setCourse(dto.getCourse());

        Student savedStudent = repo.save(student);

        return mapToResponseDTO(savedStudent);
    }

    public List<Student> getAllStudents() {

        return repo.findAll();
    }

    public Student getStudentById(Long id) {

        return repo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Student not found with id " + id));
    }

    public Student updateStudent(
            Long id,
            Student updatedStudent) {

        Student existingStudent =
                repo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Student not found with id " + id));

        existingStudent.setName(
                updatedStudent.getName());

        existingStudent.setEmail(
                updatedStudent.getEmail());

        existingStudent.setCourse(
                updatedStudent.getCourse());

        return repo.save(existingStudent);
    }

    public void deleteStudent(Long id) {

        Student existingStudent =
                repo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Student not found with id " + id));

        repo.delete(existingStudent);
    }

    public Page<Student> getStudentsPaginated(
            int page,
            int size,
            String sortField) {

        Pageable pageable =
                PageRequest.of(
                        page,
                        size,
                        Sort.by(sortField).ascending()
                );

        return repo.findAll(pageable);
    }

    public List<Student> searchByName(
            String name) {

        return repo.findByName(name);
    }

    public List<Student> searchByCourse(
            String course) {

        return repo.findByCourse(course);
    }
}
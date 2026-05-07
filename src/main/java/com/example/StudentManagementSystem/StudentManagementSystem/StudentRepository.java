package com.example.StudentManagementSystem.StudentManagementSystem;
import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface StudentRepository extends JpaRepository<Student,Long>{
	List<Student> findByName(String name);

	List<Student> findByCourse(String course);
}

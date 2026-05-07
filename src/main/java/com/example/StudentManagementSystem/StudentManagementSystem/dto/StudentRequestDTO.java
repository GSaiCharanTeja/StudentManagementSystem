package com.example.StudentManagementSystem.StudentManagementSystem.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class StudentRequestDTO {

    @NotBlank(message = "Name cannot be empty")
    @Size(min = 3,
          message = "Name must contain at least 3 characters")
    @Pattern(regexp = "^[A-Za-z ]+$",
             message = "Name should contain only alphabets")
    private String name;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email cannot be empty")
    private String email;

    @NotBlank(message = "Course cannot be empty")
    private String course;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}
    
    
}
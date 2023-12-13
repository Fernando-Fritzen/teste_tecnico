package com.school.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.school.exceptions.ResourceNotFoundException;
import com.school.model.entities.Student;

@SpringBootTest
public class StudentServiceTest {

	@Autowired
	StudentService studentService;

	private Student defaultStudent;

	@BeforeEach
	void setup() {
		defaultStudent = new Student(8, "Yasmin Rodrigues");
		studentService.insert(defaultStudent);
	}

	@AfterEach
	void teardown() {
		studentService.deleteById(defaultStudent.getId());
	}

	@Test
	void shouldCreateStudent() {
		Student created = studentService.findById(defaultStudent.getId());

		assertNotNull(created);
		assertEquals(defaultStudent.getId(), created.getId());
		assertEquals(defaultStudent.getName(), created.getName());
	}

	@Test
	void shouldReturnStudentList() {
		List<Student> studentsList = studentService.findAll();
		assertNotNull(studentsList);
		assertEquals(studentsList.size(), 4);
	}

	@Test
	void shouldReturnStudentListByCourse() {
		List<Student> studentsList = studentService.findByCourse(1);
		assertNotNull(studentsList);
		assertEquals(studentsList.size(), 1);
	}

	@Test
	void shouldUpdateStudent() {
		Student newStudent = new Student(1, "Kaique Moura");
		studentService.update(newStudent, newStudent.getId());

		Student updated = studentService.findById(newStudent.getId());

		assertNotNull(updated);
		assertEquals(newStudent.getId(), updated.getId());
		assertEquals(newStudent.getName(), updated.getName());
	}

	@Test
	void shouldThrowResourceNotFoundException() {
		ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
				() -> studentService.findById(120), "");

		assertTrue(exception.getMessage().contains("Aluno não encontrado!"));
	}

}

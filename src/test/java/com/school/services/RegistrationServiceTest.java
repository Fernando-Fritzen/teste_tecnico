package com.school.services;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.school.exceptions.BadRequestException;
import com.school.exceptions.ResourceNotFoundException;
import com.school.model.entities.Course;
import com.school.model.entities.Registration;
import com.school.model.entities.Student;

@SpringBootTest
public class RegistrationServiceTest {

	@Autowired
	RegistrationService registrationService;

	@Autowired
	CourseService courseService;

	@Autowired
	StudentService studentService;

	private Course defaultCourse;
	private Student defaultStudent;

	@BeforeEach
	void setup() {
		defaultCourse = new Course(10, "Direito");
		defaultStudent = new Student(10, "Bruno Marques");

		courseService.insert(defaultCourse);
		studentService.insert(defaultStudent);
	}

	@AfterEach
	void teardown() {
		courseService.deleteById(defaultCourse.getId());
		studentService.deleteById(defaultStudent.getId());
	}

	@Test
	void shouldRegistrateStudent() {
		Registration registration = new Registration(10, defaultStudent.getId(), defaultCourse.getId());
		registrationService.registrate(registration);

		Boolean isRegistrated = registrationService.isStudentAlreadyRegistered(registration.getIdStudent(),
				registration.getIdCourse());

		assertTrue(isRegistrated);
	}

	@Test
	void shouldThrowBadRequestExceptionWithoutStudent() {
		Registration registration = new Registration(1, null, 1);
		BadRequestException exception = assertThrows(BadRequestException.class,
				() -> registrationService.registrate(registration), "");

		assertTrue(exception.getMessage().contains("Aluno ou curso não informado!"));
	}

	@Test
	void shouldThrowBadRequestExceptionWithoutCourse() {
		Registration registration = new Registration(1, 1, null);
		BadRequestException exception = assertThrows(BadRequestException.class,
				() -> registrationService.registrate(registration), "");

		assertTrue(exception.getMessage().contains("Aluno ou curso não informado!"));
	}

	@Test
	void shouldThrowBadRequestExceptionStudentAlreadyRegistered() {
		Registration registration = new Registration(29, 1, 1);
		BadRequestException exception = assertThrows(BadRequestException.class,
				() -> registrationService.registrate(registration), "");

		assertTrue(exception.getMessage().contains("Aluno já matriculado no curso!"));
	}

	@Test
	void shouldThrowResourceNotFoundExceptionStudent() {
		Registration registration = new Registration(29, 89, 1);
		ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
				() -> registrationService.registrate(registration), "");

		assertTrue(exception.getMessage().contains("Aluno não encontrado!"));
	}

	@Test
	void shouldThrowResourceNotFoundExceptionCourse() {
		Registration registration = new Registration(29, 1, 89);
		ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
				() -> registrationService.registrate(registration), "");

		assertTrue(exception.getMessage().contains("Curso não encontrado!"));
	}

}

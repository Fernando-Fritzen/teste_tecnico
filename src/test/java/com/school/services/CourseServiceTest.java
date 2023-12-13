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
import com.school.model.entities.Course;

@SpringBootTest
public class CourseServiceTest {

	@Autowired
	CourseService courseService;

	@Autowired
	RegistrationService registrationService;

	private Course defaultCourse;

	@BeforeEach
	void setup() {
		defaultCourse = new Course(8, "Medicina Veterinária");
		courseService.insert(defaultCourse);
	}

	@AfterEach
	void teardown() {
		courseService.deleteById(defaultCourse.getId());
	}

	@Test
	void shouldCreateCourse() {
		Course created = courseService.findById(defaultCourse.getId());

		assertNotNull(created);
		assertEquals(defaultCourse.getId(), created.getId());
		assertEquals(defaultCourse.getDescription(), created.getDescription());
	}

	@Test
	void shouldReturnCourseList() {
		List<Course> coursesList = courseService.findAll();
		assertNotNull(coursesList);
		assertEquals(coursesList.size(), 4);
	}

	@Test
	void shouldThrowResourceNotFoundException() {
		ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
				() -> courseService.findById(120), "");

		assertTrue(exception.getMessage().contains("Curso não encontrado!"));
	}

	@Test
	void shouldUpdateCourse() {
		Course newCourse = new Course(1, "Engenharia Ambiental");
		courseService.update(newCourse, newCourse.getId());

		Course updated = courseService.findById(newCourse.getId());

		assertNotNull(updated);
		assertEquals(newCourse.getId(), updated.getId());
		assertEquals(newCourse.getDescription(), updated.getDescription());
	}

	@Test
	void shouldReturnCourseWithoutRegistrationsList() {
		List<Course> coursesList = courseService.findWithoutRegistrations();
		assertNotNull(coursesList);
		assertEquals(coursesList.size(), 2);
	}

}

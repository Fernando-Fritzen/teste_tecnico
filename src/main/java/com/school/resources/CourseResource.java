package com.school.resources;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.school.exceptions.ResourceNotFoundException;
import com.school.model.dao.CourseDao;
import com.school.model.dao.DaoFactory;
import com.school.model.entities.Course;

@RestController
@RequestMapping(value = "/v1/courses")
public class CourseResource {

	@GetMapping
	public ResponseEntity<List<Course>> findAll() {
		CourseDao courseDao = DaoFactory.createCourseDao();
		List<Course> courses = courseDao.findAll();

		return ResponseEntity.ok().body(courses);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Course> findById(@PathVariable Integer id) {
		CourseDao courseDao = DaoFactory.createCourseDao();
		Course course = courseDao.findById(id);

		if (course == null)
			throw new ResourceNotFoundException("Curso não encontrado!");

		return ResponseEntity.ok().body(course);
	}

	@GetMapping(value = "/without-registrations")
	public ResponseEntity<List<Course>> findWithoutRegistrations() {
		CourseDao courseDao = DaoFactory.createCourseDao();
		List<Course> courses = courseDao.findWithoutRegistrations();

		return ResponseEntity.ok().body(courses);

	}

	@PostMapping
	public ResponseEntity<Course> insert(@RequestBody Course course) {
		CourseDao courseDao = DaoFactory.createCourseDao();
		course = courseDao.insert(course);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(course.getId()).toUri();

		return ResponseEntity.created(uri).body(course);

	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Course> update(@PathVariable Integer id, @RequestBody Course course) {
		CourseDao courseDao = DaoFactory.createCourseDao();
		course = courseDao.update(course, id);

		return ResponseEntity.ok().body(course);
	}

}

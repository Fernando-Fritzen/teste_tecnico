package com.school.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.school.model.entities.Course;
import com.school.services.CourseService;

@RestController
@RequestMapping(value = "/v1/courses")
public class CourseResource {

	@Autowired
	CourseService courseService;

	@GetMapping
	public ResponseEntity<List<Course>> findAll() {
		List<Course> courses = courseService.findAll();

		return ResponseEntity.ok().body(courses);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Course> findById(@PathVariable Integer id) {
		Course course = courseService.findById(id);

		return ResponseEntity.ok().body(course);
	}

	@GetMapping(value = "/without-registrations")
	public ResponseEntity<List<Course>> findWithoutRegistrations() {
		List<Course> courses = courseService.findWithoutRegistrations();

		return ResponseEntity.ok().body(courses);

	}

	@PostMapping
	public ResponseEntity<Course> insert(@RequestBody Course course) {
		course = courseService.insert(course);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(course.getId()).toUri();

		return ResponseEntity.created(uri).body(course);

	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Course> update(@PathVariable Integer id, @RequestBody Course course) {
		course = courseService.update(course, id);

		return ResponseEntity.ok().body(course);
	}

}

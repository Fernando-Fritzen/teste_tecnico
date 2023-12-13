package com.school.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.school.model.entities.Student;
import com.school.services.StudentService;

@RestController
@RequestMapping(value = "/v1/students")
public class StudentResource {

	@Autowired
	StudentService studentService;

	@GetMapping
	public ResponseEntity<List<Student>> findAll() {
		List<Student> students = studentService.findAll();

		return ResponseEntity.ok().body(students);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Student> findById(@PathVariable Integer id) {
		Student student = studentService.findById(id);

		return ResponseEntity.ok().body(student);
	}

	@GetMapping(value = "/{idCourse}/find-by-course")
	public ResponseEntity<List<Student>> findByCourse(@PathVariable Integer idCourse) {
		List<Student> students = studentService.findByCourse(idCourse);

		return ResponseEntity.ok().body(students);

	}

	@PostMapping
	public ResponseEntity<Student> insert(@RequestBody Student student) {
		student = studentService.insert(student);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(student.getId())
				.toUri();

		return ResponseEntity.created(uri).body(student);

	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Student> update(@PathVariable Integer id, @RequestBody Student student) {
		student = studentService.update(student, id);

		return ResponseEntity.ok().body(student);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
		studentService.deleteById(id);

		return ResponseEntity.noContent().build();
	}

}

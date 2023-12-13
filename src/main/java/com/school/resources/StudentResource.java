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
import com.school.model.dao.StudentDao;
import com.school.model.entities.Course;
import com.school.model.entities.Student;

@RestController
@RequestMapping(value = "/v1/students")
public class StudentResource {

	@GetMapping
	public ResponseEntity<List<Student>> findAll() {
		StudentDao studentDao = DaoFactory.createStudentDao();
		List<Student> students = studentDao.findAll();

		return ResponseEntity.ok().body(students);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Student> findById(@PathVariable Integer id) {
		StudentDao studentDao = DaoFactory.createStudentDao();
		Student student = studentDao.findById(id);

		if (student == null)
			throw new ResourceNotFoundException("Aluno não encontrado!");

		return ResponseEntity.ok().body(student);
	}

	@GetMapping(value = "/{idCourse}/find-by-course")
	public ResponseEntity<List<Student>> findByCourse(@PathVariable Integer idCourse) {
		StudentDao studentDao = DaoFactory.createStudentDao();
		CourseDao courseDao = DaoFactory.createCourseDao();

		Course course = courseDao.findById(idCourse);
		if (course == null)
			throw new ResourceNotFoundException("Curso não encontrado!");

		List<Student> students = studentDao.findByCourse(idCourse);

		return ResponseEntity.ok().body(students);

	}

	@PostMapping
	public ResponseEntity<Student> insert(@RequestBody Student student) {
		StudentDao studentDao = DaoFactory.createStudentDao();
		student = studentDao.insert(student);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(student.getId())
				.toUri();

		return ResponseEntity.created(uri).body(student);

	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Student> update(@PathVariable Integer id, @RequestBody Student student) {
		StudentDao studentDao = DaoFactory.createStudentDao();
		student = studentDao.update(student, id);

		return ResponseEntity.ok().body(student);
	}

}

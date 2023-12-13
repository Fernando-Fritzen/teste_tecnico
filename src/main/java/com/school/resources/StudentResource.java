package com.school.resources;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}

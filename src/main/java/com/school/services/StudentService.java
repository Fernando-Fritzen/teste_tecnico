package com.school.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.exceptions.ResourceNotFoundException;
import com.school.model.dao.DaoFactory;
import com.school.model.dao.StudentDao;
import com.school.model.entities.Student;

@Service
public class StudentService {

	@Autowired
	CourseService courseService;

	@Autowired
	RegistrationService registrationService;

	public List<Student> findAll() {
		StudentDao studentDao = DaoFactory.createStudentDao();
		return studentDao.findAll();
	}

	public Student findById(Integer id) {
		StudentDao studentDao = DaoFactory.createStudentDao();
		Student student = studentDao.findById(id);

		if (student == null)
			throw new ResourceNotFoundException("Aluno não encontrado!");

		return student;
	}

	public List<Student> findByCourse(Integer idCourse) {
		StudentDao studentDao = DaoFactory.createStudentDao();

		courseService.findById(idCourse);
		return studentDao.findByCourse(idCourse);

	}

	public Student insert(Student student) {
		StudentDao studentDao = DaoFactory.createStudentDao();
		return studentDao.insert(student);

	}

	public Student update(Student student, Integer id) {
		StudentDao studentDao = DaoFactory.createStudentDao();
		return studentDao.update(student, id);
	}

	public void deleteById(Integer id) {
		StudentDao studentDao = DaoFactory.createStudentDao();

		registrationService.deleteByStudent(id);
		studentDao.deleteById(id);

	}

}

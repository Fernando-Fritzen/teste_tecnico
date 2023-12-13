package com.school.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.school.exceptions.ResourceNotFoundException;
import com.school.model.dao.CourseDao;
import com.school.model.dao.DaoFactory;
import com.school.model.entities.Course;

@Service
public class CourseService {

	public List<Course> findAll() {
		CourseDao courseDao = DaoFactory.createCourseDao();
		return courseDao.findAll();
	}

	public Course findById(Integer id) {
		CourseDao courseDao = DaoFactory.createCourseDao();
		Course course = courseDao.findById(id);

		if (course == null)
			throw new ResourceNotFoundException("Curso não encontrado!");

		return course;
	}

	public List<Course> findWithoutRegistrations() {
		CourseDao courseDao = DaoFactory.createCourseDao();
		return courseDao.findWithoutRegistrations();

	}

	public Course insert(Course course) {
		CourseDao courseDao = DaoFactory.createCourseDao();
		return courseDao.insert(course);
	}

	public Course update(Course course, Integer id) {
		CourseDao courseDao = DaoFactory.createCourseDao();
		return courseDao.update(course, id);

	}

}

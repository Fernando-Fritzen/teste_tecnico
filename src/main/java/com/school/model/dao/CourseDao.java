package com.school.model.dao;

import java.util.List;

import com.school.model.entities.Course;

public interface CourseDao {

	List<Course> findAll();

	Course findById(Integer id);

	List<Course> findWithoutRegistrations();

	Course insert(Course course);

	Course update(Course course, Integer id);

}

package com.school.model.dao;

import java.util.List;

import com.school.model.entities.Course;

public interface CourseDao {

	List<Course> findAll();

	Course findById(Integer id);

}

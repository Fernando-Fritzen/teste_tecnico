package com.school.model.dao;

import java.util.List;

import com.school.model.entities.Student;

public interface StudentDao {

	List<Student> findAll();

	Student findById(Integer id);

	List<Student> findByCourse(Integer idCourse);

	Student insert(Student student);

	Student update(Student student, Integer id);

	void deleteById(Integer id);
}

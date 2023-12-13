package com.school.model.dao.impl;

import java.sql.Connection;

import com.school.model.dao.CourseDao;

public class CourseDaoJDBC implements CourseDao {

	private Connection conn;

	public CourseDaoJDBC(Connection conn) {
		this.conn = conn;
	}

}

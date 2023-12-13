package com.school.model.dao.impl;

import java.sql.Connection;

import com.school.model.dao.StudentDao;

public class StudentDaoJDBC implements StudentDao {

	private Connection conn;

	public StudentDaoJDBC(Connection conn) {
		this.conn = conn;
	}

}

package com.school.model.dao.impl;

import java.sql.Connection;

import com.school.model.dao.RegistrationDao;

public class RegistrationDaoJDBC implements RegistrationDao {

	private Connection conn;

	public RegistrationDaoJDBC(Connection conn) {
		this.conn = conn;
	}

}

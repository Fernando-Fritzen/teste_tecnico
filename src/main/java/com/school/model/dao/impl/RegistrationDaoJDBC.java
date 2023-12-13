package com.school.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.school.db.DB;
import com.school.db.DbException;
import com.school.model.dao.RegistrationDao;
import com.school.model.entities.Registration;

public class RegistrationDaoJDBC implements RegistrationDao {

	private Connection conn;

	public RegistrationDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public Registration registrate(Registration registration) {
		PreparedStatement st = null;
		try {

			st = conn.prepareStatement("INSERT INTO registration (id_course, id_student) VALUES (?, ?)",
					Statement.RETURN_GENERATED_KEYS);

			st.setInt(1, registration.getIdCourse());
			st.setInt(2, registration.getIdStudent());

			int rowsAffected = st.executeUpdate();

			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					registration.setId(id);
				}
			} else {
				throw new DbException("Unexpected error! No rows affected!");
			}

			return registration;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public Boolean isStudentAlreadyRegistered(Integer idStudent, Integer idCourse) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT count(*) counter FROM registration WHERE id_student = ? AND id_course = ?");
			st.setInt(1, idStudent);
			st.setInt(2, idCourse);

			rs = st.executeQuery();
			if (rs.next()) {
				Integer count = rs.getInt("counter");

				return count > 0;
			}
			return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
	
	@Override
	public void deleteByCourse(Integer idCourse) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM registration WHERE id_course = ?");
			
			st.setInt(1, idCourse);
			
			st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}
	
	@Override
	public void deleteByStudent(Integer idStudent) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM registration WHERE id_student = ?");
			
			st.setInt(1, idStudent);
			
			st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}
}

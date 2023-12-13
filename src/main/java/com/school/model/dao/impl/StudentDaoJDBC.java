package com.school.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.school.db.DB;
import com.school.db.DbException;
import com.school.model.dao.StudentDao;
import com.school.model.entities.Student;

public class StudentDaoJDBC implements StudentDao {

	private Connection conn;

	public StudentDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public List<Student> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT id, name FROM student ORDER BY name");
			rs = st.executeQuery();

			List<Student> students = new ArrayList<>();

			while (rs.next()) {
				Student student = new Student();
				student.setId(rs.getInt("id"));
				student.setName(rs.getString("name"));

				students.add(student);
			}
			return students;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public Student findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT id, name FROM student WHERE id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Student student = new Student();
				student.setId(rs.getInt("id"));
				student.setName(rs.getString("name"));
				return student;
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
	public List<Student> findByCourse(Integer idCourse) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT student.id, student.name "
			        + "FROM student INNER JOIN registration "
					+ "ON student.id = registration.id_student "
			        + "WHERE registration.id_course = ? "
					+ "ORDER BY name");

			st.setInt(1, idCourse);

			rs = st.executeQuery();

			List<Student> students = new ArrayList<>();

			while (rs.next()) {
				Student student = new Student();
				student.setId(rs.getInt("id"));
				student.setName(rs.getString("name"));

				students.add(student);
			}
			return students;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

}

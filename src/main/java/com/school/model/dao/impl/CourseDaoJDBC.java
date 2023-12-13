package com.school.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.school.db.DB;
import com.school.db.DbException;
import com.school.model.dao.CourseDao;
import com.school.model.entities.Course;

public class CourseDaoJDBC implements CourseDao {

	private Connection conn;

	public CourseDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public List<Course> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT id, description FROM course ORDER BY description");
			rs = st.executeQuery();

			List<Course> courses = new ArrayList<>();

			while (rs.next()) {
				Course course = new Course();
				course.setId(rs.getInt("id"));
				course.setDescription(rs.getString("description"));

				courses.add(course);
			}
			return courses;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public Course findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT id, description FROM course WHERE id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Course course = new Course();
				course.setId(rs.getInt("id"));
				course.setDescription(rs.getString("description"));
				return course;
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
	public List<Course> findWithoutRegistrations() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {

			st = conn.prepareStatement(
					"SELECT course.id, course.description "
					+ "FROM course "
					+ "LEFT JOIN registration ON course.id = registration.id_course "
					+ "WHERE registration.id IS NULL");

			rs = st.executeQuery();

			List<Course> courses = new ArrayList<>();

			while (rs.next()) {
				Course course = new Course();
				course.setId(rs.getInt("id"));
				course.setDescription(rs.getString("description"));

				courses.add(course);
			}
			return courses;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public Course insert(Course course) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO course (description) VALUES (?)", Statement.RETURN_GENERATED_KEYS);

			st.setString(1, course.getDescription());

			int rowsAffected = st.executeUpdate();

			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					course.setId(id);
				}
			} else {
				throw new DbException("Unexpected error! No rows affected!");
			}

			return course;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public Course update(Course course, Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("UPDATE course SET description = ? WHERE id = ?");

			st.setString(1, course.getDescription());
			st.setInt(2, id);

			st.executeUpdate();

			return course;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

}

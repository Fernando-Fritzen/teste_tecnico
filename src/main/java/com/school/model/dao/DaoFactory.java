package com.school.model.dao;

import com.school.db.DB;
import com.school.model.dao.impl.CourseDaoJDBC;
import com.school.model.dao.impl.StudentDaoJDBC;

public class DaoFactory {

	public static CourseDao createCourseDao() {
		return new CourseDaoJDBC(DB.getConnection());
	}

	public static StudentDao createStudentDao() {
		return new StudentDaoJDBC(DB.getConnection());
	}

}

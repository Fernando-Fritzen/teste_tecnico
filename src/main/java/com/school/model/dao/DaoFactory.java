package com.school.model.dao;

import com.school.db.DB;
import com.school.model.dao.impl.CourseDaoJDBC;

public class DaoFactory {

	public static CourseDao createCourseDao() {
		return new CourseDaoJDBC(DB.getConnection());
	}

}

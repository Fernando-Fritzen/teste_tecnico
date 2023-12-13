package com.school.model.entities;

import java.io.Serializable;
import java.util.Objects;

public class Registration implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer idStudent;
	private Integer idCourse;

	public Registration() {
	}

	public Registration(Integer id, Integer idStudent, Integer idCourse) {
		super();
		this.id = id;
		this.idStudent = idStudent;
		this.idCourse = idCourse;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdStudent() {
		return idStudent;
	}

	public void setIdStudent(Integer idStudent) {
		this.idStudent = idStudent;
	}

	public Integer getIdCourse() {
		return idCourse;
	}

	public void setIdCourse(Integer idCourse) {
		this.idCourse = idCourse;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Registration other = (Registration) obj;
		return Objects.equals(id, other.id);
	}

}

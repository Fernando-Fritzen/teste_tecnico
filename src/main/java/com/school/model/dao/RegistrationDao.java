package com.school.model.dao;

import com.school.model.entities.Registration;

public interface RegistrationDao {

	Registration registrate(Registration registration);

	Boolean isStudentAlreadyRegistered(Integer idStudent, Integer idCourse);

}

package com.school.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.exceptions.BadRequestException;
import com.school.model.dao.DaoFactory;
import com.school.model.dao.RegistrationDao;
import com.school.model.entities.Registration;

@Service
public class RegistrationService {

	@Autowired
	StudentService studentService;

	@Autowired
	CourseService courseService;

	public Registration registrate(Registration registration) {
		RegistrationDao registrationDao = DaoFactory.createRegistrationDao();

		if (registration.getIdStudent() == null || registration.getIdCourse() == null) {
			throw new BadRequestException("Aluno ou curso não informado!");
		}

		studentService.findById(registration.getIdStudent());
		courseService.findById(registration.getIdCourse());

		if (isStudentAlreadyRegistered(registration.getIdStudent(), registration.getIdCourse())) {
			throw new BadRequestException("Aluno já matriculado no curso!");
		}

		return registrationDao.registrate(registration);

	}

	public Boolean isStudentAlreadyRegistered(Integer idStudent, Integer idCourse) {
		RegistrationDao registrationDao = DaoFactory.createRegistrationDao();

		return registrationDao.isStudentAlreadyRegistered(idStudent, idCourse);
	}
}

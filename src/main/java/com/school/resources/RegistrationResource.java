package com.school.resources;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.school.exceptions.BadRequestException;
import com.school.model.dao.DaoFactory;
import com.school.model.dao.RegistrationDao;
import com.school.model.entities.Registration;

@RestController
@RequestMapping(value = "/v1/registrations")
public class RegistrationResource {

	@PostMapping
	public ResponseEntity<Void> registrate(@RequestBody Registration registration) {
		RegistrationDao registrationDao = DaoFactory.createRegistrationDao();

		if (registration.getIdStudent() == null || registration.getIdCourse() == null) {
			throw new BadRequestException("Aluno ou curso não informado!");
		}
		if (registrationDao.isStudentAlreadyRegistered(registration.getIdStudent(), registration.getIdCourse())) {
			throw new BadRequestException("Aluno já matriculado no curso!");
		}

		registration = registrationDao.registrate(registration);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(registration.getId())
				.toUri();

		return ResponseEntity.created(uri).build();

	}

}

package com.school.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.school.model.entities.Registration;
import com.school.services.RegistrationService;

@RestController
@RequestMapping(value = "/v1/registrations")
public class RegistrationResource {

	@Autowired
	RegistrationService registrationService;

	@PostMapping
	public ResponseEntity<Void> registrate(@RequestBody Registration registration) {

		registration = registrationService.registrate(registration);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(registration.getId())
				.toUri();

		return ResponseEntity.created(uri).build();

	}

}

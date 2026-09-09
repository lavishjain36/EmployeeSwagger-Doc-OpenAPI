package com.emp.employeeopenapidoc.service; // same folder as the service we test

import com.emp.employeeopenapidoc.model.Employee; // person we pretend exists
import com.emp.employeeopenapidoc.repository.EmployeeRepository; // we fake this
import org.junit.jupiter.api.Test; // marks a check
import org.junit.jupiter.api.extension.ExtendWith; // turn Mockito on
import org.mockito.InjectMocks; // put fakes into the real service
import org.mockito.Mock; // fake database
import org.mockito.junit.jupiter.MockitoExtension; // Mockito + JUnit 5
import org.springframework.web.server.ResponseStatusException; // error when missing

import java.util.Optional; // maybe a person, maybe not

import static org.junit.jupiter.api.Assertions.assertEquals; // names must match
import static org.junit.jupiter.api.Assertions.assertThrows; // expect an error
import static org.mockito.Mockito.when; // "if asked X, answer Y"

@ExtendWith(MockitoExtension.class) // start Mockito for this class
class EmployeeServiceTest { // checks service rules, no MySQL

	@Mock // fake repository — no real database
	EmployeeRepository repository;

	@InjectMocks // real service that uses the fake repository
	EmployeeService service;

	@Test // check 1: we can find Ajay
	void findsAjay() {
		Employee ajay = new Employee(); // empty person
		ajay.setId(1L); // give him number 1
		ajay.setFirstName("Ajay"); // give him a name

		when(repository.findById(1L)).thenReturn(Optional.of(ajay)); // pretend DB returns Ajay for id 1

		assertEquals("Ajay", service.findById(1L).getFirstName()); // service should give back Ajay
	}

	@Test // check 2: missing id fails
	void missingPersonFails() {
		when(repository.findById(99L)).thenReturn(Optional.empty()); // pretend DB has nobody with 99

		assertThrows(ResponseStatusException.class, () -> service.findById(99L)); // asking for 99 must throw
	}
}

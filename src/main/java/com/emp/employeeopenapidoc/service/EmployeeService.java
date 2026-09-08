package com.emp.employeeopenapidoc.service; // service package

import com.emp.employeeopenapidoc.model.Employee; // employee entity
import com.emp.employeeopenapidoc.repository.EmployeeRepository; // DB access
import org.springframework.http.HttpStatus; // 404 / 409
import org.springframework.stereotype.Service; // Spring service bean
import org.springframework.web.server.ResponseStatusException; // throw HTTP errors

import java.util.List; // list return type

@Service // Spring creates one instance
public class EmployeeService { // rules + CRUD

	private final EmployeeRepository repository; // talks to MySQL

	public EmployeeService(EmployeeRepository repository) { // Spring injects repo
		this.repository = repository; // store repo
	} // end constructor

	public List<Employee> findAll() { // list all
		return repository.findAll(); // SELECT * FROM employees
	} // end findAll

	public Employee findById(Long id) { // get one
		Employee employee = repository.findById(id).orElse(null); // SELECT by id, or null
		if (employee == null) { // no row
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found: " + id); // 404
		} // end if
		return employee; // found
	} // end findById

	public Employee create(Employee employee) { // add row
		employee.setId(null); // DB will assign id
		if (repository.existsByEmailIgnoreCase(employee.getEmail())) { // duplicate email?
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists: " + employee.getEmail()); // 409
		} // end if
		return repository.save(employee); // INSERT
	} // end create

	public Employee update(Long id, Employee employee) { // replace row
		findById(id); // 404 if missing
		if (repository.existsByEmailIgnoreCaseAndIdNot(employee.getEmail(), id)) { // email taken by someone else?
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists: " + employee.getEmail()); // 409
		} // end if
		employee.setId(id); // keep URL id
		return repository.save(employee); // UPDATE
	} // end update

	public void delete(Long id) { // remove row
		findById(id); // 404 if missing
		repository.deleteById(id); // DELETE
	} // end delete
} // end class

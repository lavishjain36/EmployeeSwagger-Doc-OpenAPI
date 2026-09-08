package com.emp.employeeopenapidoc.service; // folder for the rules

import com.emp.employeeopenapidoc.model.Employee; // person record
import com.emp.employeeopenapidoc.repository.EmployeeRepository; // database helper
import org.springframework.http.HttpStatus; // "not found" / "already exists"
import org.springframework.stereotype.Service; // this class holds the rules
import org.springframework.web.server.ResponseStatusException; // send a clear error back

import java.util.List; // a list of people

@Service // Spring makes one of these
public class EmployeeService { // add, find, change, remove people

	private final EmployeeRepository repository; // database helper

	public EmployeeService(EmployeeRepository repository) { // Spring gives us the helper
		this.repository = repository; // remember it
	} // constructor done

	public List<Employee> findAll() { // show everyone
		return repository.findAll(); // get all people from the database
	} // done

	public Employee findById(Long id) { // show one person
		Employee employee = repository.findById(id).orElse(null); // look up by number, or nothing
		if (employee == null) { // nobody with that number
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found: " + id); // tell the caller "not found"
		} // end check
		return employee; // found them
	} // done

	public Employee create(Employee employee) { // add a new person
		employee.setId(null); // let the database pick the number
		if (repository.existsByEmailIgnoreCase(employee.getEmail())) { // email already used?
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists: " + employee.getEmail()); // stop — duplicate email
		} // end check
		return repository.save(employee); // save the new person
	} // done

	public Employee update(Long id, Employee employee) { // change an existing person
		findById(id); // stop if that number does not exist
		if (repository.existsByEmailIgnoreCaseAndIdNot(employee.getEmail(), id)) { // someone else has this email?
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists: " + employee.getEmail()); // stop — duplicate email
		} // end check
		employee.setId(id); // keep the same number from the web address
		return repository.save(employee); // save the changes
	} // done

	public void delete(Long id) { // remove a person
		findById(id); // stop if that number does not exist
		repository.deleteById(id); // remove them from the database
	} // done
} // end of file

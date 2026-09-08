package com.emp.employeeopenapidoc.controller; // folder that handles web calls

import com.emp.employeeopenapidoc.model.Employee; // person sent in JSON
import com.emp.employeeopenapidoc.service.EmployeeService; // where the real work happens
import io.swagger.v3.oas.annotations.Operation; // short name in Swagger
import io.swagger.v3.oas.annotations.Parameter; // explain a URL number in Swagger
import io.swagger.v3.oas.annotations.media.Content; // no extra body in the error
import io.swagger.v3.oas.annotations.responses.ApiResponse; // one possible result in Swagger
import io.swagger.v3.oas.annotations.responses.ApiResponses; // several possible results
import io.swagger.v3.oas.annotations.tags.Tag; // group name in Swagger
import jakarta.validation.Valid; // check required fields before saving
import org.springframework.http.HttpStatus; // "no content" after delete
import org.springframework.http.ResponseEntity; // reply with extra details (new address)
import org.springframework.web.bind.annotation.DeleteMapping; // handle remove
import org.springframework.web.bind.annotation.GetMapping; // handle show
import org.springframework.web.bind.annotation.PathVariable; // take the number from the URL
import org.springframework.web.bind.annotation.PostMapping; // handle add
import org.springframework.web.bind.annotation.PutMapping; // handle replace
import org.springframework.web.bind.annotation.RequestBody; // read JSON from the caller
import org.springframework.web.bind.annotation.RequestMapping; // shared start of the URL
import org.springframework.web.bind.annotation.ResponseStatus; // set the reply code
import org.springframework.web.bind.annotation.RestController; // this class is a web API

import java.net.URI; // address of the new person
import java.util.List; // list of people

@RestController // answers web requests with JSON
@RequestMapping("/api/v1/employees") // all URLs start with this
@Tag(name = "Employees") // Swagger tab name
public class EmployeeController { // the doors people call (Get, Add, Change, Remove)

	private final EmployeeService employeeService; // helper that does the work

	public EmployeeController(EmployeeService employeeService) { // Spring gives us the helper
		this.employeeService = employeeService; // remember it
	} // constructor done

	@GetMapping // show everyone: GET /api/v1/employees
	@Operation(summary = "List employees") // Swagger button name
	@ApiResponse(responseCode = "200", description = "List of employees") // success in Swagger
	public List<Employee> list() { // return everyone
		return employeeService.findAll(); // ask the service for all people
	} // done

	@GetMapping("/{id}") // show one: GET /api/v1/employees/1
	@Operation(summary = "Get employee by id") // Swagger button name
	@ApiResponses({ // what can happen
			@ApiResponse(responseCode = "200", description = "Employee found"), // we found them
			@ApiResponse(responseCode = "404", description = "Employee not found", content = @Content) // nobody with that number
	}) // end list of results
	public Employee getById( // one person
			@Parameter(description = "Employee id", example = "1") @PathVariable Long id) { // number from the URL
		return employeeService.findById(id); // look them up
	} // done

	@PostMapping // add one: POST /api/v1/employees
	@Operation(summary = "Create employee") // Swagger button name
	@ApiResponses({ // what can happen
			@ApiResponse(responseCode = "201", description = "Employee created"), // added
			@ApiResponse(responseCode = "400", description = "Validation failed", content = @Content), // missing/bad fields
			@ApiResponse(responseCode = "409", description = "Email already exists", content = @Content) // email already used
	}) // end list of results
	public ResponseEntity<Employee> create(@Valid @RequestBody Employee employee) { // read JSON, reject empty/bad email
		Employee created = employeeService.create(employee); // save the new person
		return ResponseEntity.created(URI.create("/api/v1/employees/" + created.getId())).body(created); // "created" plus where to find them
	} // done

	@PutMapping("/{id}") // replace one: PUT /api/v1/employees/1
	@Operation(summary = "Replace employee") // Swagger button name
	@ApiResponses({ // what can happen
			@ApiResponse(responseCode = "200", description = "Employee updated"), // saved
			@ApiResponse(responseCode = "400", description = "Validation failed", content = @Content), // missing/bad fields
			@ApiResponse(responseCode = "404", description = "Employee not found", content = @Content), // nobody with that number
			@ApiResponse(responseCode = "409", description = "Email already exists", content = @Content) // email already used
	}) // end list of results
	public Employee update( // change all fields
			@Parameter(description = "Employee id", example = "1") @PathVariable Long id, // number from the URL
			@Valid @RequestBody Employee employee) { // new details from JSON
		return employeeService.update(id, employee); // save the changes
	} // done

	@DeleteMapping("/{id}") // remove one: DELETE /api/v1/employees/1
	@ResponseStatus(HttpStatus.NO_CONTENT) // success with no body
	@Operation(summary = "Delete employee") // Swagger button name
	@ApiResponses({ // what can happen
			@ApiResponse(responseCode = "204", description = "Employee deleted"), // removed
			@ApiResponse(responseCode = "404", description = "Employee not found", content = @Content) // nobody with that number
	}) // end list of results
	public void delete( // nothing to return
			@Parameter(description = "Employee id", example = "1") @PathVariable Long id) { // number from the URL
		employeeService.delete(id); // remove them
	} // done
} // end of file

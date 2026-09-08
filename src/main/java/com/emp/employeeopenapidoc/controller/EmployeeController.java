package com.emp.employeeopenapidoc.controller; // controller package

import com.emp.employeeopenapidoc.model.Employee; // JSON body type
import com.emp.employeeopenapidoc.service.EmployeeService; // business calls
import io.swagger.v3.oas.annotations.Operation; // Swagger method title
import io.swagger.v3.oas.annotations.Parameter; // Swagger path param
import io.swagger.v3.oas.annotations.media.Content; // empty error body in Swagger
import io.swagger.v3.oas.annotations.responses.ApiResponse; // one status in Swagger
import io.swagger.v3.oas.annotations.responses.ApiResponses; // several statuses
import io.swagger.v3.oas.annotations.tags.Tag; // Swagger group
import jakarta.validation.Valid; // run @NotBlank/@Email
import org.springframework.http.HttpStatus; // 204
import org.springframework.http.ResponseEntity; // 201 + headers
import org.springframework.web.bind.annotation.DeleteMapping; // DELETE
import org.springframework.web.bind.annotation.GetMapping; // GET
import org.springframework.web.bind.annotation.PathVariable; // {id}
import org.springframework.web.bind.annotation.PostMapping; // POST
import org.springframework.web.bind.annotation.PutMapping; // PUT
import org.springframework.web.bind.annotation.RequestBody; // JSON body
import org.springframework.web.bind.annotation.RequestMapping; // base path
import org.springframework.web.bind.annotation.ResponseStatus; // force status
import org.springframework.web.bind.annotation.RestController; // REST + JSON

import java.net.URI; // Location header
import java.util.List; // list of employees

@RestController // JSON API
@RequestMapping("/api/v1/employees") // base URL
@Tag(name = "Employees") // Swagger section
public class EmployeeController { // HTTP endpoints

	private final EmployeeService employeeService; // service used by all methods

	public EmployeeController(EmployeeService employeeService) { // Spring injects service
		this.employeeService = employeeService; // store service
	} // end constructor

	@GetMapping // GET /api/v1/employees
	@Operation(summary = "List employees") // Swagger title
	@ApiResponse(responseCode = "200", description = "List of employees") // 200 in Swagger
	public List<Employee> list() { // return all
		return employeeService.findAll(); // load all rows
	} // end list

	@GetMapping("/{id}") // GET /api/v1/employees/{id}
	@Operation(summary = "Get employee by id") // Swagger title
	@ApiResponses({ // possible statuses
			@ApiResponse(responseCode = "200", description = "Employee found"), // found
			@ApiResponse(responseCode = "404", description = "Employee not found", content = @Content) // missing
	}) // end ApiResponses
	public Employee getById( // one employee
			@Parameter(description = "Employee id", example = "1") @PathVariable Long id) { // id from URL
		return employeeService.findById(id); // load by id
	} // end getById

	@PostMapping // POST /api/v1/employees
	@Operation(summary = "Create employee") // Swagger title
	@ApiResponses({ // possible statuses
			@ApiResponse(responseCode = "201", description = "Employee created"), // created
			@ApiResponse(responseCode = "400", description = "Validation failed", content = @Content), // bad body
			@ApiResponse(responseCode = "409", description = "Email already exists", content = @Content) // duplicate
	}) // end ApiResponses
	public ResponseEntity<Employee> create(@Valid @RequestBody Employee employee) { // JSON → Employee, validate
		Employee created = employeeService.create(employee); // INSERT
		return ResponseEntity.created(URI.create("/api/v1/employees/" + created.getId())).body(created); // 201 + Location
	} // end create

	@PutMapping("/{id}") // PUT /api/v1/employees/{id}
	@Operation(summary = "Replace employee") // Swagger title
	@ApiResponses({ // possible statuses
			@ApiResponse(responseCode = "200", description = "Employee updated"), // ok
			@ApiResponse(responseCode = "400", description = "Validation failed", content = @Content), // bad body
			@ApiResponse(responseCode = "404", description = "Employee not found", content = @Content), // missing
			@ApiResponse(responseCode = "409", description = "Email already exists", content = @Content) // duplicate
	}) // end ApiResponses
	public Employee update( // replace fields
			@Parameter(description = "Employee id", example = "1") @PathVariable Long id, // id from URL
			@Valid @RequestBody Employee employee) { // JSON body
		return employeeService.update(id, employee); // UPDATE
	} // end update

	@DeleteMapping("/{id}") // DELETE /api/v1/employees/{id}
	@ResponseStatus(HttpStatus.NO_CONTENT) // 204
	@Operation(summary = "Delete employee") // Swagger title
	@ApiResponses({ // possible statuses
			@ApiResponse(responseCode = "204", description = "Employee deleted"), // deleted
			@ApiResponse(responseCode = "404", description = "Employee not found", content = @Content) // missing
	}) // end ApiResponses
	public void delete( // no body
			@Parameter(description = "Employee id", example = "1") @PathVariable Long id) { // id from URL
		employeeService.delete(id); // DELETE
	} // end delete
} // end class

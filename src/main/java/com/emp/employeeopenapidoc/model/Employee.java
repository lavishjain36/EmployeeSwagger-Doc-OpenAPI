package com.emp.employeeopenapidoc.model; // model package

import io.swagger.v3.oas.annotations.media.Schema; // Swagger field docs
import jakarta.persistence.Column; // column options
import jakarta.persistence.Entity; // JPA entity
import jakarta.persistence.GeneratedValue; // auto id
import jakarta.persistence.GenerationType; // IDENTITY = AUTO_INCREMENT
import jakarta.persistence.Id; // primary key
import jakarta.persistence.Table; // table name
import jakarta.validation.constraints.Email; // must look like email
import jakarta.validation.constraints.NotBlank; // must not be empty
import jakarta.validation.constraints.Size; // max length

@Entity // map class to a table
@Table(name = "employees") // table name in MySQL
@Schema(description = "Employee") // Swagger body name
public class Employee { // one employee

	@Id // primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // MySQL AUTO_INCREMENT
	@Schema(example = "1", accessMode = Schema.AccessMode.READ_ONLY) // do not type id on create
	private Long id; // employee number

	@NotBlank // 400 if blank
	@Size(max = 50) // max 50 chars
	@Schema(example = "Priya") // Swagger example
	private String firstName; // given name

	@NotBlank // 400 if blank
	@Size(max = 50) // max 50 chars
	@Schema(example = "Sharma") // Swagger example
	private String lastName; // family name

	@NotBlank // 400 if blank
	@Email // 400 if not an email
	@Column(unique = true) // unique in MySQL
	@Schema(example = "priya.sharma@example.com") // Swagger example
	private String email; // work email

	@NotBlank // 400 if blank
	@Size(max = 80) // max 80 chars
	@Schema(example = "Engineering") // Swagger example
	private String department; // team / dept

	@Size(max = 80) // optional, max 80 chars
	@Schema(example = "Software Engineer") // Swagger example
	private String jobTitle; // role

	public Long getId() { // read id
		return id; // return stored id
	} // end getId

	public void setId(Long id) { // write id
		this.id = id; // save id
	} // end setId

	public String getFirstName() { // read first name
		return firstName; // return stored first name
	} // end getFirstName

	public void setFirstName(String firstName) { // write first name
		this.firstName = firstName; // save first name
	} // end setFirstName

	public String getLastName() { // read last name
		return lastName; // return stored last name
	} // end getLastName

	public void setLastName(String lastName) { // write last name
		this.lastName = lastName; // save last name
	} // end setLastName

	public String getEmail() { // read email
		return email; // return stored email
	} // end getEmail

	public void setEmail(String email) { // write email
		this.email = email; // save email
	} // end setEmail

	public String getDepartment() { // read department
		return department; // return stored department
	} // end getDepartment

	public void setDepartment(String department) { // write department
		this.department = department; // save department
	} // end setDepartment

	public String getJobTitle() { // read job title
		return jobTitle; // return stored job title
	} // end getJobTitle

	public void setJobTitle(String jobTitle) { // write job title
		this.jobTitle = jobTitle; // save job title
	} // end setJobTitle
} // end class

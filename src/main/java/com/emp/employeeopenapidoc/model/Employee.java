package com.emp.employeeopenapidoc.model; // folder for the person shape

import io.swagger.v3.oas.annotations.media.Schema; // sample text in Swagger
import jakarta.persistence.Column; // extra rules for one column
import jakarta.persistence.Entity; // this class is a database table
import jakarta.persistence.GeneratedValue; // number is created for us
import jakarta.persistence.GenerationType; // how the number is created
import jakarta.persistence.Id; // this field is the unique number
import jakarta.persistence.Table; // name of the table
import jakarta.validation.constraints.Email; // must look like an email
import jakarta.validation.constraints.NotBlank; // cannot be empty
import jakarta.validation.constraints.Size; // cannot be too long

@Entity // save this as a table
@Table(name = "employees") // table is called employees
@Schema(description = "Employee") // Swagger calls this an Employee
public class Employee { // one person at work

	@Id // unique number for this person
	@GeneratedValue(strategy = GenerationType.IDENTITY) // MySQL picks the next number
	@Schema(example = "1", accessMode = Schema.AccessMode.READ_ONLY) // you don't type this when adding someone
	private Long id; // person's number

	@NotBlank // first name is required
	@Size(max = 50) // first name up to 50 letters
	@Schema(example = "Priya") // sample first name in Swagger
	private String firstName; // first name

	@NotBlank // last name is required
	@Size(max = 50) // last name up to 50 letters
	@Schema(example = "Sharma") // sample last name in Swagger
	private String lastName; // last name

	@NotBlank // email is required
	@Email // must be a real-looking email
	@Column(unique = true) // two people cannot share an email
	@Schema(example = "priya.sharma@example.com") // sample email in Swagger
	private String email; // email address

	@NotBlank // department is required
	@Size(max = 80) // department up to 80 letters
	@Schema(example = "Engineering") // sample department in Swagger
	private String department; // which team

	@Size(max = 80) // job title is optional, up to 80 letters
	@Schema(example = "Software Engineer") // sample job title in Swagger
	private String jobTitle; // job title

	public Long getId() { // give back the number
		return id; // the number
	} // done

	public void setId(Long id) { // change the number
		this.id = id; // store the number
	} // done

	public String getFirstName() { // give back first name
		return firstName; // the first name
	} // done

	public void setFirstName(String firstName) { // change first name
		this.firstName = firstName; // store first name
	} // done

	public String getLastName() { // give back last name
		return lastName; // the last name
	} // done

	public void setLastName(String lastName) { // change last name
		this.lastName = lastName; // store last name
	} // done

	public String getEmail() { // give back email
		return email; // the email
	} // done

	public void setEmail(String email) { // change email
		this.email = email; // store email
	} // done

	public String getDepartment() { // give back department
		return department; // the department
	} // done

	public void setDepartment(String department) { // change department
		this.department = department; // store department
	} // done

	public String getJobTitle() { // give back job title
		return jobTitle; // the job title
	} // done

	public void setJobTitle(String jobTitle) { // change job title
		this.jobTitle = jobTitle; // store job title
	} // done
} // end of file

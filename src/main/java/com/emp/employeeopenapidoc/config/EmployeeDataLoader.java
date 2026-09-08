package com.emp.employeeopenapidoc.config; // settings folder

import com.emp.employeeopenapidoc.model.Employee; // one person record
import com.emp.employeeopenapidoc.service.EmployeeService; // used to save people
import org.springframework.boot.CommandLineRunner; // run extra work after the app starts
import org.springframework.context.annotation.Profile; // turn this on or off
import org.springframework.stereotype.Component; // Spring should create this

@Component // Spring will run this class
@Profile("!test") // do not add sample people while tests are running
public class EmployeeDataLoader implements CommandLineRunner { // add sample data after start

	private final EmployeeService employeeService; // saver we will use

	public EmployeeDataLoader(EmployeeService employeeService) { // Spring hands us the saver
		this.employeeService = employeeService; // remember it
	} // constructor done

	@Override
	public void run(String... args) { // runs once when the app is up
		employeeService.create(employee("Priya", "Sharma", "priya.sharma@example.com", "Engineering", "Software Engineer")); // add Priya
		employeeService.create(employee("Arjun", "Patel", "arjun.patel@example.com", "Engineering", "Compiler Engineer")); // add Arjun
		employeeService.create(employee("Ananya", "Reddy", "ananya.reddy@example.com", "Product", "Product Manager")); // add Ananya
		employeeService.create(employee("Rohan", "Iyer", "rohan.iyer@example.com", "Engineering", "Backend Developer")); // add Rohan
		employeeService.create(employee("Meera", "Nair", "meera.nair@example.com", "Design", "UX Designer")); // add Meera
		employeeService.create(employee("Vikram", "Singh", "vikram.singh@example.com", "Operations", "DevOps Engineer")); // add Vikram
		employeeService.create(employee("Kavya", "Gupta", "kavya.gupta@example.com", "People", "HR Specialist")); // add Kavya
	} // all sample people added

	private Employee employee(String firstName, String lastName, String email, String department, String jobTitle) { // fill one person
		Employee employee = new Employee(); // empty person
		employee.setFirstName(firstName); // put first name
		employee.setLastName(lastName); // put last name
		employee.setEmail(email); // put email
		employee.setDepartment(department); // put department
		employee.setJobTitle(jobTitle); // put job title
		return employee; // ready to save
	} // helper done
} // end of file

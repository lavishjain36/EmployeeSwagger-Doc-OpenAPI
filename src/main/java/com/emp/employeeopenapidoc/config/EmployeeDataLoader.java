package com.emp.employeeopenapidoc.config; // config package

import com.emp.employeeopenapidoc.model.Employee; // employee row
import com.emp.employeeopenapidoc.service.EmployeeService; // create API
import org.springframework.boot.CommandLineRunner; // runs after startup
import org.springframework.context.annotation.Profile; // on/off by profile
import org.springframework.stereotype.Component; // Spring bean

@Component // Spring creates this class
@Profile("!test") // skip when profile is test
public class EmployeeDataLoader implements CommandLineRunner { // run() after boot

	private final EmployeeService employeeService; // used to INSERT rows

	public EmployeeDataLoader(EmployeeService employeeService) { // Spring injects service
		this.employeeService = employeeService; // store for run()
	} // end constructor

	@Override
	public void run(String... args) { // called once at startup
		employeeService.create(employee("Priya", "Sharma", "priya.sharma@example.com", "Engineering", "Software Engineer")); // sample 1
		employeeService.create(employee("Arjun", "Patel", "arjun.patel@example.com", "Engineering", "Compiler Engineer")); // sample 2
		employeeService.create(employee("Ananya", "Reddy", "ananya.reddy@example.com", "Product", "Product Manager")); // sample 3
		employeeService.create(employee("Rohan", "Iyer", "rohan.iyer@example.com", "Engineering", "Backend Developer")); // sample 4
		employeeService.create(employee("Meera", "Nair", "meera.nair@example.com", "Design", "UX Designer")); // sample 5
		employeeService.create(employee("Vikram", "Singh", "vikram.singh@example.com", "Operations", "DevOps Engineer")); // sample 6
		employeeService.create(employee("Kavya", "Gupta", "kavya.gupta@example.com", "People", "HR Specialist")); // sample 7
	} // end run

	private Employee employee(String firstName, String lastName, String email, String department, String jobTitle) { // build one Employee
		Employee employee = new Employee(); // empty object
		employee.setFirstName(firstName); // set first name
		employee.setLastName(lastName); // set last name
		employee.setEmail(email); // set email
		employee.setDepartment(department); // set department
		employee.setJobTitle(jobTitle); // set job title
		return employee; // give back to create()
	} // end employee helper
} // end class

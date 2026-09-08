package com.emp.employeeopenapidoc; // root package of the app

import org.springframework.boot.SpringApplication; // helper that starts Spring
import org.springframework.boot.autoconfigure.SpringBootApplication; // turns on auto-config

@SpringBootApplication // scan beans, start Tomcat, load JPA
public class EmployeeopenapidocApplication { // class you Run in the IDE

	public static void main(String[] args) { // JVM entry point
		SpringApplication.run(EmployeeopenapidocApplication.class, args); // start the API
	} // end main
} // end class

package com.emp.employeeopenapidoc; // home folder for the whole app

import org.springframework.boot.SpringApplication; // tool that starts the app
import org.springframework.boot.autoconfigure.SpringBootApplication; // turns on the usual Spring setup

@SpringBootApplication // start the website and connect everything
public class EmployeeopenapidocApplication { // the class you click Run on

	public static void main(String[] args) { // where the program begins
		SpringApplication.run(EmployeeopenapidocApplication.class, args); // turn the API on
	} // finished starting
} // end of file

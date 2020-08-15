package test;

import org.apache.commons.text.RandomStringGenerator;

//Create a seperate file for creating a new user with unique name and email

public class freshUser {

	String password = "11111111";
	
	String firstName = new RandomStringGenerator.Builder().withinRange('a', 'z').build().generate(4);
	String lastName = new RandomStringGenerator.Builder().withinRange('a', 'z').build().generate(4);

	String fullName = firstName + "_" + lastName;
	String email = firstName + "_" + lastName + "@yahoo.com";
	
}

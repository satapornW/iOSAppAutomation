package test;

import static org.apache.commons.text.CharacterPredicates.DIGITS;
import static org.apache.commons.text.CharacterPredicates.LETTERS;

import org.apache.commons.text.RandomStringGenerator;

public class freshUser {

	String password = "11111111";
	
	String firstName = new RandomStringGenerator.Builder().withinRange('a', 'z').build().generate(4);
	String lastName = new RandomStringGenerator.Builder().withinRange('a', 'z').build().generate(4);

	String fullName = firstName + "_" + lastName;
	String email = firstName + "_" + lastName + "@yahoo.com";
	
	
	
	 
}

package com.capgemini.addressbook;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class AddressBookJSONTest {

	@Test
	public void given3ContactsWhenWrittenToCSVFileShouldMatchContactEntries() throws IOException {
		ContactDetails jeff=new ContactDetails("Megha", "Sarena", "pill", "Miami", "California",234876, 654321789, "megha@gmail.com");
		ContactDetails mark=new ContactDetails("Krish", "Malhotra", "Chandini chowk", "Delhi", "Delhi", 543762, 987456320, "krish@gmail.com");
		ContactDetails satya=new ContactDetails("Satya", "Srujana", "bhel", "hyderabad", "Telangana", 432876, 908761790, "satyasrujana@gmail.com");
		List<ContactDetails> contactDetailsList=Arrays.asList(new ContactDetails[] {jeff, mark, satya});
		AddressBookJSON addressBookCSV=new AddressBookJSON();
		addressBookCSV.writeJSON(contactDetailsList);
		int readContacts=addressBookCSV.readJSON();
		Assert.assertEquals(3,readContacts);	
	}
}

package com.capgemini.addressbook;

import java.util.Arrays;
import java.util.List;
//import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;

public class AddressBookCSVTest 
{
	private final static String ADDRESS_BOOK_CSV = "addressbookcsv.csv";
	@Test
	public void given3ContactsWhenWrittenToCSVFileShouldMatchContactEntries() {
		ContactDetails jeff=new ContactDetails("Megha", "Sarena", "pill", "Miami", "California",234876, 654321789, "megha@gmail.com");
		ContactDetails mark=new ContactDetails("Krish", "Malhotra", "Chandini chowk", "Delhi", "Delhi", 543762, 987456320, "krish@gmail.com");
		ContactDetails satya=new ContactDetails("Satya", "Srujana", "bhel", "hyderabad", "Telangana", 432876, 908761790, "satyasrujana@gmail.com");
		List<ContactDetails> contactDetailsList=Arrays.asList(new ContactDetails[] {jeff, mark, satya});
		AddressBookCSV addressBookCSV=new AddressBookCSV();
		addressBookCSV.writeContactDetails(contactDetailsList);
		List<ContactDetails> readContacts=addressBookCSV.readContactDetails();
		System.out.println(readContacts);
		Assert.assertEquals(jeff.toString(),readContacts.get(0).toString());
		Assert.assertEquals(mark.toString(),readContacts.get(1).toString());
		Assert.assertEquals(satya.toString(),readContacts.get(2).toString());
	
	}
	
}


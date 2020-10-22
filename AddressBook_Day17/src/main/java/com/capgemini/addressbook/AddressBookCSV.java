package com.capgemini.addressbook;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

public class AddressBookCSV {

	private final static String ADDRESS_BOOK_CSV = "addressbookcsv.csv";

	public void writeContactDetails(List<ContactDetails> contactDetailsList) {

		try (Writer writer = Files.newBufferedWriter(Paths.get(ADDRESS_BOOK_CSV));) {
			StatefulBeanToCsv<ContactDetails> beanToCSV = new StatefulBeanToCsvBuilder(writer)
					.withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).build();
			beanToCSV.write(contactDetailsList);
		} catch (IOException | CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
			System.out.println(e.getMessage());
		}
	}

	public List<ContactDetails> readContactDetails() {
		List<ContactDetails> contactsList = null;
		try (Reader reader = Files.newBufferedReader(Paths.get(ADDRESS_BOOK_CSV));) {
			CSVReader csvReader = new CSVReader(reader);
			List<String[]> contactStrings = csvReader.readAll();
			contactStrings.remove(0);
			contactsList = contactStrings.stream().map(contact -> {
				String address = contact[0];
				String city = contact[1];
				String email = contact[2];
				String firstName = contact[3];
				String lastName = contact[4];
				long phoneNumber = Long.parseLong(contact[5]);
				String state = contact[6];
				int zip = Integer.parseInt(contact[7]);
				return new ContactDetails(firstName, lastName, address, city, state, zip, phoneNumber, email);
			}).collect(Collectors.toList());
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return contactsList;

	}
}

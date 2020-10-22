package com.capgemini.addressbook;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class AddressBookMain {
	static Scanner sc = new Scanner(System.in);
	private static LinkedList<ContactDetails> contactDetailsList;

	private AddressBookMain() {
		contactDetailsList = new LinkedList<>();
	}

	/**
	 * 
	 * @param addressBookNum
	 */

	private void addContact(int addressBookNum) {
		System.out.println("How many entries you want to make in Address Book " + addressBookNum);
		int numOfEntries = sc.nextInt();
		sc.nextLine();
		for (int i = 0; i < numOfEntries; i++) {
			String firstName, lastName;
			int flag = 0;
			do {
				int counter = 0;
				System.out.println("First Name: ");
				firstName = sc.nextLine();
				System.out.println("Last Name: ");
				lastName = sc.nextLine();
				final String first = firstName;
				final String last = lastName;
				if (contactDetailsList.stream().anyMatch(n -> n.getFirstName().equals(first))
						&& contactDetailsList.stream().anyMatch(n -> n.getLastName().equals(last))) {
					counter++;
				}
				if (counter != 0) {
					System.out.println("This name already exists! Please enter again");
					flag = 0;
				} else
					flag = 1;
			} while (flag == 0);
			System.out.println("Address: ");
			String address = sc.nextLine();
			System.out.println("City: ");
			String city = sc.nextLine();
			System.out.println("State: ");
			String state = sc.nextLine();
			System.out.println("ZIP: ");
			int zip = sc.nextInt();
			System.out.println("Phone No: ");
			long phoneNo = sc.nextLong();
			sc.nextLine();
			System.out.println("Email ID: ");
			String emailId = sc.nextLine();
			ContactDetails contactDetail = new ContactDetails(firstName, lastName, address, state, city, zip, phoneNo,
					emailId);
			contactDetailsList.add(contactDetail);
		}
	}

	/**
	 * 
	 * @param addressBookMap
	 */
	private static void editContact(Map<String, AddressBookMain> addressBookMap) {
		sc.nextLine();
		System.out.println("First Name of the person whose record is to be edited: ");
		String firstName = sc.nextLine();
		System.out.println("Last Name of the person whose record is to be edited: ");
		String lastName = sc.nextLine();
		System.out.println("New Address: ");
		String address = sc.nextLine();
		System.out.println("New City: ");
		String city = sc.nextLine();
		System.out.println("New State: ");
		String state = sc.nextLine();
		System.out.println("New ZIP: ");
		int zip = sc.nextInt();
		System.out.println("New Phone No: ");
		long phoneNo = sc.nextLong();
		sc.nextLine();
		System.out.println("Edited Email ID: ");
		String emailId = sc.nextLine();
		for (Map.Entry<String, AddressBookMain> entry : addressBookMap.entrySet()) {
			AddressBookMain value = entry.getValue();
			for (int i = 0; i < value.contactDetailsList.size(); i++)
				if (value.contactDetailsList.get(i).firstName.equals(firstName)
						&& value.contactDetailsList.get(i).lastName.equals(lastName)) {
					ContactDetails contactDetails = new ContactDetails(firstName, lastName, address, city, state, zip,
							phoneNo, emailId);
					value.contactDetailsList.set(i, contactDetails);
					System.out.println("Edited the contact");
				}
		}
	}

	/**
	 * 
	 * @param addressBookMap
	 */
	private static void searchContactDetails(Map<String, AddressBookMain> addressBookMap) {
		sc.nextLine();
		System.out.println("Enter First Name of person whose record is to be searched: ");
		String firstName = sc.nextLine();
		System.out.println("Enter Last Name of person whose record is to be searched: ");
		String lastName = sc.nextLine();
		int flag = 0;
		for (Map.Entry<String, AddressBookMain> entry : addressBookMap.entrySet()) {
			AddressBookMain value = entry.getValue();
			for (int i = 0; i < value.contactDetailsList.size(); i++)
				if (value.contactDetailsList.get(i).firstName.equals(firstName)
						&& value.contactDetailsList.get(i).lastName.equals(lastName)) {
					System.out.println(value.contactDetailsList.get(i));
					flag = 1;
					break;
				}
		}
		if (flag == 0) {
			System.out.println("No such record found");
		}
	}

	/**
	 * 
	 * @param addressBookMap
	 */

	public static void deleteContactDetails(Map<String, AddressBookMain> addressBookMap) {
		sc.nextLine();
		System.out.println("Enter First Name of person whose record is to be deleted: ");
		String firstName = sc.nextLine();
		System.out.println("Enter Last Name of person whose record is to be deleted: ");
		String lastName = sc.nextLine();
		int flag = 0;
		for (Map.Entry<String, AddressBookMain> entry : addressBookMap.entrySet()) {
			AddressBookMain value = entry.getValue();
			for (int i = 0; i < value.contactDetailsList.size(); i++)
				if (value.contactDetailsList.get(i).firstName.equals(firstName)
						&& value.contactDetailsList.get(i).lastName.equals(lastName)) {
					value.contactDetailsList.remove(i);
					System.out.println("Deleted Contact");
					flag = 1;
				}
		}
		if (flag == 0) {
			System.out.println("No such record found");
		}

	}

	/**
	 * 
	 * @param addressBookMap
	 */
	private static void displayContactDetails(Map<String, AddressBookMain> addressBookMap) {
		for (Map.Entry<String, AddressBookMain> entry : addressBookMap.entrySet()) {
			AddressBookMain value = entry.getValue();
			for (int i = 0; i < value.contactDetailsList.size(); i++)
				System.out.println(value.contactDetailsList.get(i));
		}
	}

	/**
	 * 
	 * @param addressBookMap
	 * @param city
	 */
	private static void searchByCity(Map<String, AddressBookMain> addressBookMap, String city) {
		int count = 0;
		for (Map.Entry<String, AddressBookMain> entry : addressBookMap.entrySet()) {
			AddressBookMain value = entry.getValue();
			for (int i = 0; i < value.contactDetailsList.size(); i++) {
				if (value.contactDetailsList.stream().anyMatch(n -> n.getCity().equals(city))) {
					System.out.println(value.contactDetailsList.get(i));
					count++;
				}
			}
		}
		System.out.println("Count in this city is " + count);
	}

	/**
	 * 
	 * @param addressBookMap
	 * @param state
	 */

	private static void searchByState(Map<String, AddressBookMain> addressBookMap, String state) {
		int count = 0;
		for (Map.Entry<String, AddressBookMain> entry : addressBookMap.entrySet()) {
			AddressBookMain value = entry.getValue();
			for (int i = 0; i < value.contactDetailsList.size(); i++) {
				if (value.contactDetailsList.stream().anyMatch(n -> n.getState().equals(state))) {
					System.out.println(value.contactDetailsList.get(i));
					count++;
				}
			}
		}
		System.out.println("Count in the state " + state + " is " + count);
	}

	/**
	 * 
	 * @param addressBookMap
	 */
	private static void groupByCity(Map<String, AddressBookMain> addressBookMap) {
		for (Map.Entry<String, AddressBookMain> entry : addressBookMap.entrySet()) {
			AddressBookMain value = entry.getValue();
			for (int i = 0; i < value.contactDetailsList.size(); i++) {
				Set<String> cityList = value.contactDetailsList.stream().map(contact -> contact.getCity())
						.collect(Collectors.toSet());
				for (String cityName : cityList) {
					System.out.println("Contact Entries for CITY: " + cityName);
					searchByCity(addressBookMap, cityName);
				}
			}
		}
	}

	/**
	 * 
	 * @param addressBookMap
	 */
	private static void groupByState(Map<String, AddressBookMain> addressBookMap) {
		for (Map.Entry<String, AddressBookMain> entry : addressBookMap.entrySet()) {
			AddressBookMain value = entry.getValue();
			for (int i = 0; i < value.contactDetailsList.size(); i++) {
				Set<String> stateList = value.contactDetailsList.stream().map(contact -> contact.getState())
						.collect(Collectors.toSet());
				for (String stateName : stateList) {
					System.out.println("Contact Entries for STATE: " + stateName);
					searchByCity(addressBookMap, stateName);
				}
			}
		}
	}

	/**
	 * 
	 * @param addressBookMap
	 */
	public static void sortByFirstName(Map<String, AddressBookMain> addressBookMap) {
		List<ContactDetails> unsortedList = null;
		for (Map.Entry<String, AddressBookMain> entry : addressBookMap.entrySet()) {
			AddressBookMain value = entry.getValue();
			for (int i = 0; i < value.contactDetailsList.size(); i++) {
				unsortedList = contactDetailsList;
			}
		}
		List<ContactDetails> sortedList = unsortedList.stream()
				.sorted(Comparator.comparing(ContactDetails::getFirstName)).collect(Collectors.toList());
		System.out.println(sortedList);
	}

	/**
	 * 
	 * @param addressBookMap
	 */
	public static void sortByCity(Map<String, AddressBookMain> addressBookMap) {
		List<ContactDetails> unsortedList = null;
		for (Map.Entry<String, AddressBookMain> entry : addressBookMap.entrySet()) {
			AddressBookMain value = entry.getValue();
			for (int i = 0; i < value.contactDetailsList.size(); i++) {
				unsortedList = contactDetailsList;
			}
		}
		List<ContactDetails> sortedList = unsortedList.stream().sorted(Comparator.comparing(ContactDetails::getCity))
				.collect(Collectors.toList());
		System.out.println(sortedList);
	}

	/**
	 * 
	 * @param addressBookMap
	 */
	public static void sortByState(Map<String, AddressBookMain> addressBookMap) {
		List<ContactDetails> unsortedList = null;
		for (Map.Entry<String, AddressBookMain> entry : addressBookMap.entrySet()) {
			AddressBookMain value = entry.getValue();
			for (int i = 0; i < value.contactDetailsList.size(); i++) {
				unsortedList = contactDetailsList;
			}
		}
		List<ContactDetails> sortedList = unsortedList.stream().sorted(Comparator.comparing(ContactDetails::getState))
				.collect(Collectors.toList());
		System.out.println(sortedList);
	}

	/**
	 * 
	 * @param addressBookMap
	 */
	public static void sortByZip(Map<String, AddressBookMain> addressBookMap) {
		List<ContactDetails> unsortedList = null;
		for (Map.Entry<String, AddressBookMain> entry : addressBookMap.entrySet()) {
			AddressBookMain value = entry.getValue();
			for (int i = 0; i < value.contactDetailsList.size(); i++) {
				unsortedList = contactDetailsList;
			}
		}
		List<ContactDetails> sortedList = unsortedList.stream().sorted(Comparator.comparing(ContactDetails::getZip))
				.collect(Collectors.toList());
		System.out.println(sortedList);
	}

	public static void main(String[] args) {
		Map<String, AddressBookMain> addressBookMap = new HashMap<>();
		System.out.println("How many address books should be created? ");
		int noOfAddressBooks = sc.nextInt();
		sc.nextLine();
		AddressBookMain[] addressBookArray = new AddressBookMain[noOfAddressBooks];
		for (int i = 0; i < noOfAddressBooks; i++) {
			System.out.println("Enter name for Address Book " + (i + 1) + ": ");
			String addressBookName = sc.nextLine();
			addressBookArray[i] = new AddressBookMain();
			addressBookArray[i].addContact(i + 1);
			addressBookMap.put(addressBookName, addressBookArray[i]);
		}
		int i = 1;
		while (i == 1) {
			System.out.println("Choose an option ");
			System.out.println("1. Edit Contact ");
			System.out.println("2. Delete Contact ");
			System.out.println("3. Search Contact ");
			System.out.println("4. Display Contact ");
			System.out.println("5. Search Contact by city ");
			System.out.println("6. Search Contact by state ");
			System.out.println("7. List by city ");
			System.out.println("8. List by state ");
			System.out.println("9. Sort By First Name");
			System.out.println("10. Sort By City");
			System.out.println("11. Sort By State");
			System.out.println("12. Sort By ZIP");
			System.out.println("13. Exit ");
			int option = sc.nextInt();
			switch (option) {
			case 1:
				editContact(addressBookMap);
				break;
			case 2:
				deleteContactDetails(addressBookMap);
				break;
			case 3:
				searchContactDetails(addressBookMap);
				break;
			case 4:
				displayContactDetails(addressBookMap);
				break;
			case 5:
				System.out.println("Enter city name");
				String city = sc.nextLine();
				searchByCity(addressBookMap, city);
				break;
			case 6:
				System.out.println("Enter state name");
				String state = sc.nextLine();
				searchByState(addressBookMap, state);
				break;
			case 7:
				groupByCity(addressBookMap);
				break;
			case 8:
				groupByState(addressBookMap);
				break;
			case 9:
				sortByFirstName(addressBookMap);
				break;
			case 10:
				sortByCity(addressBookMap);
				break;
			case 11:
				sortByState(addressBookMap);
				break;
			case 12:
				sortByZip(addressBookMap);
				break;
			default:
				i = 0;
			}
		}

	}
}
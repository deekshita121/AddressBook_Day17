package com.capgemini.addressbook;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class AddressBookJSON {

	public int writeJSON(List<ContactDetails> contactDetailsList) throws IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(contactDetailsList);
		FileWriter writer = new FileWriter("AddressBookJson.json");
		writer.write(json);
		writer.close();
		return contactDetailsList.size();
	}
	
	public int readJSON() throws IOException {
		int count = 0;
		Reader reader = Files.newBufferedReader(Paths.get("AddressBookJson.json"));
		List<ContactDetails> users = new Gson().fromJson(reader, new TypeToken<List<ContactDetails>>() {
		}.getType());
		for (ContactDetails user : users) {
			System.out.println(user);
			count++;
		}
		return count;
	}
}

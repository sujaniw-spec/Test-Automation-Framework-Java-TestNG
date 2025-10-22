package com.utilitiy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.ui.pojo.User;

public class CSVReaderUtility {

	public static Iterator<User> readCSV(String fileName) {
		
		File csvFile = new File(System.getProperty("user.dir") + "\\TestData\\"+fileName);
		FileReader reader;
		CSVReader csvReader;
		String [] line;
		List<User> userList = null;
		User userData;					
					
			try {
				reader = new FileReader(csvFile);
				
				csvReader = new CSVReader(reader);	
				csvReader.readNext(); //reading the imediate line (line1) headers. If no row return null
				userList = new ArrayList<User>();				
				
				while((line = csvReader.readNext()) != null){
					userData = new User(line[0],line[1]);
					userList.add(userData);
				}
				
				for(User user: userList) {
					System.out.println(user);
				}
			} catch (CsvValidationException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			return userList.iterator();
	}
}

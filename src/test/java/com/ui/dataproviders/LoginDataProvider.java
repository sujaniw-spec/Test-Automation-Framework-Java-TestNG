package com.ui.dataproviders;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.google.gson.Gson;
import com.ui.pojo.TestData;
import com.ui.pojo.User;
import com.utilitiy.CSVReaderUtility;
import com.utilitiy.XSLReaderUtility;

public class LoginDataProvider {
	
	@DataProvider(name="LoginTestDataProvider")
	public Iterator<Object[]> loginDataProvider() {
		Gson gson = new Gson();
		//File testDataFile = new File(System.getProperty("user.dir") + "\\TestData\\LoginData.json");
		File testDataFile = new File("TestData//LoginData.json");
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(testDataFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TestData data = gson.fromJson(fileReader, TestData.class); //deserilization
		
		List <Object[]> dataToReturn = new ArrayList<Object[]>();
		
		for(User user: data.getData()) {
			dataToReturn.add(new Object[] {user});
		}
		
		return dataToReturn.iterator()
		;
	}
	
	@DataProvider(name ="loginCSVDataProvider")
	public Iterator<User> loginCSVDataProvider() {
		return CSVReaderUtility.readCSV("LoginData.csv");
	}
	
	@DataProvider(name = "loginXlsxDataProvider")
	public Iterator<User> loginXlsxDataProvider(){
		return XSLReaderUtility.readXlsx("LoginData.xlsx");
	}

}

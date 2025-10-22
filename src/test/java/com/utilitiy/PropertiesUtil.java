package com.utilitiy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import com.constants.Env;

public class PropertiesUtil {
//read properties file
	
	public static String readProperites(Env env, String propertyName) {
		
		File propFile = new File(System.getProperty("user.dir")+"\\config\\"+env+".properties");//Project stating path
		FileReader fileReader;
		Properties properties = new Properties();
		
		try {
			fileReader = new FileReader(propFile);
			properties.load(fileReader);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return properties.getProperty(propertyName.toUpperCase());
		
	}
}

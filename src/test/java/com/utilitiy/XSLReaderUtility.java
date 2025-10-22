package com.utilitiy;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ui.pojo.User;

public class XSLReaderUtility {

	public static Iterator<User> readXlsx(String fileName) {
		
		File xlsxFile = new File(System.getProperty("user.dir") + "\\TestData\\LoginData.xlsx");

		//XLSX file
		XSSFWorkbook xssfWorkbook;
		XSSFSheet xssfSheet;
		List<User> listOfusers = null;
		Row row;
		Cell emailCell;
		Cell passwordCell;
		try {
		 xssfWorkbook = new XSSFWorkbook(xlsxFile);
		 xssfSheet = xssfWorkbook.getSheet("LoginData");
		 listOfusers = new ArrayList<User>();	
		 
		Iterator <Row> rowIterator = xssfSheet.iterator();
		rowIterator.next();	
		while(rowIterator.hasNext())
		{
			 row = rowIterator.next();
			
			emailCell = row.getCell(0);
			passwordCell = row.getCell(1);
			User user = new User(emailCell.toString(),passwordCell.toString());
			listOfusers.add(user);			
		}
		
		xssfWorkbook.close();
		
		} catch (InvalidFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listOfusers.iterator();
		
	   
	}

}

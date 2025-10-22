package com.ui.listerners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.constants.Env;
import com.utilitiy.JSONUtility;
import com.utilitiy.PropertiesUtil;

public class MyRetryAnalyzer implements IRetryAnalyzer{

	private static final int MAX_NUMBER_OF_ATTEMPS =JSONUtility.readJSON(Env.QA).getMAX_NUMBER_OF_ATTEMPS();
			//Integer.parseInt(
			//PropertiesUtil.readProperites(Env.DEV, "MAX_NUMBER_OF_ATTEMPS"));
	private static int currentAttempt = 1;
	
	@Override
	public boolean retry(ITestResult result) {
		if(currentAttempt <= MAX_NUMBER_OF_ATTEMPS) {
			currentAttempt++;
			return true;
		}
		return false;
	}

}

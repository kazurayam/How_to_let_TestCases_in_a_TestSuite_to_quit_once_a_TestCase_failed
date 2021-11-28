package com.kazurayam.ks.testsuite

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable

public class ProgressEntry {

	private static String testCaseName
	private static String testCaseId
	private static Map<String, String> bindedValues
	private static Boolean passed     // TestCaseStatus:"PASSED" -> true, "FAILED" -> false

	ProgressEntry(String testCaseName, String testCaseId, Map<String, String> bindedValues) {
		this.testCaseName = testCaseName
		this.testCaseId = testCaseId
		this.bindedValues = bindedValues
		this.passed = true
	}

	ProgressEntry(String testCaseName, String testCaseId) {
		this(testCaseName, testCaseId, null)
	}

	void setPassed(boolean passed) {
		this.passed = passed
	}

	boolean isPassed() {
		return this.passed
	}

}

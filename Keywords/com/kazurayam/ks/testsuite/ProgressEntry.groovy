package com.kazurayam.ks.testsuite

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

package com.kazurayam.ks.testsuite

import java.nio.file.Files
import java.nio.file.Path

import com.kazurayam.ks.globalvariable.ExpandoGlobalVariable
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.context.TestCaseContext
import com.kms.katalon.core.util.KeywordUtil

import groovy.json.JsonSlurper


public class Advisor {

	public static final String PROPERTY_NAME = "test_suite_progress"

	private List<ProgressEntry> entries

	@Keyword
	static boolean shouldQuit() {
		Advisor progress = ExpandoGlobalVariable.getPropertyValue(Advisor.PROPERTY_NAME)
		assert progress != null
		boolean oneOrMoreFailures = progress.oneOrMorePreviousTestCasesHaveFailed()
		if (oneOrMoreFailures) {
			KeywordUtil.markWarning("should quit")
		}
		return oneOrMoreFailures
	}

	Advisor() {
		this.entries = new ArrayList<ProgressEntry>()
	}

	Advisor(Path testCaseBinding) {
		this.entries = loadFile(testCaseBinding)
	}

	private List<ProgressEntry> loadFile(Path testCaseBinding) {
		Objects.requireNonNull(testCaseBinding)
		assert Files.exists(testCaseBinding)
		List<ProgressEntry> entries = new ArrayList<ProgressEntry>()
		testCaseBinding.toFile().eachLine { String line ->
			JsonSlurper slurper = new JsonSlurper()
			Map object = slurper.parseText(line)
			ProgressEntry entry
			if (object.bindedValues) {
				entry = new ProgressEntry(object.testCaseName, object.testCaseId)
			} else {
				entry = new ProgressEntry(object.testCaseName, object.testCaseId, object.bindedValues)
			}
			entries.add(entry)
		}
		return entries
	}

	void afterTestCase(int index, TestCaseContext testCaseContext) {
		if (index < entries.size()) {
			if (testCaseContext.getTestCaseStatus() == "FAILED") {
				this.entries.get(index).setPassed(false)
			} else {
				this.entries.get(index).setPassed(true)
			}
		} else {
			println "index=${index} exceeds the size of entries=${entries.size()}"
		}
	}

	boolean oneOrMorePreviousTestCasesHaveFailed() {
		return (this.entries.stream()
				.filter({ !it.isPassed() })
				.collect()
				.size() > 0)
	}
}

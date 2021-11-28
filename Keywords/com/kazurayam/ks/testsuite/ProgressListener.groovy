package com.kazurayam.ks.testsuite

import java.nio.file.Path
import java.nio.file.Paths

import com.kazurayam.ks.globalvariable.ExpandoGlobalVariable
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.context.TestCaseContext
import com.kms.katalon.core.context.TestSuiteContext

public class ProgressListener {

	private int index

	ProgressListener() {
		index = 0
	}

	def beforeTestSuite(TestSuiteContext testSuiteContext) {
		Path bunchDir = Paths.get(RunConfiguration.getReportFolder())
		Path testCaseBinding = bunchDir.resolve("testCaseBinding")
		ExpandoGlobalVariable.addProperty(Advisor.PROPERTY_NAME, new Advisor(testCaseBinding))
		this.index = 0
	}

	def afterTestCase(TestCaseContext testCaseContext) {
		Advisor progress = ExpandoGlobalVariable.getPropertyValue(Advisor.PROPERTY_NAME)
		progress.afterTestCase(index, testCaseContext)
		index += 1
	}
}

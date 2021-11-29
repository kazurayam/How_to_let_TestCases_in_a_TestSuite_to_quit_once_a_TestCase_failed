import com.kazurayam.ks.testsuite.ProgressListener

import com.kms.katalon.core.annotation.AfterTestCase
import com.kms.katalon.core.annotation.BeforeTestSuite
import com.kms.katalon.core.context.TestCaseContext
import com.kms.katalon.core.context.TestSuiteContext

class TL1 {

	ProgressListener listener

	TL1() {
		this.listener = new ProgressListener()
	}
	@BeforeTestSuite
	def beforeTestSuite(TestSuiteContext testSuiteContext) {
		listener.beforeTestSuite(testSuiteContext)
	}
	@AfterTestCase
	def afterTestCase(TestCaseContext testCaseContext) {
		listener.afterTestCase(testCaseContext)
	}
}
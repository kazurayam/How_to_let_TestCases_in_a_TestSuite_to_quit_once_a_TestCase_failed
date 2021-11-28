import com.kazurayam.ks.testsuite.Advisor
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

if (Advisor.shouldQuit()) return;

WebUI.comment("TC2 ran")

KeywordUtil.markFailed("TC2 failed")
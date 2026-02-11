import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.BeforeTestCase
import com.kms.katalon.core.annotation.BeforeTestSuite
import com.kms.katalon.core.annotation.AfterTestCase
import com.kms.katalon.core.annotation.AfterTestSuite
import com.kms.katalon.core.context.TestCaseContext
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

class LoginListener {
	@BeforeTestCase
    def beforeTestCase(TestCaseContext tcContext) {

        Map<String, Object> vars = tcContext.getTestCaseVariables()

        String username = vars.get('Username')
        String password = vars.get('Password')

        WebUI.openBrowser('')
        WebUI.maximizeWindow()
        WebUI.navigateToUrl('https://test.salesforce.com/')

        WebUI.setText(findTestObject('LoginPage/Username'), username)
        WebUI.setText(findTestObject('LoginPage/Password'), password)
        WebUI.click(findTestObject('LoginPage/LoginButton'))

        WebUI.waitForPageLoad(30)
        WebUI.comment("Login successful for user: ${username}")
    }

    @AfterTestCase
	
    def afterTestCase(TestCaseContext tcContext) {
        try {
            WebUI.waitForElementClickable(findTestObject('LogoutPage/ProfileIcon'), 5)
            WebUI.click(findTestObject('LogoutPage/ProfileIcon'))

            WebUI.waitForElementClickable(findTestObject('LogoutPage/Logout'), 5)
            WebUI.click(findTestObject('LogoutPage/Logout'))

            WebUI.comment('Logout successful')
        } catch (Exception e) {
            WebUI.comment('Logout skipped or already logged out')
        } finally {
            WebUI.closeBrowser()
        }
    }
}

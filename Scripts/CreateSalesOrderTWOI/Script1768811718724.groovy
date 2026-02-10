import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import internal.GlobalVariable as GlobalVariable

import java.text.SimpleDateFormat

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.checkpoint.Checkpoint


// ==================================================
// APP SELECTION LOGIC
// ==================================================

WebUI.waitForElementVisible(findTestObject('HomePage/ExistingApp'), 20)

String currentApp = WebUI.getText(findTestObject('HomePage/ExistingApp')).trim()
WebUI.comment('Current App selected: ' + currentApp)

String expectedApp = AppName   // e.g. "EAAA CS Console"

if (!currentApp.equalsIgnoreCase(expectedApp)) {

    WebUI.comment('Switching App to: ' + expectedApp)

    WebUI.click(findTestObject('HomePage/AppLauncherButton'))
    WebUI.waitForElementVisible(findTestObject('HomePage/SearchTextbox'), 10)

    WebUI.setText(findTestObject('HomePage/SearchTextbox'), expectedApp)
    WebUI.waitForElementClickable(findTestObject('HomePage/ConsoleAppName'), 15)
    WebUI.click(findTestObject('HomePage/ConsoleAppName'))

    WebUI.comment('App switched successfully to ' + expectedApp)

} else {

    WebUI.comment('Required App already selected. Skipping App switch.')
    WebUI.delay(3)
}


// ==================================================
// ACCOUNT SELECTION
// ==================================================

WebUI.click(findTestObject('HomePage/GlobalSearchTextBox'), FailureHandling.STOP_ON_FAILURE)
WebUI.setText(findTestObject('HomePage/GlobalSearchbox'), AccountName)

WebUI.waitForElementClickable(findTestObject('HomePage/AccountSelection'), 15)
WebUI.click(findTestObject('HomePage/AccountSelection'))

WebUI.delay(3)
WebUI.takeScreenshot()
WebUI.comment('Screenshot captured on Account page')


// ==================================================
// CREATE SALES ORDER
// ==================================================

WebUI.waitForElementClickable(findTestObject('CreateSalesOrderPage/DropdownButton'), 10)
WebUI.click(findTestObject('CreateSalesOrderPage/DropdownButton'))

WebUI.delay(3)

WebUI.waitForElementClickable(findTestObject('CreateSalesOrderPage/CreateSalesOrderTWOI'), 15)
WebUI.click(findTestObject('CreateSalesOrderPage/CreateSalesOrderTWOI'))

WebUI.delay(3)


// ==================================================
// PRICEBOOK DETAILS
// ==================================================

WebUI.waitForElementVisible(findTestObject('CreateSalesOrderPage/PriceBook'), 15)
WebUI.setText(findTestObject('CreateSalesOrderPage/PriceBook'), PricebookName)

WebUI.delay(3)

WebUI.waitForElementClickable(findTestObject('CreateSalesOrderPage/PricebookSelection'), 15)
WebUI.click(findTestObject('CreateSalesOrderPage/PricebookSelection'))

WebUI.delay(3)

WebUI.setText(findTestObject('CreateSalesOrderPage/JobName'), 'Test')
WebUI.delay(3)

String timestamp = new SimpleDateFormat('yyyyMMddHHmmss').format(new Date())
String uniquePONumber = 'PO' + timestamp

WebUI.setText(findTestObject('CreateSalesOrderPage/PoNumber'), uniquePONumber)
WebUI.delay(3)

WebUI.click(findTestObject('CreateSalesOrderPage/MarketSegment'))
WebUI.delay(3)

WebUI.click(findTestObject('CreateSalesOrderPage/MarketSegmentValue'))
WebUI.delay(3)

WebUI.click(findTestObject('CreateSalesOrderPage/MarketCode'))
WebUI.delay(3)

WebUI.click(findTestObject('CreateSalesOrderPage/MarketCodeValue'))
WebUI.delay(3)

WebUI.waitForElementClickable(findTestObject('CreateSalesOrderPage/ETMTerritory'), 15)
WebUI.setText(findTestObject('CreateSalesOrderPage/ETMTerritory'), ETMTerritory)

WebUI.delay(3)

WebUI.waitForElementClickable(findTestObject('CreateSalesOrderPage/ETMTerritorySelection'), 10)
WebUI.click(findTestObject('CreateSalesOrderPage/ETMTerritorySelection'))

WebUI.delay(3)


// ==================================================
// SAVE AS DRAFT
// ==================================================

WebUI.waitForElementClickable(findTestObject('CreateSalesOrderPage/SaveAsDraftButton'), 15)
WebUI.click(findTestObject('CreateSalesOrderPage/SaveAsDraftButton'))

WebUI.delay(3)
WebUI.takeScreenshot()
WebUI.comment('Screenshot captured after selecting Pricebook details')


// ==================================================
// LINE ITEMs DETAILS
// ==================================================

WebUI.waitForElementClickable(findTestObject('CreateSalesOrderPage/LineItemsTab'), 15)
WebUI.click(findTestObject('CreateSalesOrderPage/LineItemsTab'))

WebUI.delay(3)

WebUI.waitForElementVisible(findTestObject('AddProductPage/SearchProduct'), 15)
WebUI.setText(findTestObject('AddProductPage/SearchProduct'), ProductName)

WebUI.delay(3)

WebUI.waitForElementClickable(findTestObject('AddProductPage/ProductSelection'), 20)
WebUI.click(findTestObject('AddProductPage/ProductSelection'))

WebUI.delay(3)

WebUI.setText(findTestObject('AddProductPage/Quantity'), Quantity)
WebUI.delay(3)

WebUI.click(findTestObject('AddProductPage/AddButton'))
WebUI.delay(3)

WebUI.scrollToElement(findTestObject('AddProductPage/OrderList'), 10)
WebUI.delay(3)

WebUI.takeScreenshot()
WebUI.comment('Screenshot captured after selecting Products')


// ==================================================
// SUBMIT & ORDER CREATION
// ==================================================

if (ScenarioType.equalsIgnoreCase('Positive')) {

    WebUI.comment('Positive scenario: Clicking Save Draft before Submit ERP')

    WebUI.waitForElementClickable(findTestObject('AddProductPage/SaveDraft'), 20)
    WebUI.click(findTestObject('AddProductPage/SaveDraft'))

    WebUI.delay(3)

} else {

    WebUI.comment('Negative scenario: Save Draft skipped before Submit ERP')

}

WebUI.waitForElementClickable(findTestObject('AddProductPage/SubmitERP'), 25)
WebUI.click(findTestObject('AddProductPage/SubmitERP'))

WebUI.delay(3)

WebUI.waitForElementClickable(findTestObject('AddProductPage/OrderButton'), 60)
WebUI.click(findTestObject('AddProductPage/OrderButton'))

WebUI.delay(3)
WebUI.takeScreenshot()
WebUI.comment('Screenshot captured after Order is initiated')


// ==================================================
// ORDER VALIDATION
// ==================================================

WebUI.waitForElementVisible(findTestObject('AddProductPage/JDE-SAP-Order'), 20)

String jdeSapOrderNumber = WebUI.getText(findTestObject('AddProductPage/JDE-SAP-Order')).trim()
WebUI.comment('Captured Order Number: ' + jdeSapOrderNumber)

if (jdeSapOrderNumber != null && !jdeSapOrderNumber.isEmpty()) {
    WebUI.comment('Sales Order is placed successfully. JDE Order Number: ' + jdeSapOrderNumber)
} else {
    WebUI.comment('Sales Order is not placed. JDE Order Number was not generated.')
}

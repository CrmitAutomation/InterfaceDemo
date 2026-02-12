import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import internal.GlobalVariable as GlobalVariable
import java.text.SimpleDateFormat as SimpleDateFormat
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint

// ==================================================
// APP SELECTION LOGIC
// ==================================================
WebUI.waitForElementVisible(findTestObject('HomePage/ExistingApp'), 20)

String currentApp = WebUI.getText(findTestObject('HomePage/ExistingApp')).trim()

WebUI.comment('Current App selected: ' + currentApp)

String expectedApp = AppName

if (!(currentApp.equalsIgnoreCase(expectedApp))) {
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
WebUI.waitForElementClickable(findTestObject('AccountPage/DropdownButton'), 10)

WebUI.click(findTestObject('AccountPage/DropdownButton'))

WebUI.delay(3)

WebUI.waitForElementClickable(findTestObject('AccountPage/CreateSalesOrderTWOI'), 15)

WebUI.click(findTestObject('AccountPage/CreateSalesOrderTWOI'))

WebUI.delay(3)

// ==================================================
// PRICEBOOK DETAILS
// ==================================================
WebUI.waitForElementVisible(findTestObject('CreateSalesOrderPage/HeaderTab/PriceBook'), 15)

WebUI.setText(findTestObject('CreateSalesOrderPage/HeaderTab/PriceBook'), PricebookName)

WebUI.delay(5)

WebUI.waitForElementClickable(findTestObject('CreateSalesOrderPage/HeaderTab/PricebookSelection'), 15)

WebUI.click(findTestObject('CreateSalesOrderPage/HeaderTab/PricebookSelection'))

WebUI.delay(3)

WebUI.setText(findTestObject('CreateSalesOrderPage/HeaderTab/JobName'), 'Test')

WebUI.delay(3)

String timestamp = new SimpleDateFormat('yyyyMMddHHmmss').format(new Date())

String uniquePONumber = 'PO' + timestamp

WebUI.setText(findTestObject('CreateSalesOrderPage/HeaderTab/PoNumber'), uniquePONumber)

WebUI.delay(3)

WebUI.click(findTestObject('CreateSalesOrderPage/HeaderTab/MarketSegment'))

WebUI.delay(3)

WebUI.click(findTestObject('CreateSalesOrderPage/HeaderTab/MarketSegmentValue'))

WebUI.delay(3)

WebUI.click(findTestObject('CreateSalesOrderPage/HeaderTab/MarketCode'))

WebUI.delay(3)

WebUI.click(findTestObject('CreateSalesOrderPage/HeaderTab/MarketCodeValue'))

WebUI.delay(3)

WebUI.waitForElementClickable(findTestObject('CreateSalesOrderPage/HeaderTab/ETMTerritory'), 15)

WebUI.setText(findTestObject('CreateSalesOrderPage/HeaderTab/ETMTerritory'), ETMTerritory)

WebUI.delay(3)

WebUI.waitForElementClickable(findTestObject('CreateSalesOrderPage/HeaderTab/ETMTerritorySelection'), 10)

WebUI.click(findTestObject('CreateSalesOrderPage/HeaderTab/ETMTerritorySelection'))

WebUI.delay(3)

WebUI.scrollToElement(findTestObject('CreateSalesOrderPage/HeaderTab/PriceBook'), 2)

// ==================================================
// SAVE AS DRAFT
// ==================================================
WebUI.waitForElementClickable(findTestObject('CreateSalesOrderPage/CommonElements/SaveDraft'), 15)

WebUI.click(findTestObject('CreateSalesOrderPage/CommonElements/SaveDraft'))

WebUI.delay(3)

WebUI.takeScreenshot()

WebUI.comment('Screenshot captured after selecting Pricebook details')

// ==================================================
// LINE ITEMs DETAILS
// ==================================================
WebUI.waitForElementClickable(findTestObject('CreateSalesOrderPage/LineItemTab/LineItemsTab'), 15)

WebUI.click(findTestObject('CreateSalesOrderPage/LineItemTab/LineItemsTab'))

WebUI.delay(3)

WebUI.waitForElementVisible(findTestObject('CreateSalesOrderPage/LineItemTab/AddProductPage/SearchProduct'), 15)

WebUI.setText(findTestObject('CreateSalesOrderPage/LineItemTab/AddProductPage/SearchProduct'), ProductName)

WebUI.delay(5)

WebUI.waitForElementVisible(findTestObject('CreateSalesOrderPage/LineItemTab/AddProductPage/ProductSelection'), 30)

WebUI.click(findTestObject('CreateSalesOrderPage/LineItemTab/AddProductPage/ProductSelection'))

WebUI.delay(3)

WebUI.setText(findTestObject('CreateSalesOrderPage/LineItemTab/AddProductPage/Quantity'), Quantity)

WebUI.delay(3)

WebUI.click(findTestObject('CreateSalesOrderPage/LineItemTab/AddProductPage/AddButton'))

WebUI.delay(3)

WebUI.scrollToElement(findTestObject('CreateSalesOrderPage/LineItemTab/LineItemNotes/OrderList'), 10)

WebUI.delay(3)

WebUI.takeScreenshot()

WebUI.comment('Screenshot captured after selecting Products')

WebUI.click(findTestObject('CreateSalesOrderPage/LineItemTab/LineItemNotes/PreviewButton'))

WebUI.delay(3)

WebUI.waitForElementClickable(findTestObject('CreateSalesOrderPage/LineItemTab/LineItemNotes/NotesTab'), 15)

WebUI.click(findTestObject('CreateSalesOrderPage/LineItemTab/LineItemNotes/NotesTab'))

WebUI.delay(3)

WebUI.waitForElementClickable(findTestObject('CreateSalesOrderPage/LineItemTab/LineItemNotes/NewButton'), 15)

WebUI.click(findTestObject('CreateSalesOrderPage/LineItemTab/LineItemNotes/NewButton'))

WebUI.delay(2)

WebUI.waitForElementClickable(findTestObject('CreateSalesOrderPage/LineItemTab/LineItemNotes/FileName'), 15)

WebUI.setText(findTestObject('CreateSalesOrderPage/LineItemTab/LineItemNotes/FileName'), FileName)

WebUI.delay(2)

WebUI.setText(findTestObject('CreateSalesOrderPage/LineItemTab/LineItemNotes/AttachmentText'), AttachmentText)

WebUI.delay(2)

WebUI.click(findTestObject('CreateSalesOrderPage/LineItemTab/LineItemNotes/SaveNotes'))

WebUI.delay(3)

WebUI.click(findTestObject('CreateSalesOrderPage/CommonElements/SaveDraft'))

WebUI.delay(3)

// ==================================================
// SPLITS SECTION DETAILS
// ==================================================

WebUI.waitForElementClickable(findTestObject('CreateSalesOrderPage/SplitsTab/SplitsTab'), 20)

WebUI.click(findTestObject('CreateSalesOrderPage/SplitsTab/SplitsTab'))

WebUI.delay(3)

WebUI.waitForElementVisible(findTestObject('CreateSalesOrderPage/SplitsTab/ExistingCommissionSplit'), 2)

WebUI.click(findTestObject('CreateSalesOrderPage/SplitsTab/ExistingCommisionSplitEdit'))

WebUI.clearText(findTestObject('CreateSalesOrderPage/SplitsTab/ExistingCommissionSplit'))

WebUI.waitForElementVisible(findTestObject('CreateSalesOrderPage/SplitsTab/ExistingCommissionSplit'), 2)

WebUI.click(findTestObject('CreateSalesOrderPage/SplitsTab/ExistingCommisionSplitEdit'))

WebUI.delay(5)

WebUI.setText(findTestObject('CreateSalesOrderPage/SplitsTab/ExistingCommissionSplit'), ExistingCommissionSplit)

WebUI.waitForElementVisible(findTestObject('CreateSalesOrderPage/SplitsTab/Save'), 2)

WebUI.click(findTestObject('CreateSalesOrderPage/SplitsTab/Save'))

WebUI.delay(2)

WebUI.waitForElementVisible(findTestObject('CreateSalesOrderPage/SplitsTab/Create_EditButton'), 2)

WebUI.click(findTestObject('CreateSalesOrderPage/SplitsTab/Create_EditButton'))

WebUI.delay(2)

WebUI.doubleClick(findTestObject('CreateSalesOrderPage/SplitsTab/Userlookup'))

WebUI.delay(2)

WebUI.setText(findTestObject('CreateSalesOrderPage/SplitsTab/Userlookup'), Userlookup)

WebUI.delay(2)

WebUI.click(findTestObject('CreateSalesOrderPage/SplitsTab/UserSelection'))

WebUI.delay(4)

WebUI.waitForElementVisible(findTestObject('CreateSalesOrderPage/SplitsTab/Influence_Commission'), 10)

WebUI.setText(findTestObject('CreateSalesOrderPage/SplitsTab/Influence_Commission'), Influence / Commission)

WebUI.waitForElementVisible(findTestObject('CreateSalesOrderPage/SplitsTab/JDE_SalesUnit'), 5)

WebUI.setText(findTestObject('CreateSalesOrderPage/SplitsTab/JDE_SalesUnit'), JDE SalesUnit)

WebUI.waitForElementVisible(findTestObject('CreateSalesOrderPage/SplitsTab/AddButton'), 10)

WebUI.click(findTestObject('CreateSalesOrderPage/SplitsTab/AddButton'))

WebUI.delay(3)

// ==================================================
// SUBMIT & ORDER CREATION
// ==================================================
if (ScenarioType.equalsIgnoreCase('Positive')) {
    WebUI.comment('Positive scenario: Clicking Save Draft before Submit ERP')

    WebUI.waitForElementClickable(findTestObject('CreateSalesOrderPage/CommonElements/SaveDraft'), 20)

    WebUI.click(findTestObject('CreateSalesOrderPage/CommonElements/SaveDraft'))

    WebUI.delay(3)
} else {
    WebUI.comment('Negative scenario: Save Draft skipped before Submit ERP')
}

WebUI.waitForElementClickable(findTestObject('CreateSalesOrderPage/CommonElements/SubmitERP'), 25)

WebUI.click(findTestObject('CreateSalesOrderPage/CommonElements/SubmitERP'))

WebUI.delay(3)

WebUI.waitForElementClickable(findTestObject('CreateSalesOrderPage/OrderPage/OrderButton'), 60)

WebUI.takeScreenshot()

WebUI.comment('Screenshot captured on Order popup page')

WebUI.click(findTestObject('CreateSalesOrderPage/OrderPage/OrderButton'))

WebUI.delay(3)

WebUI.refresh()

WebUI.waitForElementPresent(findTestObject('CreateSalesOrderPage/OrderPage/JDE-SAP-Order'), 20)

WebUI.takeScreenshot()

WebUI.comment('Screenshot captured after Order is initiated')

// ==================================================
// ORDER VALIDATION – 100% STABLE VERSION
// ==================================================
TestObject orderObj = findTestObject('CreateSalesOrderPage/OrderPage/JDE-SAP-Order')

// Wait until element is visible
WebUI.waitForElementVisible(orderObj, 60)

// Wait until element text is populated (polling)
String rawText = ''

int timeout = 60

int counter = 0

while (counter < timeout) {
    rawText = WebUI.getText(orderObj)

    if ((rawText != null) && !(rawText.trim().isEmpty())) {
        rawText = rawText.trim()

        break
    }
    
    WebUI.delay(1)

    counter++
}

// Final trim safety
rawText = rawText.trim()

WebUI.comment(('UI FULL TEXT CAPTURED -> [' + rawText) + ']')

// Extract only numeric value from UI text
String jdeSapOrderNumber = rawText.replaceAll('[^0-9]', '')

WebUI.comment(('EXTRACTED ORDER NUMBER -> [' + jdeSapOrderNumber) + ']')

// Hard validation
if ((jdeSapOrderNumber != null) && !(jdeSapOrderNumber.isEmpty())) {
    WebUI.comment('Sales Order placed successfully.')

    WebUI.comment('JDE/SAP Order Number: ' + jdeSapOrderNumber)
} else {
    WebUI.takeScreenshot()

    WebUI.comment('Sales Order NOT placed. JDE/SAP Order Number not generated.')

    assert false : 'JDE/SAP Order Number was not captured from UI'
}


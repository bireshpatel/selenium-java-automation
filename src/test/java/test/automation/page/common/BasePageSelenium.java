package test.automation.page.common;

import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class BasePageSelenium extends PageObject {

    public WebDriverWait webDriverWait;
    public String parentWindow;

    By PageLoad = By.cssSelector("body > div.loading-indicator.page-loading-indicator");

    public BasePageSelenium(WebDriver driver) {
        super(driver);
        setImplicitTimeout(30, TimeUnit.SECONDS);
        getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        getDriver().manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        getDriver().manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
        webDriverWait = new WebDriverWait(getDriver(), 60);
    }

    public WebElement waitForElement(final By byElement) {

        WebElement element;
        try {
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(byElement));
            element = getDriver().findElement(byElement);
        } catch (WebDriverException e) {
            throw new WebDriverException(e.getMessage());
        }
        return element;
    }

    public void waitForLoading() {
        int tme = 0;
        try {
            WebElement ele1 = getDriver().findElement(PageLoad);
            if (ele1 != null) {
                tme = 0;
                while (ele1.isDisplayed()) {
                    //Wait.waitFor(2);
                    tme += 2;
                }
            }
        } catch (org.openqa.selenium.NoSuchElementException e) {
        }
    }

    public void buttonClick(By locator) {
        try {
            webDriverWait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (Exception e) {
            e.printStackTrace();
        }
        waitForElement(locator).click();
    }

    public void checkboxClick(By locator, boolean value) {
        try {
            webDriverWait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (Exception e) {
            e.printStackTrace();
        }
        boolean currentValue = waitForElement(locator).isSelected();
        if (currentValue != value)
            waitForElement(locator).click();
    }

    public boolean isElementPresent(By locator) {
        WebElement element;
        try {
            element = getDriver().findElement(locator);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isElementDisplayed(By locator) {

        boolean isElementDisplayed;
        isElementDisplayed = waitForElement(locator).isDisplayed();
        return isElementDisplayed;
    }

    public boolean isButtonEnabled(By locator) {
        boolean isButtonEnabled;
        isButtonEnabled = waitForElement(locator).isEnabled();
        return isButtonEnabled;
    }

    public String getWebElementText(By locator) {

        WebElement webElement = waitForElement(locator);
        return webElement.getText();
    }

    public void enterText(By locator, String text) {
        WebElement webElementEnter = waitForElement(locator);
        webElementEnter.clear();
        webElementEnter.sendKeys(text);
    }

    public void waitUntilElementVisible(By locator) {
        int seconds = 30;
        WebDriverWait expWait = new WebDriverWait(getDriver(), seconds);
        expWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void highlightElement(By locator) {

        WebElement webElement = waitForElement(locator);
        try {
            if (getDriver() instanceof JavascriptExecutor) {
                ((JavascriptExecutor) getDriver()).executeScript("arguments[0].style.border='4px solid green';arguments[0].style.background='yellow'", webElement);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void selectOption(By locator, String opt) {
        WebElement element = waitForElement(locator);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(locator));
        highlightElement(locator);
        Select select = new Select(element);
        select.selectByVisibleText(opt);
    }

    public void selectValue(By locator, String text) throws Exception {
        WebElement webElementSelect = waitForElement(locator);
        Select select = new Select(webElementSelect);
        select.selectByValue(text);
    }

    public void selectOptionByIndex(By locator, int index) {
        WebElement element = waitForElement(locator);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(locator));
        highlightElement(locator);
        Select select = new Select(element);
        select.selectByIndex(index);
    }

    public void radioBtnClick(By locator) {
        WebElement element = waitForElement(locator);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }

    public void sendKeyTextBox(By locator, String str) {
        WebElement element = waitForElement(locator);
        element.findElement(locator).sendKeys(str);
    }

    public void sendKeyEnter(By locator) {
        WebElement element = waitForElement(locator);
        element.findElement(locator).sendKeys(Keys.ENTER);
    }

    public boolean verifyTextFromElement(By locator, String expectedText) {
        WebElement webElementText = waitForElement(locator);
        String elementText = webElementText.getText();
        return elementText.contains(expectedText);
    }

    public void clearText(By locator) {
        WebElement webElementEnter = waitForElement(locator);
        webElementEnter.clear();
    }

    public List getColumnValueList(By locator, int columnNumber) {
        List columnValuesList = new ArrayList();
        WebElement table = waitForElement(locator);
        List<WebElement> allRows = table.findElements(By.tagName("tr"));
        for (WebElement row : allRows) {
            WebElement cell = row.findElement(By.xpath("td[" + columnNumber + "]"));
            columnValuesList.add(cell.getText().trim());
        }
        return columnValuesList;
    }

    public void switchWindowHandle() {
        for (String handle : getDriver().getWindowHandles()) {
            getDriver().switchTo().window(handle);
        }
    }

    public boolean switchWindow(String title) {
        parentWindow = getDriver().getWindowHandle();
        Set<String> handles = getDriver().getWindowHandles();
        for (String handle : handles) {
            getDriver().switchTo().window(handle);
            if (getDriver().getTitle().equals(title)) {
                return true;
            }
        }
        return false;
    }

    public void switchFrame(int frameNumber) {
        getDriver().switchTo().frame(frameNumber);
    }

    public void switchParentWindow(String parentWin) {
        getDriver().switchTo().window(parentWin);
    }

    public void switchParentFrame() {
        getDriver().switchTo().parentFrame();
    }

    public String nameParentFrame() {
        String nameParent = getDriver().switchTo().parentFrame().toString();
        return nameParent;
    }

    public void refreshWebPage() {
        getDriver().navigate().refresh();
    }

    public Integer readFromTable(By locatorTable, String vSelect) {

        Integer reqRowValue = null;
        WebElement element = waitForElement(locatorTable);
        List<WebElement> tableRows = element.findElements(By.tagName("tr"));
        int countRows = tableRows.size();
        for (int j = 1; j < countRows; j++) {
            List<WebElement> columnsRow = tableRows.get(j).findElements(By.tagName("td"));
            String cellText = columnsRow.get(2).getText();
            if (cellText.contains((vSelect))) {
                reqRowValue = j + 1;
                break;
            }
        }
        return reqRowValue;

    }

    public void waitUntilTextPresentInElement(By locator, String elementText) {
        webDriverWait.until(ExpectedConditions.textToBePresentInElementLocated(locator, elementText));
    }

    public String getWebElementAttribute(By locator, String webElementAttribute) {
        WebElement webElementAtt = waitForElement(locator);
        return webElementAtt.getAttribute(webElementAttribute);
    }
}
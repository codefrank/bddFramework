package utilities;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class SeleniumUtils {

    private static Logger logger = Logger.getLogger(SeleniumUtils.class.getName());


    public static void _waitForPageLoad(WebDriver driver, int timeoutSeconds) {
        logger.info("Waiting '" + timeoutSeconds + "' seconds for document.readySate = complete");
        new WebDriverWait(driver, timeoutSeconds).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                JavascriptExecutor js = (JavascriptExecutor) d;
                return js.executeScript("return document.readyState").equals("complete");
            }
        });
    }


    public static WebElement _waitUntilElementPresent(WebDriver driver, By locator, int timeoutSeconds) {
        logger.info("Waiting '" + timeoutSeconds + "' seconds for element to be present");
        return new WebDriverWait(driver, timeoutSeconds).until(ExpectedConditions.presenceOfElementLocated(locator));
    }


    public static WebElement _waitUntilElementClickable(WebDriver driver, By locator, int timeoutSeconds) {
        logger.info("Waiting '" + timeoutSeconds + "' for element to be clickable");
        return new WebDriverWait(driver, timeoutSeconds).until(ExpectedConditions.elementToBeClickable(locator));
    }


    public static WebElement _waitUntilElementVisible(WebDriver driver, By locator, int timeoutSeconds) {
        logger.info("Waiting '" + timeoutSeconds + "' for element to be visible");
        return new WebDriverWait(driver, timeoutSeconds).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }


    public static boolean _waitUntilElementInvisible(WebDriver driver, By locator, int timeoutSeconds) {
        logger.info("Waiting '" + timeoutSeconds + "' for element to be invisible");
        return new WebDriverWait(driver, timeoutSeconds).until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public static boolean _waitUntilElementStale(WebDriver driver, By locator, int timeoutSeconds) {
        logger.info("Waiting '" + timeoutSeconds + "' seconds for element to be stale");
        return new WebDriverWait(driver, timeoutSeconds).until(ExpectedConditions.stalenessOf(driver.findElement(locator)));
    }

    public static void _waitUntilElementHasText(WebDriver driver, final By locator, int timeoutSeconds) {
        logger.info("Waiting ' " + timeoutSeconds + "' seconds for element to have text");
        new WebDriverWait(driver, timeoutSeconds).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.findElement(locator).getText().length() != 0;
            }
        });
    }


    public static boolean _doesElementExist(WebDriver driver, By locator) {
        logger.info("checking if the element exist");
        try {
            driver.findElement(locator);
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }


    public static void _selectFromDropDown(WebDriver driver, By locator, String value) {
        logger.info("Selecting value '" + value + "' from drop down");
        Select select = new Select(driver.findElement(locator));
        select.selectByVisibleText(value);
    }


    public static String _getSelectedDropDownValue(WebDriver driver, By locator) throws Exception {
        logger.info("Getting selected drop down value");
        Select select = new Select(driver.findElement(locator));
        WebElement option = select.getFirstSelectedOption();
        return option.getText();
    }


    public static List<String> _getDropDownOptions(WebDriver driver, By locator) {
        logger.info("Getting values from drop down");
        List<String> options = new ArrayList<>();
        for (WebElement option : new Select(driver.findElement(locator)).getOptions()) {
            String txt = option.getText();
            if (!option.getAttribute("value").equals("")) {
                options.add(option.getText());
            }
        }
        return options;
    }


    public static WebElement _iterateElement(WebDriver driver, By locator, String value) {
        logger.info("Iterating and finding " + value + " element");
        List<WebElement> element = driver.findElements(locator);
        for (int i = 0; i < element.size(); i++) {
            WebElement opt = element.get(i);
            if (opt.getAttribute("value").equalsIgnoreCase(value)){
                return opt;
            }
        }
        logger.info("Didnt find the element when iterting for "+value);
        return null;
    }


    public static void _selectRadio(WebDriver driver, By locator, String value){
        logger.info("Selecting '"+value + "' radio button");
        _iterateElement(driver, locator, value).click();
    }

    public static boolean _isRadioSelected(WebDriver driver, By locator, String value){
        logger.info("Checking if '"+value +"' radio is selected");
        return (_iterateElement(driver, locator, value)).isSelected();
    }


    public static void _setCheckBox(WebDriver driver, By locator, String value){
        logger.info("Setting value '"+value +"' checkbox to true");
        WebElement element = _iterateElement(driver, locator, value);
        if (element.isSelected()){
            element.click();
        }
    }


    public static boolean isCheckboxSelected(WebDriver driver, By locator, String value){
        logger.info("Checking if '"+value +" checkbox is selected");
        WebElement element = _iterateElement(driver, locator, value);
        return element.isSelected();
    }


    public static void _mouseOverElement(WebDriver driver, By locator){
        logger.info("Performing mouseover an element");
        Actions actions = new Actions(driver);
        WebElement element = driver.findElement(locator);
        actions.moveToElement(element);
        actions.perform();
    }


}

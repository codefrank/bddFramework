package utilities;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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


}

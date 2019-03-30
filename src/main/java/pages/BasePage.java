package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import java.util.logging.Logger;

public class BasePage {

    private static Logger log = Logger.getLogger(BasePage.class.getName());
    private static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<WebDriver>();
    private static JavascriptExecutor jsx = (JavascriptExecutor) getDriver();

    public static WebDriver getDriver(){
        return threadDriver.get();
    }

    public static void setDriver(WebDriver driver){
        threadDriver.set(driver);
    }

    public static void quitDriver(){
        if (getDriver() != null){
            getDriver().quit();
        }
    }

    public void navigateTo(String url) {
        log.info("Navigating to: "+url);
        getDriver().navigate().to(url);
    }

}

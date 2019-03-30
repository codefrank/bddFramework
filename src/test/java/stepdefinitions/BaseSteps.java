package stepdefinitions;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.BasePage;
import utilities.ConfigUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class BaseSteps {

    private static Logger log = Logger.getLogger(BaseSteps.class.getName());

    public static WebDriver startDriver(String browser){
        WebDriver driver = null;

        try {
            switch (browser){
                case "chrome":
                    /*DesiredCapabilities chromeCapabilities = DesiredCapabilities.chrome();
                    System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("disable-utilities");
                    chromeOptions.addArguments("disable-extensions");
                    chromeOptions.addArguments("--incognito");
                    chromeOptions.addArguments("--start-maximized");
                    chromeOptions.addArguments("--disable-component-extensions-with-background-pages");
                    chromeCapabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
                    driver = new ChromeDriver(chromeCapabilities);*/
                    System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.setAcceptInsecureCerts(true);
                    chromeOptions.addArguments("--start-maximized");
                    driver = new ChromeDriver(chromeOptions);
                    break;

                case "firefox":
                    DesiredCapabilities firefoxCapabilities = DesiredCapabilities.firefox();
                    FirefoxProfile firefoxProfile = new FirefoxProfile();
                    firefoxProfile.setAcceptUntrustedCertificates(true);
                    firefoxCapabilities.setCapability(FirefoxDriver.PROFILE, firefoxProfile);
                    driver = new FirefoxDriver(firefoxCapabilities);

                    break;

                case "edge":
                    System.setProperty("webdriver.edge.driver", "src/main/resources/drivers/MicrosoftWebDriver.exe");
                    driver = new EdgeDriver();

                    break;

            }

        } catch (Exception ex){
            log.info("Exception setting up drive: "+ex);
        }

        driver.manage().timeouts().pageLoadTimeout(Integer.parseInt(ConfigUtils.getConfigValue("page.load.timeout")), TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(Integer.parseInt(ConfigUtils.getConfigValue("implicit.wait.timeout")), TimeUnit.SECONDS);
        driver.manage().window().maximize();

        return driver;
    }


    @Before
    public void beforeScenario(Scenario scenario) throws Exception {
        String browser = System.getProperty("browser");
        setupLogAndConfig();
//        If no browser is passed through -Dbrowser then use the value from config file
        if (browser == null){
            browser = ConfigUtils.getConfigValue("browser.name");
        }
//        log.info(scenario.getName().toUpperCase() + " scenarios started with browser "+browser.toUpperCase());
        BasePage.setDriver(startDriver(browser));
    }


    @After
    public void afterScenario(Scenario scenario) throws IOException {
        if (scenario.isFailed()){
            try{
                final byte[] screenshot = ((TakesScreenshot) new BasePage().getDriver()).getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshot, "image/jpeg");
            } catch (Exception ex){
                log.info("Exception taking screenshots: "+ex);
            }
        }
        log.info("Scenario "+scenario.getName() +" ended with status: "+scenario.getStatus().toUpperCase());
        log.info("****************************************************************************************");
        BasePage.quitDriver();
    }




    public void setupLogAndConfig(){
        ConfigUtils.loadConfig();
        DOMConfigurator.configure("log4j.xml");
    }











}

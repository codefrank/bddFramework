package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class WikipediaResultsPage extends BasePage {

    private static Logger log = Logger.getLogger(WikipediaResultsPage.class.getName());
    private static JavascriptExecutor jsx = (JavascriptExecutor) BasePage.getDriver();

    private By firstHeading = By.xpath("//h1[@id='firstHeading']");
    private By languageList = By.xpath("//div[@id='p-lang']");

    public WikipediaResultsPage() {
    }

    public String getResultsHeader(){
        return getDriver().findElement(firstHeading).getText();
    }


    public ArrayList<String> getAvailableLanguages() {
        jsx.executeScript("arguments[0].scrollIntoView(true);", getDriver().findElement(languageList));
        log.info("Getting available languages");
        List<WebElement> allLangElements = getDriver().findElements(languageList);
        log.info("size of the language list is: "+allLangElements.size());
        ArrayList<String> allLangText = new ArrayList<>();
        for (WebElement element : allLangElements) {
            allLangText.add(element.getText());
        }
        return allLangText;
    }


    public Boolean selectLanguage(String language) {
        log.info("Selecting language '" + language + "' ");
        List<WebElement> allLangElements = getDriver().findElements(languageList);
        for (WebElement element : allLangElements) {
            if (element.getText().equalsIgnoreCase(language)) {
                element.click();
                return true;
            }
        }
        return false;
    }
}

package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import java.util.logging.Logger;

public class WikipediaHomePage extends BasePage {

    private static Logger log = Logger.getLogger(WikipediaHomePage.class.getName());

    private By searchInput = By.xpath("//input[@id='searchInput']");
    private By searchLanguage = By.xpath("//select[@id='searchLanguage']");
    private By searchButton = By.xpath("//button[@class='pure-button pure-button-primary-progressive']");

    public WikipediaHomePage(){

    }

    public WikipediaHomePage enterSearchTerm(String searchTerm){
        log.info("Entering search term '" +searchTerm +"' ");
        getDriver().findElement(searchInput).sendKeys(searchTerm);
        return this;
    }


    public WikipediaHomePage selectLanguage(String language){
        log.info("Selecting language '" +language +"' ");
        new Select(getDriver().findElement(searchLanguage)).selectByVisibleText(language);
        return this;
    }


    public WikipediaResultsPage clickSearchButton(){
        log.info("Clicking on search button");
        getDriver().findElement(searchButton).click();
        return new WikipediaResultsPage();
    }



















}

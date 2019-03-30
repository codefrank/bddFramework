package stepdefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import pages.WikipediaHomePage;
import pages.WikipediaResultsPage;

import java.util.logging.Logger;

import static pages.BasePage.getDriver;

public class WikipediaSteps {

    private Logger log = Logger.getLogger(WikipediaSteps.class.getName());
    private static JavascriptExecutor jsx = (JavascriptExecutor) getDriver();

    WikipediaHomePage home;
    WikipediaResultsPage results;


    @Given("^User is on the wikipedia home page$")
    public void user_is_on_the_wikipedia_home_page() throws Throwable {
        String url = "http://www.wikipedia.org";
        home = new WikipediaHomePage();
        home.navigateTo(url);
    }

    @When("^User searches for (.*)$")
    public void user_searches_for(String term) throws Throwable {
        home.enterSearchTerm(term);
        results = home.clickSearchButton();
    }

    @Then("^User should see search results containing heading (.*)$")
    public void user_should_see_search_results_containing_heading(String term) throws Throwable {
        String actualHeader = results.getResultsHeader();
        Assert.assertTrue(term.equalsIgnoreCase(actualHeader));
    }

}

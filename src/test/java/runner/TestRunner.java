package runner;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        tags = {"@wiki"},
        features = {"src/test/resources/features"},
        glue ={"stepdefinitions"},
        dryRun = false,
        strict = false,
        plugin = {
                "pretty",
                "html:target/cucumber-report/",
                "json:target/cucumber-report/cucumber-json-report.json",
                "junit:target/junit-report/cucumber-junit-report.xml"
        }
)

public class TestRunner {

}

package testRunners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "resources/features/ClientAdvancedQueries.feature", glue = "stepDefinitions", strict = true)
public class TestRunner { 

}

package testRunners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "resources/features/ClientBookContainer.feature", glue = "stepDefinitions", strict = true)
public class TestRunner { 

}

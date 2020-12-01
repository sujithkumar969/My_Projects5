package cucumber.Options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/java/features/validateAddPlaceAPI.feature"},
                 glue = {"stepDefinitions"},
                 plugin = "json:target/jsonReports/cucumber-reports.json")

public class TestRunner {

//,tags = "@deletePlaceAPI", tags = "@addPlaceAPI" , tags = "@RegressionTest"
	
}
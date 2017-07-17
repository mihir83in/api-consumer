package com.zp.apiconsumer.acceptance;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "pretty", "html:target/cucumber", "json:target/cucumber.json" },
        glue = "com.zp.apiconsumer.acceptance.steps",
        features = { "src/test/resources/features" }, strict = true)
public class RunCukesTest {

}

package com.baryonminds.revaliyo.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features/08.Verify Listing Owner Receives & Accepts a Giveaway Request.feature",
    glue = {
        "com.baryonminds.revaliyo.stepdefinitions",
    },
    plugin = {
        "pretty",
        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
    },
    monochrome = true,dryRun = false
)
public class TestRunner extends AbstractTestNGCucumberTests {
}











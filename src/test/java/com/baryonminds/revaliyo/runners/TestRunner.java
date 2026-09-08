package com.baryonminds.revaliyo.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features/Verify New User Sign Up, Email OTP Verification, Profile Setup, and First Sign-In Flow.feature",
    glue = {
        "com.baryonminds.revaliyo.stepdefinitions",
    },
    plugin = {
        "pretty",
        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
    },
    monochrome = true,dryRun = true
)
public class TestRunner extends AbstractTestNGCucumberTests {
}
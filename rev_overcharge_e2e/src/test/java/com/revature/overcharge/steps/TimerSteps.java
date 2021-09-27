package com.revature.overcharge.steps;

import org.openqa.selenium.JavascriptExecutor;


//import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.Assert.*;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.revature.overcharge.pages.TimerWidget;
import com.revature.overcharge.runners.TimerRunner;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TimerSteps {
	
	private static WebDriver driver = TimerRunner.driver;
	private static WebDriverWait wait = new WebDriverWait(driver, 10);
	private static TimerWidget timer = TimerRunner.timer;
	
	

	

    @Given("^User has navigated to the website for timer$")
    public void user_has_navigated_to_the_website_for_timer() throws Throwable {
    	
    	JavascriptExecutor js = (JavascriptExecutor) driver;  
		   js.executeScript("window.scrollBy(0,350)", "");
		
		Thread.sleep(2000);

    	timer.navigateTo(timer.getURL());
    	Thread.sleep(2000);
    }
    
    @Given("^User shows Timer$")
    public void user_shows_timer() throws Throwable {
    	
    	JavascriptExecutor js = (JavascriptExecutor) driver;  
		   js.executeScript("window.scrollBy(0,350)", "");
		
		Thread.sleep(2000);
		
    	timer.timerLink.click();
    	wait.withTimeout(Duration.ofSeconds(2));
    }
    
    @Given("^Timer visibility is \"([^\"]*)\"$")
    public void timer_visibility_is_something(String visibility) throws Throwable {
    	
    	JavascriptExecutor js = (JavascriptExecutor) driver;  
		   js.executeScript("window.scrollBy(0,350)", "");
		
		Thread.sleep(2000);
		
    	String css = "";
    	switch (visibility) {
		case "On":
			css = "block";
			Thread.sleep(2000);
			break;
		case "Off":
			css = "none";
			timer.timerLink.click();
			wait.withTimeout(Duration.ofSeconds(1));
			break;
    	}
    	assertEquals(css,timer.timerContainer.getCssValue("display"));
    }

    @When("^User clicks \"([^\"]*)\" on timer$")
    public void user_clicks_something_on_timer(String button) throws Throwable {
    	
    	JavascriptExecutor js = (JavascriptExecutor) driver;  
		   js.executeScript("window.scrollBy(0,350)", "");
		
		Thread.sleep(2000);
		
    	switch (button) {
		case "Timer":
			timer.timerLink.click();
			Thread.sleep(2000);
			break;
		case "Play":
			timer.playTimer.click();
			Thread.sleep(2000);
			break;
		case "Pause":
			timer.playTimer.click();
			Thread.sleep(2000);
			timer.pauseTimer.click();
			Thread.sleep(2000);
			break;
		case "Reset":
			timer.playTimer.click();
			Thread.sleep(2000);
			timer.resetTimer.click();
			Thread.sleep(2000);
			break;
		case "Change Mode":
			timer.modeToggle.click();
			Thread.sleep(2000);
			break;
		case "Display Input":
			timer.inputToggle.click();
			Thread.sleep(2000);
			break;
    	}
    	wait.withTimeout(Duration.ofSeconds(1));
    }

    @When("^Timer display is \"([^\"]*)\"$")
    public void timer_display_is_something(String display) throws Throwable {
    	
    	JavascriptExecutor js = (JavascriptExecutor) driver;  
		   js.executeScript("window.scrollBy(0,350)", "");
		
		Thread.sleep(2000);
		
    	user_clicks_something_on_timer("Display Input");
    	Thread.sleep(2000);
    	user_inputs_and("0", "1");
    	Thread.sleep(2000);
    	user_clicks_something_on_timer("Play");
    	wait.withTimeout(Duration.ofMinutes(1)).until(
    			ExpectedConditions.attributeToBe(timer.display, "innerText", display));
    	
    }

    @Then("^Timer toggles visibility to \"([^\"]*)\"$")
    public void timer_toggles_visibility_to_something(String visibility) throws Throwable {
    	
    	JavascriptExecutor js = (JavascriptExecutor) driver;  
		   js.executeScript("window.scrollBy(0,350)", "");
		
		Thread.sleep(2000);
		
    	String css = "";
    	switch (visibility) {
		case "On":
			css = "block";
			Thread.sleep(2000);
			break;
		case "Off":
			css = "none";
			Thread.sleep(2000);
			break;
    	}
    	assertEquals(css,timer.timerContainer.getCssValue("display"));
    }

    @Then("^Timer status is \"([^\"]*)\"$")
    public void timer_status_is_something(String status) throws Throwable {
    	
    	JavascriptExecutor js = (JavascriptExecutor) driver;  
		   js.executeScript("window.scrollBy(0,350)", "");
		
		Thread.sleep(2000);
		
    	assertEquals(": "+status, timer.status.getText());
    }

    @Then("^Timer mode changes to \"([^\"]*)\"$")
    public void timer_mode_changes_to_something(String finalMode) throws Throwable {
    	
    	JavascriptExecutor js = (JavascriptExecutor) driver;  
		   js.executeScript("window.scrollBy(0,350)", "");
		
		Thread.sleep(2000);
		
    	ExpectedConditions.attributeToBe(timer.mode, "innerText", finalMode);
    	Thread.sleep(2000);
    }

    @Then("^Timer is set to (.+)$")
    public void timer_is_set_to(String newTime) throws Throwable {
    	
    	JavascriptExecutor js = (JavascriptExecutor) driver;  
		   js.executeScript("window.scrollBy(0,350)", "");
		
		Thread.sleep(2000);
		
    	ExpectedConditions.attributeToBe(timer.display, "innerText", newTime);
    	Thread.sleep(2000);
    }

    @And("^User inputs (.+) and (.+)$")
    public void user_inputs_and(String hours, String minutes) throws Throwable {
    	
    	JavascriptExecutor js = (JavascriptExecutor) driver;  
		   js.executeScript("window.scrollBy(0,350)", "");
		
		Thread.sleep(2000);
		
    	timer.hoursInput.sendKeys(hours);
    	Thread.sleep(2000);
    	timer.minInput.sendKeys(minutes);
    	Thread.sleep(2000);
	    timer.setTimer.click();
	    wait.withTimeout(Duration.ofSeconds(1));
    }

    @And("^Timer mode is \"([^\"]*)\"$")
    public void timer_mode_is_something(String initMode) throws Throwable {
    	
    	JavascriptExecutor js = (JavascriptExecutor) driver;  
		   js.executeScript("window.scrollBy(0,350)", "");
		
		Thread.sleep(2000);
		
    	if (initMode == "Break") user_clicks_something_on_timer("Change Mode");
    	ExpectedConditions.attributeToBe(timer.mode, "innerText", initMode);
    	wait.withTimeout(Duration.ofSeconds(1));
    }
}

package com.revature.overcharge.steps;

import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.WebDriver;

import com.revature.overcharge.pages.CardTraversal;
import com.revature.overcharge.runners.CardTraversalRunner;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;


public class CardTraversalSteps {
	public static CardTraversal cardTraversal = CardTraversalRunner.cardTraversal;
	public static WebDriver driver = CardTraversalRunner.driver;

	@Given("User has nagivated to the website")
	public void user_has_nagivated_to_the_website() throws InterruptedException {
		driver.get("http://localhost:4200/home");
		Thread.sleep(3000);
	}

	@Given("User has logged in")
	public void user_has_logged_in() throws InterruptedException {
		cardTraversal.login.click();
		Thread.sleep(3000);
		cardTraversal.inputUname.sendKeys("user");
		Thread.sleep(3000);
		cardTraversal.inputPass.sendKeys("pass");
		Thread.sleep(3000);
		cardTraversal.loginButton.click();
		Thread.sleep(3000);
	}

	@When("User navigates to the library section in side nav")
	public void user_navigates_to_the_library_section_in_side_nav() throws InterruptedException {
		cardTraversal.library.click();
		Thread.sleep(3000);
	}

	@Then("User has option to click card runner")
	public void user_has_option_to_click_card_runner() throws InterruptedException {
		cardTraversal.cardrunner.click();
		Thread.sleep(3000);
	}

	@Then("User traverses a deck")
	public void user_traverses_a_deck() throws InterruptedException {
		// Need to traverse deck until there is no more questions
		cardTraversal.nextquestion.click();
		Thread.sleep(1000);
		cardTraversal.nextquestion.click();
		Thread.sleep(1000);
		cardTraversal.nextquestion.click();
		Thread.sleep(1000);
		cardTraversal.nextquestion.click();
		Thread.sleep(1000);
		cardTraversal.nextquestion.click();
		Thread.sleep(1000);
		cardTraversal.nextquestion.click();
		Thread.sleep(1000);
		cardTraversal.nextquestion.click();
		Thread.sleep(1000);
		cardTraversal.nextquestion.click();
		Thread.sleep(1000);
		cardTraversal.nextquestion.click();
		Thread.sleep(1000);
		cardTraversal.nextquestion.click();
		Thread.sleep(1000);
		cardTraversal.nextquestion.click();
		Thread.sleep(1000);
		cardTraversal.nextquestion.click();
		Thread.sleep(1000);
		cardTraversal.nextquestion.click();
		Thread.sleep(1000);
		cardTraversal.nextquestion.click();
		Thread.sleep(1000);
		cardTraversal.nextquestion.click();
		Thread.sleep(1000);
		cardTraversal.nextquestion.click();
		Thread.sleep(1000);
		cardTraversal.nextquestion.click();
		Thread.sleep(1000);
		cardTraversal.nextquestion.click();
		Thread.sleep(1000);
	}

	@Then("User clicks finish set")
	public void user_clicks_finish_set() throws InterruptedException {
		cardTraversal.nextquestion.click();
		Thread.sleep(3000);
	}
	
	@Then("User enters a rating to submit")
	public void user_enters_a_rating_to_submit() throws InterruptedException {
		cardTraversal.star5.click();
		Thread.sleep(5000);
		
		JavascriptExecutor js = (JavascriptExecutor) driver;  
		   js.executeScript("window.scrollBy(0,350)", "");
		   
		   Thread.sleep(5000);
		
		cardTraversal.submitrating.click();
		Thread.sleep(3000);
	}
}

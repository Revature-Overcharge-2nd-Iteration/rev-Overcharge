Feature: Deck Approval

Scenario: Successful Login
	Given I am at the home page
	When I click on Guest button
	Then I should be able to click on "Login"
	When I click on "Login"
	Then I should be able to input my Info
	When I type in the username input value "snassey1"
	And I type in the password input value "CwQOZeX"
	And I click the Login Button
	Then I should recieve points and brought to the Library page
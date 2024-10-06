Feature: feature to test login functionality
Background:
Given User opens D1 OLB application

  Scenario: Check login is successful with valid credentials
    When User is on "LoginPage"
    When User enters username 
    When User enters password
    When User clicks on login button
    When User is on "DashboardPage"
    Then Verify DashboardPage Title
    
  
    When User is on "DashboardPage"
    When User click on Tile View 
    When User click on MMA Account tile
    When User is on "AccountPage"
    When User clicks on Show Accout Details
    When User clicks on Logout
    When User clicks Yes on confirmation
    When User is on "LoginPage"
    Then Verify Login Title
   
    
    

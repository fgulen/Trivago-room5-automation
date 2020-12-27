Cucumber BDD Test Automation Framework

Selenium & Java based `Frontend` and `Backend` automation suite utilising the BDD methodologies of Cucumber and Gherkin


- 1. Install JAVA SDK 12
- 2. Install Maven
- 3. Download chromedriver https://sites.google.com/a/chromium.org/chromedriver/downloads
- 4. Install Plugins (Cucumber,Gherkin)
   
    # Mac
    
    Intellij IDEA   -> Preferences   -> Plugins ->  Marketplace -> `Cucumber` for Java 
    Intellij IDEA   -> Preferences   -> Plugins ->  Marketplace -> `Gherkin`  
    
    # Windows  
    Intellij IDEA   -> Settings     ->  Plugins -> Marketplace    -> `Cucumber` for Java 
    Intellij IDEA   -> Preferences  -> Plugins  -> Marketplace    -> `Gherkin`  



# Framework Overview

The cucumber BDD testing framework Using keywords such as Given, When, Then and And, acceptance criteria tests known as feature files can then be broken down into testable steps.
Can be utilized both for UI and Backend Testing

# Cucumber Selenium
Overall testframework leveraging the Cucumber framework with Selenium written in JAVA.

# Feature File
The feature file specifies the steps in BDD language style (`Plain English Language`)

# Utilities Package - 
In order to keep common methods separate

# (POM)Page Object Model - 
Java class whereby the necessary HTML objects are captured as WebElements to be manipulated by the associated model class to be able to reach and maintain easly

# ChromeDriver.exe - 
Local chromedriver necessary in order 

# Cucumber Reports -
Cucumber has a built in report generation whereby Feature files tested are automatically written to cucumbers own reporting system.

To run Report
   ```$ mvn clean
   ```$ mvn verify => the folders and files will be created as HTML format


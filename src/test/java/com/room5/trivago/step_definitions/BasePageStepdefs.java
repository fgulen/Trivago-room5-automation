package com.room5.trivago.step_definitions;


import com.room5.trivago.pages.HomePage;
import com.room5.trivago.utilities.BrowserUtils;
import com.room5.trivago.utilities.ConfigurationReader;
import com.room5.trivago.utilities.Driver;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.*;


import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class BasePageStepdefs {

    String url = ConfigurationReader.get("url");
    HomePage homePage = new HomePage();

    //Scenario 1 => Destination links go to page with following titles

    @Given("user opens homepage")
    public void user_opens_homepage() throws IOException {
        Driver.get().get(url);

    }

    @When("user clicks mainMenuIcon and clicks {string} and {string}")
    public void userClicksMainMenuIconAndClicksAnd(String menuLink, String destinationOpt) {

        homePage.mainIcon.click();
        BrowserUtils.waitFor(3);
        homePage.destinations.click();
        WebElement destination = Driver.get().findElement(By.xpath("//div[contains(text(),'" + destinationOpt + "')]"));

        JavascriptExecutor jse = (JavascriptExecutor) Driver.get();
        jse.executeScript("arguments[0].click()", destination);
        BrowserUtils.waitFor(2);
    }

    @Then("verify that page title is {string}")
    public void verifyThatPageTitleIs(String expected) {

        Assert.assertEquals(expected, Driver.get().getTitle());
        System.out.println("expected = " + expected);
        System.out.println("actual = " + Driver.get().getTitle());

    }
    // Scenario 2 => Destination menu has following data

    @When("user clicks mainMenuIcon and clicks {string}")
    public void userClicksMainMenuIconAndClicks(String destination) {

        homePage.mainIcon.click();
        BrowserUtils.waitFor(3);
        homePage.destinations.click();

    }

    @Then("verify that menu exist following options")
    public void verifyThatMenuExistFollowingOptions(List<String> destinationOptions) {

        List<String> actualDestinationOptions = BrowserUtils.getElementsText(homePage.destinationOptions);
        BrowserUtils.waitFor(4);
        Assert.assertEquals(destinationOptions, actualDestinationOptions);
    }

    // Scenario 3 => Search function

    @When("user clicks search button and writes {string} to search")
    public void userClicksSearchButtonAndWritesToSearch(String anyDestination) {
        homePage.searchButton.click();
        BrowserUtils.waitFor(3);
        homePage.searchInput.sendKeys(anyDestination + Keys.ENTER);
        BrowserUtils.waitFor(2);

    }

    @Then("verify that message contains following {string}")
    public void verifyThatMessageContainsFollowing(String text) {

        BrowserUtils.waitFor(2);

        try {

            if (homePage.resultFound.getText().contains(text)) {

                assertTrue(homePage.resultFound.getText().contains(text));

                System.out.println("Search result : " + homePage.resultFound.getText());
                System.out.println("Search result must contain = " + text);
            }

        } catch (NoSuchElementException e) {

            Assert.assertTrue(homePage.resultNotFound.getText().contains(text));

            System.out.println("Search result : " + homePage.resultNotFound.getText());
            System.out.println("Search result must contain = " + text);
        }


    }

}
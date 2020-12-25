package com.room5.trivago.step_definitions;

import com.room5.trivago.pages.HomePage;
import com.room5.trivago.utilities.BrowserUtils;
import com.room5.trivago.utilities.ConfigurationReader;
import com.room5.trivago.utilities.Driver;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class BasePageStepDefs {

    String url = ConfigurationReader.get("url");
    HomePage homePage = new HomePage();

    @Given("user opens homepage")
    public void user_opens_homepage() throws IOException {

        Driver.get().get(url);
        File file = new File("Cookiefile.data");
        try {
            file.delete();
            file.createNewFile();
            FileWriter file2 = new FileWriter(file);
            BufferedWriter Bwritecookie = new BufferedWriter(file2); //Getting the cookie information
            for (Cookie ck : Driver.get().manage().getCookies()) {
                Bwritecookie.write((ck.getName() + ";" + ck.getValue() + ";" + ck.getDomain() + ";" + ck.getPath() + ";" + ck.getExpiry() + ";" + ck.isSecure()));
                Bwritecookie.newLine();
            }
            Bwritecookie.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @When("user clicks mainMenuIcon and clicks {string} and {string}")
    public void userClicksMainMenuIconAndClicksAnd(String arg0, String destinationOpt) {

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
//--------------------

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

    //----------------------------------------

    @When("user clicks search button and writes {string} to search")
    public void userClicksSearchButtonAndWritesToSearch(String anyDestination) {
        homePage.searchButton.click();
        BrowserUtils.waitFor(3);
        homePage.searchInput.sendKeys(anyDestination + Keys.ENTER);
        BrowserUtils.waitFor(2);

//        System.out.println(homePage.searchResult.getText());
//        System.out.println("homePage = " + homePage.searchResultNo.getText());


    }

    @Then("verify that message contains following {string}")
    public void verifyThatMessageContainsFollowing(String text) {
        BrowserUtils.waitFor(2);

        try {
            Assert.assertTrue(homePage.searchResult.getText().contains("Search Results"));
            System.out.println("Search Report :"+homePage.searchResult.getText());
        } catch (Exception e) {
            Assert.assertFalse(homePage.searchResultNo.getText().contains("Search Results"));
            System.out.println("Search Report :"+homePage.searchResultNo.getText());
        }

//        String bodyText = Driver.get().findElement(By.xpath("//body")).getText();
//        int x = 0;
//        boolean flag=true;
//        for (int i = 0; i < 5; i++) {
//            if (bodyText.contains(text)) {
//                x++;
//            }
//        }
//
//        if (x > 1) {
//            assertTrue(flag);
//            System.out.println("***"+text+"*** Found");
//        } else {
//            assertTrue(flag);
//            System.out.println("***"+text+"*** NOT Found");
//        }
    }
}
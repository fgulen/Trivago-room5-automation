package com.room5.trivago.step_definitions;

import com.github.javafaker.Faker;
import com.room5.trivago.pages.HomePage;
import com.room5.trivago.utilities.BrowserUtils;
import com.room5.trivago.utilities.ConfigurationReader;
import com.room5.trivago.utilities.Driver;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class HomePageStepdefs {

    String url = ConfigurationReader.get("url");
    HomePage homePage = new HomePage();

    //Scenario 1 => Broken links verification

    @Then("verify the links")
    public void verify_the_links() {

        List<WebElement> links=Driver.get().findElements(By.tagName("a"));

        System.out.println("Total links are "+links.size());

        for(int i=0;i<links.size();i++) {

            WebElement link= links.get(i);

            String urls=link.getAttribute("href");

            if (urls.contains("https://www.trivago.com/")){
                break;
            }else{

                verifyLinkActive(urls);
            }

            // System.out.println("Url = " + url);
        }

    }

    public static void verifyLinkActive(String linkUrl)
    {
        try
        {
            URL url = new URL(linkUrl);
            HttpURLConnection httpURLConnect=(HttpURLConnection)url.openConnection();
            httpURLConnect.setConnectTimeout(3000);
            httpURLConnect.connect();

            if(httpURLConnect.getResponseCode()==200) {
                System.out.println(linkUrl+" - "+httpURLConnect.getResponseMessage());
            }
            if(httpURLConnect.getResponseCode()==HttpURLConnection.HTTP_NOT_FOUND) {
                System.out.println(linkUrl+" - "+httpURLConnect.getResponseMessage() + " - "+ HttpURLConnection.HTTP_NOT_FOUND);
            }
        } catch (Exception e) {

        }

    }

    //  Scenario 2 => Valid data and successful subscription messages in different languages

    @Given("user selects {string} from drop-down menu")
    public void userSelectsFromDropDownMenu(String country) {

        Select dropDown = new Select(homePage.dropDownCountries);
        List<WebElement> dropDownList =dropDown.getOptions();
        /*
        System.out.println("Number of the countries in dropdown menu :"+dropDownList.size());

        for (WebElement lists :dropDownList){
            System.out.println(lists.getText());
        }*/
        dropDown.selectByVisibleText(country); ;
        BrowserUtils.waitFor(1);

        System.out.println("***Country = " + country);
    }



    @When("user writes own {string} address to the newsletter subscription input and submits")
    public void userWritesOwnAddressToTheNewsletterSubscriptionInputAndSubmits(String email) {

        Faker faker = new Faker();

        homePage.newsLetterMail.sendKeys(faker.internet().emailAddress());
        BrowserUtils.waitFor(2);
        homePage.newsLetterSubmit.click();
        BrowserUtils.waitFor(2);
        System.out.println("***Actual Message = " + homePage.newsLetterAlert.getText());

    }

    @Then("Verify that user gets  newsletter subscription {string} in the own language")
    public void verifyThatUserGetsNewsletterSubscriptionInTheOwnLanguage(String message) {

        BrowserUtils.waitFor(3);
        Assert.assertEquals(message,homePage.newsLetterAlert.getText());
    }


    //  Scenario 3 => Invalid data and messages in different languages

    @Given("user selects {string} for invalid data")
    public void userSelectsForInvalidData(String country) {

        Select dropDown = new Select(homePage.dropDownCountries);
        List<WebElement> dropDownList =dropDown.getOptions();

    /*  System.out.println(dropDownList.size());          Printing menu options with visible text
          for (WebElement lists :dropDownList){
               System.out.println(lists.getText());
            } */

        dropDown.selectByVisibleText(country); ;
        BrowserUtils.waitFor(1);
        System.out.println("***Country = " + country);
    }

    @When("user writes {string} address to the newsletter subscription input and submits")
    public void userWritesAddressToTheNewsletterSubscriptionInputAndSubmits(String invalidEmail) {

        homePage.newsLetterMail.sendKeys(invalidEmail);
        BrowserUtils.waitFor(1);
        homePage.newsLetterSubmit.click();
        BrowserUtils.waitFor(2);
        BrowserUtils.waitFor(1);

    }

    @Then("Verify that user gets newsletter subscription {string} in own language")
    public void verifyThatUserGetsNewsletterSubscriptionInOwnLanguage(String errorMessage) {

        BrowserUtils.waitFor(2);
        System.out.println("***Actual Message = " + homePage.messageErrorAlert.getText());
        Assert.assertEquals(errorMessage,homePage.messageErrorAlert.getText());
    }

}

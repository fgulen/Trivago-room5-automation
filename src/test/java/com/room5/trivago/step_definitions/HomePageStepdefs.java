package com.room5.trivago.step_definitions;

import com.github.javafaker.Faker;
import com.room5.trivago.pages.HomePage;
import com.room5.trivago.utilities.BrowserUtils;
import com.room5.trivago.utilities.ConfigurationReader;
import com.room5.trivago.utilities.Driver;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class HomePageStepdefs {

    String url = ConfigurationReader.get("url");
    HomePage homePage = new HomePage();

    //Scenario 1 => Broken links verification

    @Then("verify the links")
    public void verify_the_links() {

        String hrefvalue = null;

        List<WebElement> links = Driver.get().findElements(By.tagName("a"));

        System.out.println("Total links are " + links.size());

        for (int i = 0; i < links.size(); i++) {

            hrefvalue = links.get(i).getAttribute("href");

            if (hrefvalue != null && !hrefvalue.contains("www.trivago.com")) {  // trivago.com doesn`t let me to send http request

//                if(hrefvalue.contains("magazine.trivago")) {
//                    System.out.println( hrefvalue + " = internal domain");
//                } else {
//                    System.out.println( hrefvalue + " = external domain");
//                }
                verifyLinkActive(hrefvalue);
            } else {
                System.out.println("element doesn't have href attriubte");
            }
        }

    }

    public static void verifyLinkActive(String linkUrl) {
        try {
            URL url = new URL(linkUrl);
            HttpURLConnection httpURLConnect = (HttpURLConnection) url.openConnection();
            httpURLConnect.setConnectTimeout(3000);
            httpURLConnect.connect();

            if (httpURLConnect.getResponseCode() == 200) {
                System.out.println(linkUrl + " - " + httpURLConnect.getResponseMessage());
            }
            if (httpURLConnect.getResponseCode() == HttpURLConnection.HTTP_NOT_FOUND) {
                System.out.println(linkUrl + " - " + httpURLConnect.getResponseMessage() + " - " + HttpURLConnection.HTTP_NOT_FOUND);
            }
        } catch (Exception e) {

        }

    }

    //  Scenario 2 => Valid data and successful subscription messages in different languages

    @Given("user selects {string} from drop-down menu")
    public void userSelectsFromDropDownMenu(String country) {

        Select dropDown = new Select(homePage.dropDownCountries);
        List<WebElement> dropDownList = dropDown.getOptions();
        /*
        System.out.println("Number of the countries in dropdown menu :"+dropDownList.size());

        for (WebElement lists :dropDownList){
            System.out.println(lists.getText());
        }*/
        dropDown.selectByVisibleText(country);
        BrowserUtils.waitForImp(1);

        System.out.println("***Country = " + country);
    }


    @When("user writes own {string} address to the newsletter subscription input and submits")
    public void userWritesOwnAddressToTheNewsletterSubscriptionInputAndSubmits(String email) throws InterruptedException {

        Faker faker = new Faker();

        homePage.newsLetterMail.sendKeys(faker.internet().emailAddress());
        BrowserUtils.waitForImp(4);

        JavascriptExecutor jse = (JavascriptExecutor) Driver.get();
        jse.executeScript("arguments[0].click();", homePage.newsLetterSubmit);

        // homePage.newsLetterSubmit.click();

        BrowserUtils.waitForThread(3);
        System.out.println("***Actual Message = " + homePage.newsLetterAlert.getText());

    }

    @Then("Verify that user gets  newsletter subscription {string} in the own language")
    public void verifyThatUserGetsNewsletterSubscriptionInTheOwnLanguage(String message) {

        BrowserUtils.waitForImp(10);
        Assert.assertEquals(message, homePage.newsLetterAlert.getText());
    }


    //  Scenario 3 => Invalid data and messages in different languages

    @Given("user selects {string} for invalid data")
    public void userSelectsForInvalidData(String country) {

        Select dropDown = new Select(homePage.dropDownCountries);
        List<WebElement> dropDownList = dropDown.getOptions();

    /*  System.out.println(dropDownList.size());          Printing menu options with visible text
          for (WebElement lists :dropDownList){
               System.out.println(lists.getText());
            } */

        dropDown.selectByVisibleText(country);
        BrowserUtils.waitForImp(1);
        System.out.println("***Country = " + country);
    }

    @When("user writes {string} address to the newsletter subscription input and submits")
    public void userWritesAddressToTheNewsletterSubscriptionInputAndSubmits(String invalidEmail) {

        homePage.newsLetterMail.sendKeys(invalidEmail);
        BrowserUtils.waitForExp(4);
        homePage.newsLetterSubmit.click();
        BrowserUtils.waitForImp(3);


    }

    @Then("Verify that user gets newsletter subscription {string} in own language")
    public void verifyThatUserGetsNewsletterSubscriptionInOwnLanguage(String errorMessage) {

        BrowserUtils.waitForThread(3);
        System.out.println("***Actual Message = " + homePage.messageErrorAlert.getText());
        Assert.assertEquals(errorMessage, homePage.messageErrorAlert.getText());
    }

}

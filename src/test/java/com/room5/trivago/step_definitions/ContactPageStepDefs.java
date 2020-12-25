package com.room5.trivago.step_definitions;

import com.github.javafaker.Faker;
import com.room5.trivago.pages.ContactPage;
import com.room5.trivago.pages.HomePage;
import com.room5.trivago.utilities.BrowserUtils;
import com.room5.trivago.utilities.Driver;
import io.cucumber.java.en.*;
import org.junit.Assert;

public class ContactPageStepDefs {

    ContactPage contactPage = new ContactPage();
    String originalWindow;

    @Given("user clicks {string} links to go contact page")
    public void user_clicks_links_to_go_contact_page(String string) {

        HomePage homePage = new HomePage();

        //Store the ID of the original window
         originalWindow = Driver.get().getWindowHandle();

        //Check we don't have other windows open already
        assert Driver.get().getWindowHandles().size() == 1;

        //Click the link which opens in a new window
        homePage.contact.click();





//Wait for the new tab to finish loading content
        BrowserUtils.waitFor(1);


    }

    @When("user writes message in {string}, name in {string} and your mail in {string} inputs")
    public void user_writes_message_in_name_in_and_your_mail_in_inputs(String message, String name, String email) {

        //Wait for the new window or tab
        BrowserUtils.waitFor(1);
        //Loop through until we find a new window handle
        for (String windowHandle : Driver.get().getWindowHandles()) {
            if(!originalWindow.contentEquals(windowHandle)) {
                Driver.get().switchTo().window(windowHandle);
                break;
            }
        }

        Faker faker = new Faker();
         name =faker.name().fullName();
         email = faker.internet().emailAddress();
         message = faker.food().ingredient();


        BrowserUtils.waitFor(1);
        contactPage.yourMsg.sendKeys(message);
        contactPage.fullName.sendKeys(name);
        contactPage.yourEmail.sendKeys(email);
        contactPage.acceptBox.click();
        contactPage.submit.click();
        BrowserUtils.waitFor(1);

    }

    @Then("Verify that user can see the {string} message on the page")
    public void verify_that_user_can_see_the_message_on_the_page(String message) {

        BrowserUtils.waitFor(2);
        System.out.println("contactPage = " + contactPage.alert.getText());
        Assert.assertEquals(message,contactPage.alert.getText());

    }
}
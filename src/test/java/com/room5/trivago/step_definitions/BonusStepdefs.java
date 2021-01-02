package com.room5.trivago.step_definitions;

import com.room5.trivago.pages.BonusPage;
import com.room5.trivago.utilities.BrowserUtils;
import com.room5.trivago.utilities.Driver;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class BonusStepdefs {

    BonusPage bonusPage = new BonusPage();

    JavascriptExecutor js = (JavascriptExecutor) Driver.get();

    ArrayList<Map<String, List<String>>> myList = new ArrayList<>();


    @When("user navigates to bonus page")
    public void user_navigates_to_bonus_page() {
        //bonusPage.readMeButton.click();
    }

    @Then("user click on read more button in the hero image")
    public void userClickOnReadMoreButtonInTheHeroImage() {
        BrowserUtils.waitFor(1);
        js.executeScript("arguments[0].click();", bonusPage.readMeButton);
        myList = (ArrayList) js.executeScript("return window.dataLayer");

    }

    @When("following {string} fired")
    public void followingFired(String event) {


        boolean resutl = false;
        myList = (ArrayList) js.executeScript("return window.dataLayer");

        for (int i = 0; i < myList.size(); i++) {

            if (myList.get(i).toString().contains(event)) {
                System.out.println("List of "+event+" = " + myList.get(i).entrySet());
                resutl=true;
                Assert.assertTrue(myList.get(i).toString().contains(event));

            }
        }

        Assert.assertTrue("Event not found", resutl);
    }


    @Then("verify {string} contains following {string}")
    public void verifyContainsFollowing(String event, String data) {

            boolean resutl = false;
            myList = (ArrayList) js.executeScript("return window.dataLayer");

            for (int i = 0; i < myList.size(); i++) {

                if (myList.get(i).toString().contains(event)) {
                    System.out.println("List of "+event+" = " + myList.get(i).entrySet());
                    resutl=true;
                    Assert.assertTrue(myList.get(i).entrySet().toString().contains(data));

                }
            }

            Assert.assertTrue("Event not found", resutl);
    }

    //


    @When("Check {string} is fired")
    public void checkIsFired(String event) {

        boolean resutl = false;
        myList = (ArrayList) js.executeScript("return window.dataLayer");

        for (int i = 0; i < myList.size(); i++) {

            if (myList.get(i).toString().contains(event)) {
                System.out.println("List of "+event+" = " + myList.get(i).entrySet());
                resutl=true;
                Assert.assertTrue(myList.get(i).toString().contains(event));

            }
        }

        Assert.assertTrue("Event not found", resutl);
    }

    @Then("verify when the {string} is fired check hotelIds are not null")
    public void verifyWhenTheIsFiredCheckHotelIdsAreNotNull(String event) {

        boolean resutl = false;
        myList = (ArrayList) js.executeScript("return window.dataLayer");

        for (int i = 0; i < myList.size(); i++) {

            if (myList.get(i).toString().contains(event)) {
                System.out.println("List of "+event+" = " + myList.get(i).entrySet());
                resutl=true;
                Assert.assertTrue(myList.get(i).toString().contains(event));

                String getHotelId = "function show_homepage() {" + "var homepage = dataLayer["+i+"]['hotelIds'];" + "return homepage;" + "}"
                        + "return show_homepage();";
                Object str = js.executeScript(getHotelId, "");
                List<?> list = BrowserUtils.convertObjectToList(str);

                System.out.println("Hotel IDs are as follows: " + list.toString());
                Assert.assertFalse(list.isEmpty());

            }
        }

        Assert.assertTrue("Event not found", resutl);

    }

    @And("verify when the {string} is fired {string} has the same path in the URL {string}")
    public void verifyWhenTheIsFiredHasTheSamePathInTheURL(String event, String arg1, String url) {

        boolean resutl = false;
        myList = (ArrayList) js.executeScript("return window.dataLayer");

        for (int i = 0; i < myList.size(); i++) {

            if (myList.get(i).toString().contains(event)) {
                System.out.println("List of "+event+" = " + myList.get(i).entrySet());
                resutl=true;
                Assert.assertTrue(myList.get(i).toString().contains(event));

                String getTargetProperties = "function show_homepage() {" + "var homepage = dataLayer["+i+"]['target-properties'];" + "return homepage;" + "}"
                        + "return show_homepage();";
                Object str = js.executeScript(getTargetProperties, "");

                System.out.println("str = " + str);

                Assert.assertTrue(url.endsWith(str.toString()));
                System.out.println("Target properties are as follows: " + str.toString());


            }
        }
    }
}
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

    ArrayList<Map<String, List<String>>> returnDataLayer = new ArrayList<>();

    @Then("user click on read more button in the hero image")
    public void userClickOnReadMoreButtonInTheHeroImage() {
        BrowserUtils.waitFor(1);
        js.executeScript("arguments[0].click();", bonusPage.readMeButton);

    }

    //3 Events and Data

    @When("following {string} fired")
    public void followingFired(String event) {

        boolean resutl = false;

        returnDataLayer = (ArrayList) js.executeScript("return window.dataLayer");

        for (int i = 0; i < returnDataLayer.size(); i++) {

            if (returnDataLayer.get(i).containsValue(event)) {

                System.out.println("List of events" + event + " = " + returnDataLayer.get(i).entrySet());
                resutl = true;
                Assert.assertTrue(returnDataLayer.get(i).containsValue(event));


            }
        }
        Assert.assertTrue("Event not found", resutl);
    }


    @Then("verify {string} contains following {string}")
    public void verifyContainsFollowing(String event, String data) {

        boolean flag = false;

        returnDataLayer = (ArrayList) js.executeScript("return window.dataLayer");

        for (int i = 0; i < returnDataLayer.size(); i++) {

            if (returnDataLayer.get(i).containsValue(event)) {

                flag = true;
                Assert.assertTrue(returnDataLayer.get(i).entrySet().toString().contains(data));

            }
        }
        Assert.assertTrue("Event not found", flag);
    }

    //Content Load event and HotelIds

    @When("Check {string} is fired")
    public void checkIsFired(String event) {

        boolean flag = false;

        returnDataLayer = (ArrayList) js.executeScript("return window.dataLayer");

        for (int i = 0; i < returnDataLayer.size(); i++) {

            if (returnDataLayer.get(i).containsValue(event)) {

                System.out.println("List of " + event + " = " + returnDataLayer.get(i).entrySet());
                flag = true;
                Assert.assertTrue(returnDataLayer.get(i).containsValue(event));

            }
        }
        Assert.assertTrue("Event not found", flag);
    }

    @Then("verify when the {string} is fired check hotelIds are not null")
    public void verifyWhenTheIsFiredCheckHotelIdsAreNotNull(String event) {

        boolean flag = false;

        returnDataLayer = (ArrayList) js.executeScript("return window.dataLayer");

        for (int i = 0; i < returnDataLayer.size(); i++) {

            if (returnDataLayer.get(i).containsValue(event)) {

                System.out.println("List of " + event + " event layer = " + returnDataLayer.get(i).entrySet());
                flag = true;
                Assert.assertTrue(returnDataLayer.get(i).containsValue(event));

                String getHotelId = "function show_homepage() {" + "var homepage = dataLayer[" + i + "]['hotelIds'];" + "return homepage;" + "}"
                        + "return show_homepage();";

                Object str = js.executeScript(getHotelId, "");
                List<?> list = BrowserUtils.convertObjectToList(str);

                System.out.println("Hotel IDs are as follows: " + list.toString());
                Assert.assertFalse(list.isEmpty());

            }
        }
        Assert.assertTrue("Event not found", flag);
    }

    @And("verify when the {string} is fired {string} has the same path in the same URL")
    public void verifyWhenTheIsFiredHasTheSamePathInTheSameURL(String event, String keyName) {

        boolean flag = false;
        String currentUrl = Driver.get().getCurrentUrl();

        returnDataLayer = (ArrayList) js.executeScript("return window.dataLayer");

        for (int i = 0; i < returnDataLayer.size(); i++) {

            if (returnDataLayer.get(i).containsValue(event)) {

                System.out.println("List of " + event + " = " + returnDataLayer.get(i).entrySet());
                flag = true;
                Assert.assertTrue(returnDataLayer.get(i).containsValue(event));

                String getTargetProperties = "function show_homepage() {" + "var homepage = dataLayer[" + i + "]['target-properties'];" + "return homepage;" + "}"
                        + "return show_homepage();";
                Object getTargetPropertiesValue = js.executeScript(getTargetProperties, "");

                Assert.assertTrue(returnDataLayer.get(i).containsKey(keyName));

                Assert.assertTrue(currentUrl.contains(getTargetPropertiesValue.toString()));
                System.out.println("Target properties value is as follows: " + getTargetPropertiesValue.toString());
            }
        }
        Assert.assertTrue("Event not found", flag);
    }
}
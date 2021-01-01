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
    ArrayList returnDataLayer = new ArrayList<>();
    ArrayList<Object> listNoNull = new ArrayList<>();

    @When("user navigates to bonus page")
    public void user_navigates_to_bonus_page() {
        //bonusPage.readMeButton.click();
    }

    @When("user click on Read more button in the Hero Image")
    public void userClickOnReadMoreButtonInTheHeroImage() {
        BrowserUtils.waitFor(1);
        js.executeScript("arguments[0].click();", bonusPage.readMeButton);
        returnDataLayer = (ArrayList) js.executeScript("return window.dataLayer");

    }

    @When("user opens the page verify following {string} are fired and contains following {string}")
    public void userOpensThePageVerifyFollowingAreFiredAndContainsFollowing(String event, String data) {

        for (int i = 0; i < returnDataLayer.size(); i++) {

            String location = "function show_homepage() {" + "var homepage = dataLayer[" + i + "].event;" + "return homepage;" + "}"
                    + "return show_homepage();";
            Object eventFired = js.executeScript(location, "");
            if (eventFired != null) {
                listNoNull.add(eventFired); // we collect all events in an arrayList which is not null
            }
        }


        int numberOfTimesAppeared = 0;

        for (int i = 0; i < listNoNull.size(); i++) {

            if (listNoNull.get(i).toString().equals(event)) {
                numberOfTimesAppeared++;
            }
        }

        Assert.assertTrue(numberOfTimesAppeared > 0);

        ArrayList<Map<String, List<String>>> myList = new ArrayList<>();
        JavascriptExecutor js = (JavascriptExecutor) Driver.get();

        myList = (ArrayList) js.executeScript("return window.dataLayer");

        for (int i = 0; i < myList.size(); i++) {

            if (myList.get(i).toString().contains(event)) {
                System.out.println("The parameters in the Event = " + myList.get(i).entrySet());
                Assert.assertTrue(myList.get(i).entrySet().toString().contains(data));
            }
        }
    }


    @And("verify {string} is fired and hotelId is not null")
    public void verifyIsFiredAndHotelIdIsNotNull(String event) {

        for (int i = 0; i < returnDataLayer.size(); i++) {

            String getEvent = "function show_homepage() {" + "var homepage = dataLayer[" + i + "].event;" + "return homepage;" + "}"
                    + "return show_homepage();";
            Object eventFired = js.executeScript(getEvent, "");
        }

        int numberOfTimesAppeared = 0;

        for (int i = 0; i < listNoNull.size(); i++) {

            if (listNoNull.get(i).toString().equals(event)) {
                numberOfTimesAppeared++;
            }
        }

        Assert.assertTrue(numberOfTimesAppeared > 0);

        String getHotelId = "function show_homepage() {" + "var homepage = dataLayer[12]['hotelIds'];" + "return homepage;" + "}"
                + "return show_homepage();";
        Object str = js.executeScript(getHotelId, "");
        List<?> list = BrowserUtils.convertObjectToList(str);

        System.out.println("Hotel IDs are as follows: " + list.toString());
        Assert.assertFalse(list.isEmpty());
    }


    @Then("{string} has the same path in the URL {string}")
    public void hasTheSamePathInTheURL(String path, String url) {

        String getTargetProperties = "function show_homepage() {" + "var homepage = dataLayer[12]['target-properties'];" + "return homepage;" + "}"
                + "return show_homepage();";
        Object str = js.executeScript(getTargetProperties, "");

        System.out.println("str = " + str);

        Assert.assertTrue(url.endsWith(str.toString()));
        System.out.println("Target properties are as follows: " + str.toString());

    }
}
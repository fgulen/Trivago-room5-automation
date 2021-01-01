package com.room5.trivago.pages;

import com.github.javafaker.Faker;
import com.room5.trivago.utilities.BrowserUtils;
import com.room5.trivago.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public abstract class BasePage {
    public BasePage() {
        PageFactory.initElements(Driver.get(), this);
    }

    @FindBy(xpath = "//div[@class='nav-icon']")
    public WebElement mainIcon;

    @FindBy(xpath = "//div[@class='menu-title'][normalize-space()='Destinations']")
    public WebElement destinations;

    @FindBy (xpath = "//div[contains(text(),'Themes')]")
    public WebElement themes;

    @FindBy(xpath = " //div[@class='swiper-slide menu-destination-card']/parent::*/div")
    public List<WebElement> destinationOptions;

    @FindBy(xpath = "//div[@class='search-icon']")
    public WebElement searchButton;

    @FindBy (css = ".input.search-input")
    public  WebElement searchInput;

    @FindBy (xpath = "//h3[@class='section-title']")
    public WebElement resultFound;

    @FindBy (xpath = "//h1[@class='section-title']")
    public WebElement resultNotFound;

}
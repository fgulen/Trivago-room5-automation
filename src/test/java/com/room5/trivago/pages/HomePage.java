package com.room5.trivago.pages;

import com.room5.trivago.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage{
    public HomePage(){
        PageFactory.initElements(Driver.get(), this);
    }

    @FindBy (xpath = "//a[normalize-space()='Contact']")
    public WebElement contact;

    @FindBy (xpath = "//select[@class='locales-dropdown locales-dropdown-v2']")
    public WebElement dropDownCountries;

    @FindBy (xpath = "//div[@class='newsletter-email']/input")
    public WebElement newsLetterMail;

    @FindBy (xpath = "//div[@class='newsletter-submit']/button")
    public WebElement newsLetterSubmit;

    @FindBy (xpath = "//p[@class='submitted h1']")
    public WebElement newsLetterAlert;

    @FindBy (xpath = "//div[@class='alert-error et-error']")
    public WebElement messageErrorAlert;

}

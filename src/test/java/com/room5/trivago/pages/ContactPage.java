package com.room5.trivago.pages;

import com.room5.trivago.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactPage extends BasePage {
    public ContactPage(){
        PageFactory.initElements(Driver.get(),this);
    }

    @FindBy (xpath = "//textarea[@class='contact-msg']")
    public WebElement yourMsg;

    @FindBy (xpath = "//input[@class='contact-input']")
    public WebElement fullName;

    @FindBy (id = "contact-email")
    public WebElement yourEmail;

    @FindBy (id = "confirm")
    public WebElement acceptBox;

    @FindBy (xpath = "//button[normalize-space()='Submit']")
    public WebElement submit;

    @FindBy (xpath = "//p[@class='feedback']")
    public WebElement alert;

}

package com.room5.trivago.pages;

import com.room5.trivago.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class BonusPage {

    public BonusPage(){
        PageFactory.initElements(Driver.get(),this);
    }

    @FindBy (xpath = "//div[@class='hero-button']")
    public WebElement readMeButton;



}


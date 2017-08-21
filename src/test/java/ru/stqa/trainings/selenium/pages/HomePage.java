package ru.stqa.trainings.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class HomePage extends Page{

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


    @FindBy(xpath = "//div[@id='logotype-wrapper']/a/img")
    public WebElement clickHomePage;

    @FindBy(xpath = "//div[@class='image-wrapper']/img[@class='image']")
    public WebElement duck;

    @FindBy(linkText = "Checkout »")
    public WebElement checkoutButton;

    @FindBy(xpath = "//span[contains(@class, 'quantity')]")
    public WebElement quantity;

}
package ru.stqa.trainings.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductPage extends Page {

    public ProductPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


    @FindBy(name = "add_cart_product")
    public WebElement addToCartButton;

    @FindBy(name = "options[Size]")
    public List<WebElement> sizeList;

    @FindBy(name = "options[Size]")
    public WebElement size;
}
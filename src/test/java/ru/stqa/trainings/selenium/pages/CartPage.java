package ru.stqa.trainings.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends Page{

    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


    @FindBy(xpath = "//div[@id='page']")
    public WebElement textMessageProductsRemoved;

    @FindBy(xpath = "//div[@id='box-checkout-summary']")
    public List<WebElement> orderSummaryTableList;

    @FindBy(xpath = "//div[@id='box-checkout-summary']")
    public WebElement orderSummaryTable;

    @FindBy(name = "remove_cart_item")
    public WebElement removeFromCartButton;
}
package ru.stqa.trainings.selenium.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import ru.stqa.trainings.selenium.pages.CartPage;
import ru.stqa.trainings.selenium.pages.HomePage;
import ru.stqa.trainings.selenium.pages.ProductPage;
import java.util.concurrent.TimeUnit;
import static org.openqa.selenium.support.ui.ExpectedConditions.stalenessOf;

public class Application {

    public WebDriverWait wait;
    public WebDriver driver;

    private HomePage homePage;
    private CartPage cartPage;
    private ProductPage productPage;

    public Application() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 1);

        homePage = new HomePage(driver);
        cartPage = new CartPage(driver);
        productPage = new ProductPage(driver);

    }


    public void openHomePage() {
        driver.navigate().to("http://localhost/litecart/en/");
    }

    public void goBackToHomePage() {
        homePage.clickHomePage.click();
    }

    public void sizeDropDown() {
        if (productPage.sizeList.size() != 0) {
            Select selector = new Select(productPage.size);
            selector.selectByVisibleText("Small");
        }
    }

    public void addProductToCart(int numberOfProductsToAdd) {
        for (int i = 0; i < numberOfProductsToAdd; i++) {
            int id = i + 1;
            goBackToHomePage();
            driver.findElement(By.xpath("//li[@class='product column shadow hover-light'][" + id + "]//div[@class='name']")).click();
            sizeDropDown();
            productPage.addToCartButton.click();
            wait.until(ExpectedConditions.textToBePresentInElement(homePage.quantity, Integer.toString(id)));
        }
    }

    public void checkout() {
        homePage.checkoutButton.click();
    }

    public void removeProductFromCart() {
        while (cartPage.orderSummaryTableList.size() != 0) {
            cartPage.removeFromCartButton.click();
            //Thread.sleep(3000);
            wait.until(stalenessOf(cartPage.orderSummaryTable));
        }
    }

    public void assertProductsAreRemovedByTextMessage() {
        Assert.assertTrue((cartPage.textMessageProductsRemoved).getText().contains("There are no items in your cart."));
    }
}
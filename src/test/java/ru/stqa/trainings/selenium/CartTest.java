package ru.stqa.trainings.selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import static org.openqa.selenium.support.ui.ExpectedConditions.stalenessOf;


public class CartTest extends TestBase {

    @Test
    public void testVerifyCart() {
        int numberOfProductsToAddToCart = 3;

        goToHomepage();

        int numberOfProducts = driver.findElements
                (By.xpath("//div[@id='box-most-popular']//li[@class='product column shadow hover-light']/a[@class='link']")).size();

        if (numberOfProductsToAddToCart > numberOfProducts) {
            System.out.println("Сейчас товаров на главной: " + numberOfProducts);
            System.out.println("Нужно добавить товаров: " + (numberOfProductsToAddToCart - numberOfProducts));
            stop();
        }

        for (int i = 0; i < numberOfProductsToAddToCart; i++) {
            goToHomepage();
            int id = i + 1;

            driver.findElement(By.xpath("//li[@class='product column shadow hover-light'][" + id + "]//div[@class='name']")).click();

            if (driver.findElements(By.name("options[Size]")).size() != 0) {
                Select selector = new Select(driver.findElement(By.name("options[Size]")));
                selector.selectByVisibleText("Small");
            }

            driver.findElement(By.name("add_cart_product")).click();
            wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.xpath("//span[contains(@class, 'quantity')]"))
                    , Integer.toString(id)));

        }

        driver.findElement(By.linkText("Checkout »")).click();

        while (driver.findElements(By.xpath("//div[@id='box-checkout-summary']")).size() != 0) {
            driver.findElement(By.name("remove_cart_item")).click();
            wait.until(stalenessOf(driver.findElement(By.xpath("//div[@id='box-checkout-summary']"))));
        }

        Assert.assertTrue(driver.findElement(By.xpath("//div[@id='page']")).getText().contains("There are no items in your cart."));
    }
}
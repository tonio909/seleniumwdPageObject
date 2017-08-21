package ru.stqa.trainings.selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import java.io.File;



public class AddProductTest extends TestBase {

    @Test
    public void testAddProduct() throws InterruptedException {

        String pathToPicture = new File("src/test/resources/duck.png").getAbsolutePath();
        String productName = "Black and White Duck " + System.currentTimeMillis();

        adminLogin();
        driver.navigate().to("http://localhost/litecart/admin/?app=catalog&doc=catalog");
        driver.findElement(By.xpath("//a[@class='button'][2]")).click();

        //вкладка General
        driver.findElement(By.xpath("//ul[@class='index']/li[1]/a")).click();
        driver.findElement(By.name("name[en]")).sendKeys(productName);
        driver.findElement(By.xpath("//div[@id='tab-general']//tr[1]/td/label[1]/input")).click();
        driver.findElement(By.name("code")).sendKeys("100");
        driver.findElement(By.xpath("//div[contains(@class, 'input-wrapper')]//tr[3]/td[1]/input")).click();
        driver.findElement(By.xpath("//tr[7]//div[contains(@class, 'input-wrapper')]//tr[3]/td[1]/input")).click();
        driver.findElement(By.name("quantity")).clear();
        driver.findElement(By.name("quantity")).sendKeys("5");
        driver.findElement(By.name("new_images[]")).sendKeys(pathToPicture);
        driver.findElement(By.name("date_valid_from")).sendKeys("01.01.2000");
        driver.findElement(By.name("date_valid_to")).sendKeys("01.01.2020");

        //вкладка Information
        driver.findElement(By.xpath("//ul[@class='index']/li[2]/a")).click();
        Select selector = new Select(driver.findElement(By.name("manufacturer_id")));
        selector.selectByVisibleText("ACME Corp.");
        driver.findElement(By.name("keywords")).sendKeys("black, white, duck");
        driver.findElement(By.name("short_description[en]")).sendKeys("Black & White Duck decription");
        driver.findElement(By.name("head_title[en]")).sendKeys("Head Title");
        driver.findElement(By.name("meta_description[en]")).sendKeys("Meta description");

        //вкладка Prices
        driver.findElement(By.xpath("//ul[@class='index']/li[4]/a")).click();
        driver.findElement(By.name("purchase_price")).clear();
        driver.findElement(By.name("purchase_price")).sendKeys("100");
        Select selectorPrice = new Select(driver.findElement(By.name("purchase_price_currency_code")));
        selectorPrice.selectByVisibleText("US Dollars");
        driver.findElement(By.name("save")).click();
        Thread.sleep(500);
        Assert.assertTrue(driver.findElement(By.xpath("//td[@id='content']")).getText().contains(productName));
    }
}
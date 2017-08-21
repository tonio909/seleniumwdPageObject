package ru.stqa.trainings.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import java.util.List;

public class BrowserLogTest extends TestBase {

    @Test
    public void testBrowserLog(){
        adminLogin();
        driver.navigate().to("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
        int productsSize = driver.findElements(By.xpath("//tr[@class='row']/td[3]/a")).size();
        for (int i = 0; i < productsSize; i++) {
            List<WebElement> products = driver.findElements(By.xpath("//tr[@class='row']/td[3]/a"));
            products.get(i).click();
            int logs = driver.manage().logs().get("browser").getAll().size();
            Assert.assertTrue(logs == 0);
            driver.navigate().to("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
        }
    }
}
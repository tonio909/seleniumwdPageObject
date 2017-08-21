package ru.stqa.trainings.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Set;


public class NewWindowTest extends TestBase {

    @Test
    public void testNewWindow() {
        adminLogin();
        driver.navigate().to("http://localhost/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.xpath("//tr[@class='row']//i[@class='fa fa-pencil']")).click();

        List<WebElement> links = driver.findElements(By.className("fa-external-link"));
        int numberOfLinks = links.size();

        for (int i = 0; i < numberOfLinks; i++) {

            String mainWindowBefore = driver.getWindowHandle();
            Set<String> allWindowsBefore = driver.getWindowHandles();

            driver.findElements(By.className("fa-external-link")).get(i).click();

            String mainWindowAfter = driver.getWindowHandle();
            Set<String> allWindowsAfter = driver.getWindowHandles();


            allWindowsAfter.removeAll(allWindowsBefore);
            mainWindowAfter = allWindowsAfter.iterator().next();

            driver.switchTo().window(mainWindowAfter);

            if (driver.findElements(By.xpath("//a[contains(@class, 'mw-wiki-logo')]")).size() != 0) {
                wait.until(ExpectedConditions.urlContains("wiki"));
            } else {
                wait.until(ExpectedConditions.urlContains("informatica"));
            }

            driver.close();
            driver.switchTo().window(mainWindowBefore);
            wait.until((ExpectedConditions.titleIs("Edit Country | My Store")));
        }
    }
}
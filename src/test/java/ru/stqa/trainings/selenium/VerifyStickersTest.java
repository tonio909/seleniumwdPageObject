package ru.stqa.trainings.selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

public class VerifyStickersTest extends TestBase {

    @Test
    public void testVerifyStickers() {
        goToHomepage();
        List<WebElement> WebElementsList = driver.findElements(By.xpath("//div[@class='image-wrapper']"));
        for (WebElement w : WebElementsList) {
            Assert.assertEquals(1, w.findElements(By.xpath(".//div[starts-with(@class,'sticker')]")).size());
        }
    }
}
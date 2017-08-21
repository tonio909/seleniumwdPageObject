package ru.stqa.trainings.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import java.util.List;



public class ClickAdminMenuTest extends TestBase {


    @Test
    public void testClickAdminLeftMenu() throws InterruptedException {

        adminLogin();

        List<WebElement> WebElementsListMain = driver.findElements(By.xpath("//*[@id='app-']"));
        int mainMenuSize = WebElementsListMain.size();
        for (int m = 0; m < mainMenuSize; m++) {
            Thread.sleep(500);
            List<WebElement> ListOfMainMenuItems = driver.findElements(By.xpath("//*[@id='app-']"));
            System.out.println(ListOfMainMenuItems.get(m).getText());
            ListOfMainMenuItems.get(m).click();
            Assert.assertTrue(driver.findElement(By.xpath("//*[@id='content']/h1")).isDisplayed());
            int subMenuSize = driver.findElements(By.xpath("//li[@id!='app-']//span[@class='name']")).size();
            if (subMenuSize != 0) {
                for (int s = 0; s < subMenuSize; s++) {
                    Thread.sleep(500);
                    List<WebElement> ListOfSubMenuItems = driver.findElements(By.xpath("//li[@id!='app-']//span[@class='name']"));
                    System.out.println("----- " + ListOfSubMenuItems.get(s).getText());
                    ListOfSubMenuItems.get(s).click();
                    Assert.assertTrue(driver.findElement(By.xpath("//*[@id='content']/h1")).isDisplayed());
                }
            } else {
                System.out.println("----- No submenu");
            }
        }

        adminLogout();

    }
}
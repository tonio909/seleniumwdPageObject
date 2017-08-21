package ru.stqa.trainings.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;

public class UserRegistrationTest extends TestBase {

    @Test
    public void testUserRegistration() throws InterruptedException {

        //регистрация новой учётной записи
        String loginName = "anton" + System.currentTimeMillis() + "@test.com";
        String password = "Qwerty1";

        goToHomepage();
        navigateToCreateAccountPage();
        driver.findElement(By.name("firstname")).sendKeys("Anton");
        driver.findElement(By.name("lastname")).sendKeys("Alekseev");
        driver.findElement(By.name("address1")).sendKeys("Test Street 1/2");
        driver.findElement(By.name("postcode")).sendKeys("12345");
        driver.findElement(By.name("city")).sendKeys("Saint-Petersburg");
        driver.findElement(By.xpath("//div[@class='content']/form/table/tbody/tr[5]/td[1]")).click();
        driver.findElement(By.xpath("//input[@class='select2-search__field']")).sendKeys("United States");
        driver.findElement(By.xpath("//input[@class='select2-search__field']")).sendKeys(Keys.ENTER);
        Select selector = new Select(driver.findElement(By.xpath("//div[@class='content']/form/table/tbody/tr[5]/td[2]/select")));
        selector.selectByVisibleText("Florida");
        driver.findElement(By.name("email")).sendKeys(loginName);
        driver.findElement(By.name("phone")).sendKeys("+79119998877");
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("confirmed_password")).sendKeys(password);
        driver.findElement(By.name("create_account")).click();

        //выход (logout), потому что после успешной регистрации автоматически происходит вход
        goToHomepage();
        logOutFromUserProfile();

        //повторный вход в только что созданную учётную запись
        login(loginName, password);

        //и ещё раз выход
        goToHomepage();
        logOutFromUserProfile();
    }
}
package ru.stqa.trainings.selenium;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CountriesZonesTest extends TestBase {

    @Test
    public void testCountriesSorting() {
        //Проверить, что страны расположены в алфавитном порядке
        adminLogin();
        goToCountriesPage();

        List<WebElement> countriesGetFromPage = driver.findElements(By.xpath("//div[@id='content-wrapper']//tr[@class='row']/td[5]"));
        int countriesSize = countriesGetFromPage.size();
        List<String> countriesListToSort = new ArrayList<String>();
        List<String> countriesListOriginal = new ArrayList<String>();


        for (WebElement w : countriesGetFromPage) {
            countriesListOriginal.add(w.getText());
        }

        for (WebElement w : countriesGetFromPage) {
            countriesListToSort.add(w.getText());
        }

        Collections.sort(countriesListToSort);
        Assert.assertEquals(countriesListOriginal, countriesListToSort);

        //Log countries
        for (int i = 0; i < countriesSize; i++) {
            System.out.println(countriesListOriginal.get(i));
        }

        for (int i = 0; i < countriesSize; i++) {
            System.out.println(countriesListToSort.get(i));
        }
    }

    @Test
    public void testZones() throws InterruptedException {
        //Для тех стран, у которых количество зон отлично от нуля -- открыть страницу этой страны и там проверить, что зоны расположены в алфавитном порядке
        adminLogin();
        goToCountriesPage();
        int countriesSize = driver.findElements(By.xpath("//div[@id='content-wrapper']//tr[@class='row']/td[6]")).size();
        for (int i = 0; i < countriesSize; i++) {
            List<WebElement> countriesFromPage = driver.findElements(By.xpath("//div[@id='content-wrapper']//tr[@class='row']/td[6]"));
            String countriesWithZones = countriesFromPage.get(i).getAttribute("textContent");
            if (!countriesWithZones.equals("0")) {
                int countryId = i + 1;
                driver.findElement(By.xpath("//div[@id='content-wrapper']//tr[@class='row'][" + countryId + "]/td[7]//i[@class='fa fa-pencil']")).click();

                List<WebElement> zonesGetFromPage = driver.findElements(By.xpath("//table[@id='table-zones']/tbody//td[3]"));

                List<String> zonesListToSort = new ArrayList<String>();
                List<String> zonesListOriginal = new ArrayList<String>();

                for (WebElement e : zonesGetFromPage) {
                    zonesListOriginal.add(e.getText());
                }

                for (WebElement e : zonesGetFromPage) {
                    zonesListToSort.add(e.getText());
                }

                Collections.sort(zonesListToSort);

                zonesListOriginal.remove(zonesListOriginal.size() - 1);
                zonesListToSort.remove(0);

                Assert.assertEquals(zonesListOriginal, zonesListToSort);

                int zonesSize = zonesListToSort.size();

                //Log zones
                for (int z = 0; z < zonesSize; z++) {
                    System.out.println(zonesListOriginal.get(z));
                }

                for (int z = 0; z < zonesSize; z++) {
                    System.out.println(zonesListToSort.get(z));
                }

                goToCountriesPage();

            }
        }
    }

    @Test
    public void testZones2() throws InterruptedException {
        //зайти в каждую из стран и проверить, что зоны расположены в алфавитном порядке
        adminLogin();
        goToZonesPage();
        int geoZonesSize = driver.findElements(By.xpath("//tr[@class='row']/td[4]")).size();
        for (int i = 0; i < geoZonesSize; i++) {
            int countryWithGeoZoneId = i + 1;
            driver.findElement(By.xpath("//tr[@class='row'][" + countryWithGeoZoneId + "]/td[5]//i[@class='fa fa-pencil']")).click();

            int geoZonesListSize = driver.findElements(By.xpath("//a[@id='remove-zone']/i[@class='fa fa-times-circle fa-lg']")).size();
            for (int z = 0; z < geoZonesListSize; z++) {

                //Работаем с выпадающими в цикле
                List<WebElement> geoZonesList = driver.findElements(By.xpath("//form/table[@id='table-zones']/tbody//td[3]/select"));

                List<String> geoZonesListToSort = new ArrayList<String>();
                List<String> geoZonesListOriginal = new ArrayList<String>();

                for (WebElement w : geoZonesList) {
                    geoZonesListOriginal.add(w.getText());
                }

                for (WebElement w : geoZonesList) {
                    geoZonesListToSort.add(w.getText());
                }

                Collections.sort(geoZonesListToSort);

                Assert.assertEquals(geoZonesListOriginal, geoZonesListToSort);

            }
            goToZonesPage();
        }
    }
}
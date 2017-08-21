package ru.stqa.trainings.selenium;

import org.testng.Assert;
import org.junit.Test;
import org.openqa.selenium.By;


public class VerifyProductsTest extends TestBase {

    @Test
    public void testVerifyProducts() throws InterruptedException {
        //на главной странице и на странице товара совпадает текст названия товара
        goToHomepage();
        String productNameHomePage = driver.findElement(By.xpath("//div[@id='box-campaigns']//div[@class='name']")).getText();
        driver.findElement(By.xpath("//div[@id='box-campaigns']//img[@class='image']")).click();
        String productNameProductPage = driver.findElement(By.xpath("//h1[@class='title']")).getText();
        Assert.assertEquals(productNameHomePage, productNameProductPage);

        //на главной странице и на странице товара совпадают цены (обычная и акционная)
        goToHomepage();
        String productPriceHomePage = driver.findElement(By.xpath("//div[@id='box-campaigns']//strong[@class='campaign-price']")).getText();
        driver.findElement(By.xpath("//div[@id='box-campaigns']//img[@class='image']")).click();
        String productPriceProductPage = driver.findElement(By.xpath("//strong[@class='campaign-price']")).getText();
        Assert.assertEquals(productPriceHomePage, productPriceProductPage);

        //обычная цена зачёркнутая и серая (можно считать, что "серый" цвет это такой, у которого в RGBa представлении одинаковые значения для каналов R, G и B)
        goToHomepage();
        String[] productRegularPriceColorHomePage = driver.findElement(By.xpath("//div[@id='box-campaigns']//s[@class='regular-price']")).getCssValue("color").split("\\, ");
        String redColorR1 = productRegularPriceColorHomePage[0].replaceAll("[a-zA-Z\\D]", "");
        String greenColorR1 = productRegularPriceColorHomePage[1];
        String blueColorR1 = productRegularPriceColorHomePage[2].replaceAll("[\\D]", "");
        Assert.assertEquals(redColorR1, greenColorR1, blueColorR1);
        System.out.println("Regular price color on homepage: " + redColorR1 + " " +  greenColorR1 + " " +  blueColorR1);

        openCampaignProductPage();
        String[] productRegularPriceColorProductPage = driver.findElement(By.xpath("//s[@class='regular-price']")).getCssValue("color").split("\\, ");
        String redColorR2 = productRegularPriceColorProductPage[0].replaceAll("[a-zA-Z\\D]", "");
        String greenColorR2 = productRegularPriceColorProductPage[1];
        String blueColorR2 = productRegularPriceColorProductPage[2].replaceAll("[\\D]", "");
        Assert.assertEquals(redColorR2, greenColorR2, blueColorR2);
        System.out.println("Regular price color on product page: " + redColorR2 + " " +  greenColorR2 + " " +  blueColorR2);

        //акционная жирная и красная (можно считать, что "красный" цвет это такой, у которого в RGBa представлении каналы G и B имеют нулевые значения)
        goToHomepage();
        String[] productSalePriceColorHomePage = driver.findElement(By.xpath("//div[@id='box-campaigns']//strong[@class='campaign-price']")).getCssValue("color").split("\\, ");
        String productSalePriceBoldHomePage = driver.findElement(By.xpath("//div[@id='box-campaigns']//strong[@class='campaign-price']")).getAttribute("tagName");
        Assert.assertEquals("STRONG", productSalePriceBoldHomePage);
        String redColorS1 = productSalePriceColorHomePage[0].replaceAll("[a-zA-Z\\D]", "");
        String greenColorS1 = productSalePriceColorHomePage[1];
        String blueColorS1 = productSalePriceColorHomePage[2].replaceAll("[\\D]", "");
        Assert.assertEquals("0", greenColorS1);
        Assert.assertEquals("0", blueColorS1);
        System.out.println("Sale price color on homepage: " + redColorS1 + " " +  greenColorS1 + " " +  blueColorS1);

        openCampaignProductPage();
        String[] productSalePriceColorProductPage = driver.findElement(By.xpath("//strong[@class='campaign-price']")).getCssValue("color").split("\\, ");
        String productSalePriceBoldProductPage = driver.findElement(By.xpath("//strong[@class='campaign-price']")).getAttribute("tagName");
        Assert.assertEquals("STRONG", productSalePriceBoldProductPage);
        String redColorS2 = productSalePriceColorProductPage[0].replaceAll("[a-zA-Z\\D]", "");
        String greenColorS2 = productSalePriceColorProductPage[1];
        String blueColorS2 = productSalePriceColorProductPage[2].replaceAll("[\\D]", "");
        Assert.assertEquals("0", greenColorS2);
        Assert.assertEquals("0", blueColorS2);
        System.out.println("Sale price color on product page: " + redColorS2 + " " +  greenColorS2 + " " +  blueColorS2);

        //акционная цена крупнее, чем обычная
        goToHomepage();
        int productSalePriceSizeHomePageHeight = driver.findElement(By.xpath("//div[@id='box-campaigns']//strong[@class='campaign-price']")).getSize().height;
        int productSalePriceSizeHomePageWidth = driver.findElement(By.xpath("//div[@id='box-campaigns']//strong[@class='campaign-price']")).getSize().width;
        int productRegularPriceSizeHomePageHeight = driver.findElement(By.xpath("//div[@id='box-campaigns']//s[@class='regular-price']")).getSize().height;
        int productRegularPriceSizeHomePageWidth = driver.findElement(By.xpath("//div[@id='box-campaigns']//s[@class='regular-price']")).getSize().width;
        Assert.assertTrue(productSalePriceSizeHomePageHeight > productRegularPriceSizeHomePageHeight);
        Assert.assertTrue(productSalePriceSizeHomePageWidth > productRegularPriceSizeHomePageWidth);
    }
}
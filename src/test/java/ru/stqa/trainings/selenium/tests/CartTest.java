package ru.stqa.trainings.selenium.tests;

import org.junit.Test;


public class CartTest extends TestBase {

    @Test
    public void testVerifyCart() {

        int numberOfProductsToAddToCart = 3;

        app.openHomePage();
        app.addProductToCart(numberOfProductsToAddToCart);
        app.checkout();
        app.removeProductFromCart();
        app.assertProductsAreRemovedByTextMessage();
    }
}
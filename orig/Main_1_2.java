package com.example;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Main_1_2 {
  public static void main(String[] args) {
    //Step 1
    ChromeDriver driver = new ChromeDriver();
    driver.manage().window().maximize();
    // Explicit wait, jei reikia daugiau parametru - naudoti FluentWait (retai aptinkamas, labiau klausimas kuri uzduoda interviewers)
    WebDriverWait waitTimer = new WebDriverWait(driver, Duration.ofSeconds(10));  
    driver.get("https://demowebshop.tricentis.com/");

    //Step 2
    WebElement giftCardButton = driver.findElement(By.xpath("/descendant::a[contains(text(), 'Gift Cards')]"));
    giftCardButton.click();

    //Step 3
    WebElement product = driver.findElement(
      By.xpath( "/descendant::div[@class = 'product-grid']" +
                "/descendant::div[@class = 'prices'][number() > 99]" +
                "/ancestor::div[@class = 'item-box']" +
                "/descendant::a"));
    product.click();

    //Step 4
    WebElement recipientName = driver.findElement(By.xpath("/descendant::input[@class = \'recipient-name\']"));
    WebElement yourName = driver.findElement(By.xpath("/descendant::input[@class = \'sender-name\']"));
    recipientName.sendKeys("My friend");
    yourName.sendKeys("My name");

    //Step 5
    WebElement quantity = driver.findElement(By.xpath("/descendant::input[contains(@class, \'qty-input\')]"));
    quantity.clear();
    quantity.sendKeys("5000");

    //Step 6
    WebElement addToCartButton = driver.findElement(By.xpath("/descendant::input[contains(@class, \'add-to-cart-button\')]"));
    addToCartButton.click();
    waitTimer.until(ExpectedConditions.invisibilityOfElementLocated(
      By.xpath("/descendant::div[@class = \'ajax-loading-block-window\']")));
    
    //Step 7
    WebElement addToWishlistButton = driver.findElement(By.xpath("/descendant::input[contains(@class, \'add-to-wishlist-button\')]"));
    addToWishlistButton.click();
    waitTimer.until(ExpectedConditions.invisibilityOfElementLocated(
      By.xpath("/descendant::div[@class = \'ajax-loading-block-window\']")));
      
    //Step 8
    WebElement barNotifExit = driver.findElement(By.xpath("/descendant::div[contains(@id, \'bar-notification\')]/descendant::span[contains(@class, \'close\')]"));
    barNotifExit.click();
    WebElement jewelry = driver.findElement(By.xpath("/descendant::div[contains(@class, \'block-category-navigation\')]/descendant::a[contains(text(), \'Jewelry\')]"));
    jewelry.click();

    //Step 9
    WebElement createJewelery = driver.findElement(By.xpath("/descendant::div[contains(@class, \'product-grid\')]/descendant::a[contains(text(), \'Create Your Own Jewelry\')]"));
    createJewelery.click();
    
    //Step 10
    WebElement material = driver.findElement(By.xpath("/descendant::select/descendant::option[contains(text(), \'Silver\') and contains(text(), \'1 mm\')]"));
    WebElement length = driver.findElement(By.xpath("/descendant::dt/label[contains(text(), \'Length in cm\')]/ancestor::dt/following::dd/input[contains(@type, \'text\')]"));
    WebElement pendant = driver.findElement(By.xpath("/descendant::ul[contains(@class, \'option-list\')]/descendant::label[contains(text(), \'Star\')]"));
    material.click();
    length.sendKeys("80");
    pendant.click();

    //Step 11
    quantity = driver.findElement(By.xpath("/descendant::input[contains(@class, \'qty-input\')]"));
    quantity.clear();
    quantity.sendKeys("26");
    
    //Step 12
    addToCartButton = driver.findElement(By.xpath("/descendant::input[contains(@class, \'add-to-cart-button\')]"));
    addToCartButton.click();
    waitTimer.until(ExpectedConditions.invisibilityOfElementLocated(
      By.xpath("/descendant::div[@class = \'ajax-loading-block-window\']")));
       
    //Step 13
    addToWishlistButton = driver.findElement(By.xpath("/descendant::input[contains(@class, \'add-to-wishlist-button\')]"));
    addToWishlistButton.click();
    waitTimer.until(ExpectedConditions.invisibilityOfElementLocated(
      By.xpath("/descendant::div[@class = \'ajax-loading-block-window\']")));
    
    //Step 14
    WebElement wishlist = driver.findElement(By.xpath("/descendant::span[contains(text(), \'Wishlist\')]/parent::a"));
    wishlist.click();

    //Step 15
    List<WebElement> addToCarts = driver.findElements(By.xpath("/descendant::input[contains(@type, \'checkbox\') and contains(@name, \'addtocart\')]"));
    for(WebElement el : addToCarts)
      el.click();
    
    //Step 16
    addToCartButton = driver.findElement(By.xpath("/descendant::input[contains(@type, \'submit\') and contains(@value, \'Add to cart\')]"));
    addToCartButton.click();
    waitTimer.until(ExpectedConditions.invisibilityOfElementLocated(
      By.xpath("/descendant::div[@class = \'ajax-loading-block-window\']")));

    //Step 17
    WebElement shoppingCart = driver.findElement(By.xpath("/descendant::span[contains(@class, \'cart-label\')]/parent::a"));
    shoppingCart.click();
    WebElement subTotal = driver.findElement(By.xpath("/descendant::span[contains(@class, \'product-price\')]"));
    
    if(Double.parseDouble(subTotal.getText()) != 1002600) 
      throw new RuntimeException("Bad price detected");

    driver.quit();
    System.exit(0);
  }
}

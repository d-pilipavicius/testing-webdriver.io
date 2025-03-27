package com.example;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Main_2_2 {
//		Užduotis 2.2:
//		1. Atsidaryti https://demoqa.com/ +
//		2. Uždaryti Cookies sutikimo langą. +
//		3. Pasirinkti "Elements" kortelę, +
//		4. Pasirinkti meniu punktą "Web Tables" +
//		5. Pridėti pakankamai elementų, kad atsirastų antras puslapis puslapiavime +
//		6. Pasirinkti antrą puslapį paspaudus "Next" +
//		7. Ištrinti elementą antrajame puslapyje +
//		8. Įsitikinti, kad automatiškai puslapiavimas perkeliamas į pirmąjį puslapį ir kad puslapių skaičius sumažėjo ligi vieno. 
  public static void main(String[] args) {
    ChromeDriver driver = new ChromeDriver();
    JavascriptExecutor js = (JavascriptExecutor) driver;
    WebDriverWait waitTimer = new WebDriverWait(driver, Duration.ofSeconds(10));
    driver.manage().window().maximize();

    driver.get("https://web.archive.org/web/20240112153757/https://demoqa.com/");

    WebElement elements = driver.findElement(By.xpath("/descendant::div[contains(@class, 'mt-4')]//*[contains(text(), 'Elements')]"));
    js.executeScript("arguments[0].scrollIntoView(true);", elements);
    waitTimer.until(ExpectedConditions.elementToBeClickable(elements));
    elements.click();

    WebElement webTables = driver.findElement(By.xpath("/descendant::ul[contains(@class, 'menu-list')]//*[contains(text(), 'Web Tables')]"));
    js.executeScript("arguments[0].scrollIntoView(true);", webTables);
    webTables.click();

    WebElement totalPages = driver.findElement(By.xpath("/descendant::span[contains(@class, '-totalPages')]"));
    
    
    new WebDriverWait(driver, Duration.ofSeconds(60)).until(driverInst -> {
      if(Integer.parseInt(totalPages.getText()) > 1)
        return true;

      WebElement add = driver.findElement(By.xpath("/descendant::button[contains(@id, 'addNewRecordButton')]"));
      add.click();
      WebElement firstName = driver.findElement(By.xpath("/descendant::input[contains(@id, 'firstName')]"));
      WebElement lastName = driver.findElement(By.xpath("/descendant::input[contains(@id, 'lastName')]"));
      WebElement email = driver.findElement(By.xpath("/descendant::input[contains(@id, 'userEmail')]"));
      WebElement age = driver.findElement(By.xpath("/descendant::input[contains(@id, 'age')]"));
      WebElement salary = driver.findElement(By.xpath("/descendant::input[contains(@id, 'salary')]"));
      WebElement department = driver.findElement(By.xpath("/descendant::input[contains(@id, 'department')]"));
  
      firstName.sendKeys("Name"+Math.random());
      lastName.sendKeys("Last"+Math.random());
      email.sendKeys("emname"+Math.random()+"@email.com");
      age.sendKeys(Integer.toString((int)Math.random()));
      salary.sendKeys(Integer.toString((int)Math.random()*1000));
      department.sendKeys("Deprtmnt"+Math.random());
  
      WebElement send = driver.findElement(By.xpath("/descendant::button[contains(@id, 'submit')]"));
      js.executeScript("arguments[0].scrollIntoView(true);", send);
      send.click();
      waitTimer.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("/descendant::div[contains(@role, 'dialog')]")));
      
      return Integer.parseInt(totalPages.getText()) > 1;
    });

    WebElement next = driver.findElement(By.xpath("/descendant::button[contains(text(), 'Next')]"));
    js.executeScript("arguments[0].scrollIntoView(true)", next);
    waitTimer.until(ExpectedConditions.elementToBeClickable(next));
    next.click();

    WebElement delete = driver.findElement(By.xpath("/descendant::span[contains(@id, 'delete-record')]"));
    js.executeScript("arguments[0].scrollIntoView(true);", delete);
    delete.click();

    WebElement pageJump = driver.findElement(By.xpath("/descendant::div[contains(@class, '-pageJump')]/input"));

    if (Integer.parseInt(pageJump.getDomAttribute("value")) != 1)
      throw new RuntimeException("Page did not return to the first one");
    if (Integer.parseInt(totalPages.getText()) != 1)
      throw new RuntimeException("Second page was not removed");

    driver.quit();
    System.exit(0);
  }
}

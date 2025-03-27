package com.example;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Main_2_1 {
//	Užduotis 2.1:
//		1. Atsidaryti https://demoqa.com/ +
//		2. Uždaryti Cookies sutikimo langą. skip
//		3. Pasirinkti "Widgets" kortelę +
//		4. Pasirinkti meniu punktą "Progress Bar" +
//		5. Spausti mygtuką "Start" +
//		6. Sulaukti kol bus 100% ir paspausti "Reset"
//		7. Įsitikinti, kad progreso eilutė tuščia (0%). +
//

  public static void main(String[] args) {
    ChromeDriver driver = new ChromeDriver();
    JavascriptExecutor js = (JavascriptExecutor) driver;
    WebDriverWait waitTimer = new WebDriverWait(driver, Duration.ofSeconds(10));

    driver.manage().window().maximize();

    driver.get("https://web.archive.org/web/20240112153757/https://demoqa.com/");

    WebElement widgets = driver.findElement(By.xpath("/descendant::div[contains(@class, 'mt-4')]//*[contains(text(), 'Widgets')]/ancestor::div[contains(@class, 'top-card')]"));
    js.executeScript("arguments[0].scrollIntoView(true);", widgets);
    waitTimer.until(ExpectedConditions.elementToBeClickable(widgets));
    widgets.click();

    WebElement progressBar = driver.findElement(By.xpath("/descendant::ul[contains(@class, 'menu-list')]//*[contains(text(), 'Progress Bar')]"));
    js.executeScript("arguments[0].scrollIntoView(true);", progressBar);
    progressBar.click();

    waitTimer.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/descendant::div[contains(@id, 'progressBarContainer')]//button[contains(text(), 'Start')]")));
    WebElement start = driver.findElement(By.xpath("/descendant::div[contains(@id, 'progressBarContainer')]//button[contains(text(), 'Start')]"));
    js.executeScript("arguments[0].scrollIntoView(true);", start);
    waitTimer.until(ExpectedConditions.elementToBeClickable(start));
    start.click();

    WebElement loadingBar = driver.findElement(By.xpath("/descendant::div[contains(@role, 'progressbar')]"));
    js.executeScript("arguments[0].scrollIntoView(true);", loadingBar);
    new WebDriverWait(driver, Duration.ofSeconds(99)).until(ExpectedConditions.textToBe(By.xpath("/descendant::div[contains(@role, 'progressbar')]"), "100%"));

    WebElement resetButton = driver.findElement(By.xpath("/descendant::button[contains(text(), 'Reset')]"));
    js.executeScript("arguments[0].scrollIntoView(true);", resetButton);
    resetButton.click();

    if(!loadingBar.getText().equals("0%")) 
      throw new RuntimeException("Bad percentage detected!");

    driver.quit();
    System.exit(0);
  }
}

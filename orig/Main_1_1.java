package com.example;

import org.openqa.selenium.chrome.ChromeDriver;

public class Main_1_1 {
    public static void main(String[] args) {
        ChromeDriver driver = new ChromeDriver();

        driver.get("https://www.google.com");
        
        // Uzdaro viena tab'a, taciau uzdarant paskutini tab'a yra bug'as
        // driver.close();

        // Uzdaro visus tab'us
        driver.quit();
    }
}
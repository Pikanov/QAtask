package com.QAtask;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GoogleSearchLinkInTopFive {

    @Test
    public void firstTest() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/chromedriver.exe");

        ChromeDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();

        driver.get("https://www.google.com/");

        String pageURL = driver.getCurrentUrl();
        assertEquals(pageURL, "https://www.google.com/");

        driver.findElement(By.name("q")).sendKeys("The Ringer");
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);

        boolean desiredLinkInTopFive = false;

        for (int i = 0; i < 4; i++) {
            WebElement clickNextPage = driver.findElement(By.id("pnnext"));
            clickNextPage.click();
            List<WebElement> links = driver.findElements(By.tagName("a"));
            for (WebElement link : links) {
                if (link.getText().contains("www.cnn.com")) {
                    desiredLinkInTopFive = true;
                    link.getAttribute("href").contains("www.cnn.com");
                    link.click();
                    break;
                }
            }
            if (desiredLinkInTopFive) {
                break;
            }

        }

        assertTrue(desiredLinkInTopFive);

        driver.close();
    }
}

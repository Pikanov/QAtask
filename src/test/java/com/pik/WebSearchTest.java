package com.pik;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static com.pik.BrowserSelector.driverForBrowsers;

public class WebSearchTest {

    @BeforeClass
    public static void setupBrowser() {
        BrowserSelector.initialization();
    }

    @Test
    public void ShouldFindLinkOnFirstPages() throws InterruptedException {
        driverForBrowsers().get(ConstantVariable.SearchPage);
        assertEquals(driverForBrowsers().getCurrentUrl(), ConstantVariable.SearchPage);
        System.out.println("Current URL - " + driverForBrowsers().getCurrentUrl());
        Thread.sleep(3000);

        driverForBrowsers().findElement(By.cssSelector(Locator.HomePageSearchInputField)).sendKeys("The Ringer");
        driverForBrowsers().findElement(By.cssSelector(Locator.HomePageSearchInputField)).sendKeys(Keys.ENTER);
        driverForBrowsers().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        boolean linkContains = false;

        for (int i = 0; i < 4; i++) {
            List<WebElement> links = driverForBrowsers().findElements(By.tagName(Locator.SearchResultPageFindAllLinks));

            for (WebElement link : links) {
                driverForBrowsers().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                if (link.getText().contains(Locator.desiredLink)) {
                    linkContains = true;
                    link.getAttribute(Locator.SearchResultPageFindContainURL).contains(Locator.desiredLink);
                    link.click();
                    break;
                }
            }

            if (linkContains) {
                break;
            }
            driverForBrowsers().findElement(By.cssSelector(Locator.SearchResultPageButtonNextPage)).click();
        }
        System.out.println("Desired link was found - " + linkContains);
        assertTrue(linkContains);
    }

    @AfterClass
    public static void tearDown() {
        driverForBrowsers().quit();
    }
}
//TODO 1 твой метод не останавливается если находит то, что надо на первой странице
//TODO 2 а если я скажу на первый 200 страниц, то мы будем ждать пол часа даже если уже все нашли на первой странице?
//TODO 3 еще, твой код будет падать если скорость интернета слабая или приложения медленно грузится
//TODO 4 assertTrue(desiredLinkInTopFive); - при падении просто будет говорить что он фолс, а что фолс не понятно
//TODO 6 System.setProperty("webdriver.chrome.driver", "/chromedriver.exe"); - а если я завтра скажу сделать +100500 тестов, ты везде будешь писать одну и ту строчку?
//TODO 7 так а где драйвер у тебя лежит?
//TODO 8 driver.get("https://www.google.com/"); - я завтра говорю надо +100500 тестов, а потом говорю, слушай, у нас урл поменялся теперь на яндексе надо все. Будешь руками все исправлять?
//TODO 9 assertEquals(pageURL, "https://www.google.com/"); - тож самое
//TODO 10 WebElement clickNextPage = driver.findElement(By.id("pnnext")); - а если у нас 100500 тестов, и поменялись локаторы у елементов
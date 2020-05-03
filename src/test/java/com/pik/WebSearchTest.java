package com.pik;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.pik.BrowserSelector.driverForBrowsers;
import static com.pik.BrowserSelector.getPropertiesFile;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class WebSearchTest {

    @BeforeClass
    public static void setupBrowser() {
        BrowserSelector.initialization();
    }

    @Test
    public void ShouldFindLinkOnFirstPages() {

        driverForBrowsers().get(getPropertiesFile().getProperty("SearchPage"));
        assertEquals(driverForBrowsers().getCurrentUrl(), getPropertiesFile().getProperty("SearchPage"));

        System.out.println("Current URL - " + driverForBrowsers().getCurrentUrl());

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
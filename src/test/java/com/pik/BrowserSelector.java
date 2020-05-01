package com.pik;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;

public class BrowserSelector {
    public static WebDriver driver = null;

    public static void initialization() {
        if(driver == null) {
            if(ConstantVariable.browserName.equalsIgnoreCase("Chrome")) {
                System.setProperty("webdriver.chrome.driver", "D:\\Utils_Dev\\webdrivers\\chromedriver.exe");
                driver=new ChromeDriver();
            } else if(ConstantVariable.browserName.equalsIgnoreCase("Firefox")) {
                System.setProperty("webdriver.gecko.driver", "D:\\Utils_Dev\\webdrivers\\geckodriver.exe");
                driver=new FirefoxDriver();
            } else if(ConstantVariable.browserName.equalsIgnoreCase("Edge")) {
                System.setProperty("webdriver.edge.driver", "D:\\Utils_Dev\\webdrivers\\msedgedriver.exe");
                driver=new EdgeDriver();
            } else if(ConstantVariable.browserName.equalsIgnoreCase("Opera")) {
                System.setProperty("webdriver.opera.driver", "D:\\Utils_Dev\\webdrivers\\operadriver.exe");
                driver=new OperaDriver();
            }
        }
        if(driver != null) {
            driver.manage().deleteAllCookies();
            driver.manage().window().maximize();
        }
    }

    public static WebDriver driverForBrowsers(){
        initialization();
        return driver;
    }
}

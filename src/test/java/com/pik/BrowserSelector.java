package com.pik;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BrowserSelector {
    public static WebDriver driver = null;

    public static void initialization() {
        if(driver == null) {
            if(getPropertiesFile().getProperty("browserName").equalsIgnoreCase("Chrome")) {
                System.setProperty("webdriver.chrome.driver", "src/main/resources/webdrivers/chromedriver.exe");
                driver=new ChromeDriver();
            } else if(getPropertiesFile().getProperty("browserName").equalsIgnoreCase("Firefox")) {
                System.setProperty("webdriver.gecko.driver", "src/main/resources/webdrivers/geckodriver.exe");
                driver=new FirefoxDriver();
            } else if(getPropertiesFile().getProperty("browserName").equalsIgnoreCase("Edge")) {
                System.setProperty("webdriver.edge.driver", "src/main/resources/webdrivers/msedgedriver.exe");
                driver=new EdgeDriver();
            } else if(getPropertiesFile().getProperty("browserName").equalsIgnoreCase("Opera")) {
                System.setProperty("webdriver.opera.driver", "src/main/resources/webdrivers/operadriver.exe");
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

    public static Properties getPropertiesFile() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src/main/resources/config.properties"));
        } catch (IOException e) {
            System.err.println("properties file not found");
        }
        return properties;
    }
}

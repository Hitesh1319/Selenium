package Test;

import net.jodah.failsafe.internal.util.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestSouceDemo {
    static WebDriver driver;
    public static void setup(){
        System.setProperty("Webdriver.chrome.driver","chromedriver.exe");
        driver =new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com");
    }
    public static void closeBrowser(){
        driver.quit();
    }
    public static void login(){
        driver.findElement(By.name("user-name")).sendKeys("standard_user");
        driver.findElement(By.name("password")).sendKeys("secret_sauce");
        driver.findElement(By.name("login-button")).click();
    }
    public static void verification(String locator,String expected){
        String actual = driver.findElement(By.xpath(locator)).getText();
        Assert.isTrue(actual.contains(expected),"Expected condition needed");
    }
    public static void addToCart(){
        driver.findElement(By.className("shopping_cart_link")).click();
        driver.findElement(By.id("continue-shopping")).click();
    }
    public static void item(){
        driver.findElement(By.className("inventory_item_name")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.id("back-to-products")).click();
        driver.findElement(By.xpath("//div[contains(text(),'Test.allTheThings() T-Shirt (Red)')]")).click();
    }
    public static void main(String[]args) {
        setup();
        login();
        verification("//span[@class='title']","Products");
        item();
        addToCart();
        closeBrowser();
    }
}

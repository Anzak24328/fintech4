package ru.autotest;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.testng.Assert.assertEquals;

public class TMobileWebTest extends BaseRunner {

    @Test
    public void testCaseByCSS() throws InterruptedException {
        System.out.println("TestCaseByCSS: start:");
        driver.get(baseUrl);
        driver.findElement(By.cssSelector("#lst-ib")).click();
        driver.findElement(By.cssSelector("#lst-ib")).sendKeys("tinkoff");
        driver.findElement(By.cssSelector("#lst-ib")).sendKeys(Keys.ENTER);
        driver.findElement(By.cssSelector("a[href='https://www.tinkoff.ru/']")).click();
        Thread.sleep(500);
        ArrayList<String> tab = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tab.get(0)).close();
        driver.switchTo().window(tab.get(1));
        driver.findElement(By.cssSelector("div[id=x48761] div:nth-child(6)")).click();
        driver.findElement(By.cssSelector("#search-and-pay-container input[data-qa-file='SearchInput']")).click();
        driver.findElement(By.cssSelector("#search-and-pay-container input[data-qa-file='SearchInput']")).sendKeys("Тинькофф мобайл");
        driver.findElement(By.cssSelector("#search-and-pay-container div[style*='tinkoff-mobile.png']")).click();
        new WebDriverWait(driver, 5).until(d -> d.getTitle().equals("Оплатить мобильную связь"));
        driver.findElement(By.cssSelector("div[data-qa-file='StatelessInput'] > input[data-qa-node='ValueComponent']")).click();
        driver.findElement(By.cssSelector("div[data-qa-file='StatelessInput'] > input[data-qa-node='ValueComponent']")).sendKeys("5");
        driver.findElement(By.cssSelector("button[data-qa-file='UIButton'] > h2[data-qa-file='UIButton']")).click();
        assertEquals(driver.findElement(By.cssSelector("div[class*='phone'] div[data-qa-file='FormFieldWrapper'] > div[data-qa-file='UIFormRowError']")).getText(), "Поле обязательное");
        assertEquals(driver.findElement(By.cssSelector("div[class*='amount'] div[data-qa-file='FormFieldWrapper'] > div[data-qa-file='UIFormRowError']")).getText(), "Минимум — 10 ₽");
        System.out.println("TestCaseByCSS: complete.");
    }

    @Test
    public void testCaseByXPath() throws InterruptedException {
        System.out.println("TestCaseByXPath: start:");
        driver.get(baseUrl);
        driver.findElement(By.xpath("//*[@id='lst-ib']")).click();
        driver.findElement(By.xpath("//*[@id='lst-ib']")).sendKeys("tinkoff");
        driver.findElement(By.xpath("//*[@id='lst-ib']")).sendKeys(Keys.ENTER);
        driver.findElement(By.xpath("//*[@href='https://www.tinkoff.ru/']")).click();
        Thread.sleep(500);
        ArrayList<String> tab = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tab.get(0)).close();
        driver.switchTo().window(tab.get(1));
        driver.findElement(By.xpath("//*[@id='x48761']//div[6]")).click();
        driver.findElement(By.xpath("//*[@id='search-and-pay-container']//input[@data-qa-file='SearchInput']")).click();
        driver.findElement(By.xpath("//*[@id='search-and-pay-container']//input[contains(@data-qa-file,'SearchInput')]")).sendKeys("Тинькофф мобайл");
        driver.findElement(By.xpath("//*[@id='search-and-pay-container']//div[contains(@style,'tinkoff-mobile.png')]")).click();
        new WebDriverWait(driver, 5).until(d -> d.getTitle().equals("Оплатить мобильную связь"));
        driver.findElement(By.xpath("//div[@data-qa-file='StatelessInput']/input[@data-qa-node='ValueComponent']")).click();
        driver.findElement(By.xpath("//div[@data-qa-file='StatelessInput']/input[@data-qa-node='ValueComponent']")).sendKeys("5");
        driver.findElement(By.xpath("//button[@data-qa-file='UIButton']/h2[@data-qa-file='UIButton']")).click();
        assertEquals(driver.findElement(By.xpath("//div[contains(@class,'phone')]//div[@data-qa-file='FormFieldWrapper']/div[@data-qa-file='UIFormRowError']")).getText(), "Поле обязательное");
        assertEquals(driver.findElement(By.xpath("//div[contains(@class,'amount')]//div[@data-qa-file='FormFieldWrapper']/div[@data-qa-file='UIFormRowError']")).getText(), "Минимум — 10 ₽");
        System.out.println("TestCaseByXPath: complete.");
    }



}

package testcases;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.BaseTest;

public class TestCases extends BaseTest {

	SoftAssert softAssert = new SoftAssert();
	
	@Test
	public void verifyTestCases() throws InterruptedException {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		
		wait.until(ExpectedConditions.elementToBeClickable(By.id(loc.getProperty("search_field"))));
		driver.findElement(By.id(loc.getProperty("search_field"))).sendKeys("TSLA");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty("search_by"))));
		WebElement searchBy = driver.findElement(By.xpath(loc.getProperty("search_by")));
		
		/*** Use Case1: Validation for search by Symbol ***/
		softAssert.assertEquals(searchBy.getText(), "Symbols","not searching by stock symbol");
		System.out.println("Validated for search by Symbol");
		
		WebElement autoListFirst = driver.findElement(By.xpath(loc.getProperty("auto_list_first")));
		
		/*** Use Case2: Validation for first autosuggested entry ***/
		softAssert.assertEquals(autoListFirst.getText(), "Tesla, Inc.","first autosuggested entry is not as expected");
		System.out.println("Validated for first autosuggested entry");
		
		autoListFirst.click();
		wait.until(ExpectedConditions.titleContains("TSLA"));
		/*** Use Case3: Validation for clicking on first autosuggested entry ***/
		softAssert.assertEquals(driver.getTitle(), "Tesla, Inc. (TSLA) Stock Price, News, Quote & History - Yahoo Finance","Expected stock page not opening");
		System.out.println("Validated for clicking on first autosuggested entry");
		
		WebElement stockPrice = driver.findElement(By.xpath(loc.getProperty("stock_price")));
		float stPrice=Float.parseFloat(stockPrice.getText());
		
		/*** Use Case4: Validation for stock price ***/
		if(stPrice>200.00)
			softAssert.assertTrue(true);
		else
			softAssert.assertTrue(false);
		System.out.println("Validated for stock price");
		
		/*** Use Case5: Capturing additional data and printing on console ***/
		WebElement previousClose = driver.findElement(By.xpath(loc.getProperty("previous_close")));
		System.out.println("Previous Close: "+previousClose.getText());
		
		WebElement volume = driver.findElement(By.xpath(loc.getProperty("volume")));
		System.out.println("Volume: "+volume.getText());
		
		softAssert.assertAll();
	}
	
}

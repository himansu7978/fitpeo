package com.fitpeo;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Fitpeo {
	@Test
	public void navigateToHomePage() throws Throwable {

		// Open the web browser and navigate to
		WebDriver driver = new ChromeDriver();
		// Providing ImplicitlyWait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		// maximize The Browser
		driver.manage().window().maximize();

		// 1. Navigate to the FitPeo Homepage:
		driver.get("https://www.fitpeo.com/");

		// 2. Navigate to the Revenue Calculator Page:
		driver.navigate().to("https://fitpeo.com/revenue-calculator");

		// 3. Scroll Down to the Slider section:
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement slider = driver.findElement(By.xpath("//h4[text()='Medicare Eligible Patients']"));
		JavascriptExecutor exe = (JavascriptExecutor) driver;

		exe.executeScript("arguments[0].scrollIntoView(true);", slider);

		// 4. Adjust the Slider:

//		WebElement moveslider = driver.findElement(By.xpath("//span[@class='MuiSlider-rail css-3ndvyc']"));
//		int xasis = moveslider.getLocation().getX();
//		int yasis = moveslider.getLocation().getY();
//		System.out.println(xasis + " " + yasis);
//
//		Actions act = new Actions(driver);
//		act.dragAndDropBy(moveslider, 0, 820).perform();
//		
		
		
		  

		// 5.Update the Text Field:
		WebElement value = driver.findElement(By.xpath("//input[contains(@type,'number')]"));
		value.clear();
		Thread.sleep(3000);
		exe.executeScript("arguments[0].value='560';", value);

		// 6. Validate Slider Value:

		WebElement tagValue = driver.findElement(By.xpath("//input[contains(@type,'number')]"));
		String attributeValue = tagValue.getAttribute("value");
		System.out.println("Attribute value :" + attributeValue);
		Assert.assertEquals(attributeValue, "560");

		// 7. Select CPT Codes:

		// select the checkbox for CPT-99091
		driver.findElement(By.xpath("(//input[@type='checkbox'])[1]")).click();

		// select the checkbox for CPT-99453.
		driver.findElement(By.xpath("(//input[@type='checkbox'])[2]")).click();

		// select the checkbox for CPT-99454
		driver.findElement(By.xpath("(//input[@type='checkbox'])[3]")).click();

		// select the checkboxes for CPT-99474
		driver.findElement(By.xpath("(//input[@type='checkbox'])[8]")).click();

		// 8.Validate Total Recurring Reimbursement:

		List<WebElement> allCheckbox = driver
				.findElements(By.xpath("//p[text()='Selected CPT Codes']/../child::div/div"));

		for (WebElement chekbox : allCheckbox) {
			String data = chekbox.getText();
			System.out.println(data);
		}
		// 9.Verify that the header displaying
		WebElement totalValue = driver.findElement(By
				.xpath("//p[text()='Total Recurring Reimbursement for all Patients Per Month']/following-sibling::p"));
		System.out.println(totalValue.getText());
		
		Assert.assertEquals(totalValue.getText(), "$27000");
		
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		finally {
			driver.quit();
			
		}
	}

}

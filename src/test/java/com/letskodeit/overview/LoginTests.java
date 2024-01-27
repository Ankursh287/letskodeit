package com.letskodeit.overview;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.*;

public class LoginTests {
	WebDriver driver;
	String baseURL;

	@BeforeClass
	public void BeforeClass() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\ankur\\Documents\\selenium\\drivers\\chromedriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		baseURL = "https://sso.teachable.com/secure/42299/users/sign_in?clean_login=true&reset_purchase_session=1";
//		baseURL = "https://learn.letskodeit.com";
		driver.get(baseURL);
	}

	@Test
	public void testLogin() {
		WebElement user_id = driver.findElement(By.id("user_email"));
		WebElement user_password = driver.findElement(By.id("user_password"));
		WebElement login_button = driver.findElement(By.name("commit"));
		user_id.clear();
		user_id.sendKeys("test@email.com");
		user_password.clear();
		user_password.sendKeys("abcabc");
		login_button.click();

		WebElement account_image = null;
		try {
			account_image = driver.findElement(By.xpath("//img[@class='course-box-image']"));
		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
		}
		Assert.assertNotNull(account_image);
		driver.quit();

	}

}

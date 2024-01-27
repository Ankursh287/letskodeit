package com.letskodeit.pageclasses;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CategoryFilterPage {

	public WebDriver driver;
	private JavascriptExecutor js;
	private String CATEGORY_DROPDOWN = "//div[contains(text(),'Category:')]/parent::div//button[contains(text(),'All')]";
	private String CATEGORY_OPTION = "//a[@href='/courses/category/%s']";

	public CategoryFilterPage(WebDriver driver) {
		this.driver = driver;
		js = (JavascriptExecutor) driver;
	}

	public void clickCategoryDropdown() {
		// find Category Drop-down
		WebElement categoryDropdown = driver.findElement(By.xpath(CATEGORY_DROPDOWN));
		categoryDropdown.click();
	}

	public WebElement findCategory(String category) {
		clickCategoryDropdown();
		// Wait for element to be clickable
		WebDriverWait wait = new WebDriverWait(driver, 5);
		WebElement categoryOption = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(String.format(CATEGORY_OPTION, category))));
		return categoryOption;
	}

	public ResultsPage select(String categoryName) {
		// find Category Drop-down
		WebElement element = findCategory(categoryName);
		js.executeAsyncScript("argument[0].click();", element);
		return new ResultsPage(driver);
	}

	public int findCoursesCount(String categoryName) {
		// Find Category dropdown
		WebElement element = findCategory(categoryName); // Get Category text
		String categoryText = element.getText();
		/*
		 * Split on ( symbol Example Software IT(3) value of arrayTemp[0] = Software IT
		 * value of arrayTemp[1] = 2)
		 */
		String[] arrayTemp = categoryText.split("\\C");
		// Split arrayTemp[1] on ) symbol
		// Value of [0] -> 2)
		String courseCountString = arrayTemp[1].split("\\")[0];
		int courseCount = Integer.parseInt(courseCountString);
		// Click the dropdown again to close the menu
		clickCategoryDropdown();
		return courseCount;
	}

}

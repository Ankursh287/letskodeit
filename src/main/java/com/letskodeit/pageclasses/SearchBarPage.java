package com.letskodeit.pageclasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchBarPage {

	public WebDriver driver;
	public String SEARCH_COURSE_FIELD = "search-courses";
	public String SEARCH_COURSE_BUTTON = "search-course-button";

	public SearchBarPage(WebDriver driver) {
		this.driver = driver;
	}

	public ResultsPage course(String courseName) {
		WebElement searchField = driver.findElement(By.id(SEARCH_COURSE_FIELD));
		searchField.clear();
		searchField.sendKeys(courseName);
		WebElement searchButton = driver.findElement(By.id(SEARCH_COURSE_BUTTON));
		searchButton.click();
		return new ResultsPage(driver);
	}

}
package com.letskodeit.pageclasses;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ResultsPage {

	public WebDriver driver;
	public String URL = "query=";
	public String COURSES_LIST = "//div[@class='course-listing']";

	public ResultsPage(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isOpen() {
		return driver.getCurrentUrl().contains(URL);
	}

	public int coursesCount() {
		List<WebElement> coursesList = driver.findElements(By.xpath(COURSES_LIST));
		return coursesList.size();
	}

	public boolean verifySearchResult() {
		boolean result = false;
		if (coursesCount() > 0) {
			result = true;
		}
		result = isOpen() && result;
		return result;
	}
	
	public boolean verifyFilterCourseCount(int expectedCount) {
		return coursesCount() == expectedCount;
	}
}

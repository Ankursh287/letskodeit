package com.letskodeit.testclasses;

import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.letskodeit.pageclasses.NavigationPage;
import com.letskodeit.pageclasses.CategoryFilterPage;
import com.letskodeit.pageclasses.LoginPage;
import com.letskodeit.pageclasses.ResultsPage;
import com.letskodeit.pageclasses.SearchBarPage;

public class AllCoursesTests {

	WebDriver driver;
	String baseURL="https://learn.letskodeit.com";
	String courseToBeSearched = "rest api";

	@BeforeClass
	public void setup() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\ankur\\Documents\\selenium\\drivers\\chromedriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(baseURL);
	}

	@Test
	public void verifySearchCourse() {
		LoginPage login = new LoginPage(driver);
		login.open();
		NavigationPage nav = login.signInWith("test@email.com", "abcabc");
		SearchBarPage search = new SearchBarPage(driver);
		nav.myCourses();
		nav.allCourses();
		ResultsPage result = search.course(courseToBeSearched);
		boolean searchResult = result.verifySearchResult();
		System.out.println("Total courses available " + courseToBeSearched + " : " + result.coursesCount());
		Assert.assertTrue(searchResult);
	}
	
	@Test(dependsOnMethods="verifySearchCourse")
	public void filterByCategory(){
		NavigationPage nav = new NavigationPage(driver);
		nav.allCourses();
		CategoryFilterPage category = new CategoryFilterPage(driver);
		ResultsPage result = category.select("Software IT");
		int count = category.findCoursesCount("Software IT");
		boolean filterResult = result.verifyFilterCourseCount(count);
		Assert.assertTrue(filterResult);
	}
	
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}

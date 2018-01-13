package com.picaso;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class XYZ 
{
	WebDriver driver;
	ExtentHtmlReporter htmlReporter;
	ExtentReports extent ;
	ExtentTest test ;
	@BeforeTest
	public void method()
	{
		htmlReporter= new ExtentHtmlReporter("Resource/Tracko.html");
		htmlReporter.setAppendExisting(true);
		htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setDocumentTitle("Tracko Results");
		htmlReporter.config().setTheme(Theme.DARK);
		
		extent= new ExtentReports();
		extent.attachReporter(htmlReporter);
		
		test= extent.createTest("My Regression Test");
		test.assignAuthor("Saurab Dey");
		
		test.log(Status.PASS, "I am staring Automation");
		
		System.setProperty("webdriver.chrome.driver", "Resource/chromedriver.exe");
		driver= new ChromeDriver();
		test.pass("I am staring browser");
		
		driver.get("https://dev.tracko.co.in/");
		test.pass("I am opeing the site");
		
		try {
			
			test.pass("details").addScreenCaptureFromPath(screenshot("screenshot1"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test(priority=1)
	public void signUp_function()
	{
		try {
			test.pass("I am starting with Sign UP");
			SignUPClass sign= new SignUPClass(driver,test);
			sign.signUpSteps();
			test.pass("Sign Up successfull");
		} catch (Exception e) {
			
			
			MediaEntityModelProvider mediaModel = null;
			try {
				mediaModel = MediaEntityBuilder.createScreenCaptureFromPath(screenshot("screenshot2")).build();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			test.fail("Sign Up has some Error", mediaModel);
		}
		
	}
	

	
	@AfterTest
	public void teardown()
	{
		extent.flush();
		driver.quit();
	}
	
	
	public String screenshot(String screenShotName) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String dest = System.getProperty("user.dir") +"\\Screens\\"+screenShotName+".png";
		File destination = new File(dest);
		FileUtils.copyFile(source, destination);
		return dest;        
	}
	
}

package com.picaso;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;

public class SignUPClass 
{
	
	WebDriver driver;
	ExtentTest test;
	

	
	@FindBy(xpath="//a[text()='Sign Up Free']")
	WebElement webelementOFsignUpforFree;
	
	By locator_OfName_Txt=  By.id("name123");
	By locator_OfEamil_Txt= By.id("email");
	By locator_OfMobile_Txt= By.id("mobile");
	By locator_OfSignup_Btn=By.xpath("//button[text()='Sign up']");
	By locator_OfOTP_Txt=By.id("otp");

	public SignUPClass(WebDriver driver, ExtentTest test) 
	{
	  this.driver=driver;
	  this.test=test;
	  PageFactory.initElements(driver, this);
	}


	public void signUpSteps()
	{
		
		webelementOFsignUpforFree.click();
		
		try {
			WebElement name=driver.findElement(locator_OfName_Txt);
			name.sendKeys("Chetan");
			test.pass("signUpSteps() - Successfuly entered name");
		} catch (Exception e) {
			MediaEntityModelProvider mediaModel = null;
			try {
				mediaModel = MediaEntityBuilder.createScreenCaptureFromPath(screenshot("screenshot4")).build();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			test.fail("signUpSteps()- entered name has encounterd some Error", mediaModel);
		}
		
		
		WebElement email=driver.findElement(locator_OfEamil_Txt);
		email.sendKeys("sau12349@gmail.com");
		
		WebElement mobile=driver.findElement(locator_OfMobile_Txt);
		mobile.sendKeys("1010102027");
		
		WebElement signUpButton=driver.findElement(locator_OfSignup_Btn);
		signUpButton.click();
		
		
		waitforelement(5000);
		
		WebElement otp=driver.findElement(locator_OfOTP_Txt);
		otp.sendKeys("123444");
		
		WebElement mobileVerifyBtn=driver.findElement(By.id("sub_btn_verify"));
		mobileVerifyBtn.click();
		
		waitforelement(2000);
		
		WebElement okBtn=driver.findElement(By.xpath("//button[text()='OK']"));
		okBtn.click();
		
		WebElement password=driver.findElement(By.id("password"));
		password.sendKeys("test@123");
		
		WebElement repassword=driver.findElement(By.id("repassword"));
		repassword.sendKeys("test@123");
		
		WebElement setpassBtn=driver.findElement(By.id("sub_btn_setpass"));
		setpassBtn.click();
		
		waitforelement(5000);
		
	}
	

	public void waitforelement(int x)
	{
		try {
			Thread.sleep(x);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

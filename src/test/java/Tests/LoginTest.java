package Tests;

import java.io.IOException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import Drivers.BaseDriver;
//import Pages.LoginPage;
import Pages.LoginPage_DDT;
import Pages.NewUserPage;
import Utilities.ExtentFactory;

public class LoginTest extends BaseDriver {
	ExtentReports report;
	ExtentTest parentTest;
	ExtentTest childTest;
	
	@BeforeClass
	@Parameters({"url","browserName","headless"})
	public void openUrl(@Optional("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login")String url,
			@Optional("chrome")String browserName, @Optional("false")String headless) throws InterruptedException {
		
		report = ExtentFactory.getInstance();
		parentTest = report.createTest("<p style=\"color:#FF6000; font-size:13px\"><b> ORANGE HRM -LOGIN </b></p>").assignAuthor("SQA Team").assignDevice("Windows");
		launchPlaywright(browserName, headless);
		launchApplication(url);
	}
//	@Test
//	public void login() throws IOException, InterruptedException {
//		childTest = parentTest.createNode("<p style=\"color:#3E96E7; font-size:20px\"><b>Login</b></p>");
//		LoginPage loginPage = new LoginPage(page,childTest);
//		loginPage.login();
//		Thread.sleep(3000);		
//	}
	@Test
	public void loginWithExcelData() throws IOException, InterruptedException {
		childTest = parentTest.createNode("<p style=\"color:#3E96E7; font-size:20px\"><b>Login</b></p>");
		LoginPage_DDT loginPageDdt = new LoginPage_DDT(page,childTest);
		loginPageDdt.login();
		Thread.sleep(3000);
		NewUserPage newUser= new NewUserPage(page,childTest);
		newUser.registationPage();
		Thread.sleep(3000);
	}
	@AfterClass
	public void afterClass() {
		closePlaywright();
		report.flush();
	}
	

}

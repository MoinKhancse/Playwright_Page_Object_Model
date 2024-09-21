package Pages;



import java.io.IOException;
import java.nio.file.Paths;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Page;

import Utilities.CommonMethods;

public class NewUserPage extends CommonMethods {
	
	Page page;
	ExtentTest test;
	
	public NewUserPage(Page page, ExtentTest test) {
		this.page = page;
		this.test = test;
	}
	
	//Report
	public void passCass(String message) {
		test.pass("<p style=\"color:#85BC63; font-size:13px\"><b>" + message + "</b></p>");	
	}

	public void passCassWithSC(String message, String scName) throws IOException {
		test.pass("<p style=\"color:#85BC63; font-size:13px\"><b> click button success </b></p>");
		page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("./ScreenShots/"+scName+".png")).setFullPage(true));
		String dest = System.getProperty("user.dir") + "/ScreenShots/" + "" + scName + ".png";
		test.pass(MediaEntityBuilder.createScreenCaptureFromPath(dest).build());
	}
	
	//Fail
	public void failCass(String message, String scName) throws IOException {
		test.fail("<p style=\"color:#85BC63; font-size:13px\"><b>" + message + "</b></p>");
		Throwable t = new InterruptedException("Exception");
		test.fail(t);
		page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("./ScreenShots/"+scName+".png")).setFullPage(true));
		String dest = System.getProperty("user.dir") + "/ScreenShots/" + "" + scName + ".png";
		test.fail(MediaEntityBuilder.createScreenCaptureFromPath(dest).build());
	}
	
	
	public void registationPage() throws InterruptedException {
		ElementHandle pim = page.querySelector("//span[normalize-space()='PIM']");
		pim.click();
		Thread.sleep(2000);
		
		ElementHandle addEmploy = page.querySelector("//a[normalize-space()='Add Employee']");
		addEmploy.click();
		Thread.sleep(3000);
		
		ElementHandle image = page.querySelector("//img[@class='employee-image']");
		image.click();
		//image.fill("C:\\Users\\benny\\OneDrive\\Desktop\\1726334970351.gif");
		//image.setInputFiles("C:\\Users\\benny\\OneDrive\\Desktop\\1726334970351.gif");
		Thread.sleep(3000);
		
		ElementHandle firstName = page.querySelector("//input[@placeholder='First Name']");
		firstName.fill("Md");
		Thread.sleep(2000);
		
		ElementHandle middleName = page.querySelector("//input[@placeholder='Middle Name']");
		middleName.fill("Moin");
		Thread.sleep(2000);
		
		ElementHandle lastName = page.querySelector("//input[@placeholder='Last Name']");
		lastName.fill("khan");
		Thread.sleep(2000);
		
		ElementHandle button = page.querySelector("//span[@class='oxd-switch-input oxd-switch-input--active --label-right']");
		button.click();
		Thread.sleep(2000);
				
		ElementHandle userName = page.querySelector("(//input[@autocomplete='off'])[1]");
		userName.fill("Sakib");
		Thread.sleep(2000);
		
		ElementHandle password1 = page.querySelector("(//input[@type='password'])[1]");
		password1.fill("01717511288m");
		Thread.sleep(2000);
		
		ElementHandle password2 = page.querySelector("(//input[@type='password'])[2]");
		password2.fill("01717511288m");
		Thread.sleep(5000);
		
		ElementHandle save = page.querySelector("//button[normalize-space()='Save']");
		save.click();
		Thread.sleep(2000);	
	}

}

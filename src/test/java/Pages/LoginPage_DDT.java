package Pages;

import java.io.IOException;
import java.nio.file.Paths;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Page;
import Utilities.CommonMethods;
import Utilities.ExcelUtils;


public class LoginPage_DDT extends CommonMethods {
	Page page;
	ExtentTest test;
	
	ExcelUtils excelUtil = new ExcelUtils();
	
	protected ElementHandle userName;
	protected ElementHandle password;
	protected ElementHandle button;
	
	public LoginPage_DDT(Page page, ExtentTest test) {
		this.page = page;
		this.test = test;
		
		this.userName = page.querySelector("//input[@placeholder='Username']");
		this.password = page.querySelector("//input[@placeholder='Password']");
		this.button = page.querySelector("//button[normalize-space()='Login']");
	}

	public ElementHandle getUserName() {
		return userName;
	}

	public void setUserName(ElementHandle userName) {
		this.userName = userName;
	}

	public ElementHandle getPassword() {
		return password;
	}

	public void setPassword(ElementHandle password) {
		this.password = password;
	}

	public ElementHandle getButton() {
		return button;
	}

	public void setButton(ElementHandle button) {
		this.button = button;
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
		
		public void login() throws IOException {
			test.info("Login Page");
			try {
				excelUtil.readExcel();
				Thread.sleep(2000);
				if(userName.isVisible()) {
					//userName.fill("Admin");
					userName.fill(excelUtil.email);
					passCass("You have Success User Name");
					Thread.sleep(3000);
				}
				try {
					if(password.isVisible()) {
						//password.fill("admin123");
						password.fill(excelUtil.password);
						passCass("You have Success Password");
						Thread.sleep(3000);
					}
					try {
						if(button.isVisible()) {
							button.click();
							Thread.sleep(3000);
							passCassWithSC("You have successfully click ", "Login Page");
						}
					} catch (Exception e) {
						failCass("Sign button not found", "Login Fail");
					}
				} catch (Exception e) {
					failCass("User password Not Found", "Password Fail");
				}
			} catch (Exception e) {
				failCass("User Name Not Found", "Login Fail");
			}
			
		}
}

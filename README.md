# **Playwright Tutorials with JAVA**
### Table of Contents

1. [Overview](#Overview)
2. [Getting Started](#Getting-Started)

    - [Prerequisites](#prerequisites)
    - [Installation](#installation)

      - [Java JDK](#java-jdk)
      - [IDE](#ide)
      - [Apache Maven](#apache-maven)

    - [Setting Up Java JDK](#setting-up-java-jdk)
    - [Setting Up Spring Tools](#setting-up-spring-tools)
    - [Installing Apache Maven](#installing-apache-maven)

11. [Built With](#built-with)
12. [Installation Steps](#installation-steps)   
21. [Step Wise Details](#step-wise-details)

### Overview
This repository serves as a comprehensive guide to working with Playwright using Java. Whether you're a beginner looking to get started with Playwrigth for automated testing or an experienced developer seeking advanced browser handling techniques, this repository has something for everyone.

### Getting Started
Follow these steps to set up your development environment and start using Playwrigth with Java

### Prerequisites

1. [Java JDK](https://www.oracle.com/java/technologies/javase-jdk15-downloads.html)
2. Choose one of the following IDE:
    - [Eclipse](https://www.eclipse.org/downloads/packages/release/2024-03/r/eclipse-ide-java-developers)
    - [IntelliJ IDEA](https://www.jetbrains.com/idea/download/)
    - [Spring Tools](https://spring.io/tools)
3. [Apache Maven](https://maven.apache.org/download.cgi)

### Installation

1. Java JDK
    - Download the installer from the [Oracle Java SE Downloads](https://www.oracle.com/java/technologies/javase-jdk15-downloads.html) page.
    - Run the installer and set up the JDK.
2. IDE
    - Download and install your preferred IDE.
3. Apache Maven
    - Download Apache Maven from the [official website](https://maven.apache.org/download.cgi).
    - Follow installation instructions.
  
### Setting Up Java JDK

1. Download the JDK installer from Oracle.
2. Run the installer and select your installation folder.
3. Set up JAVA_HOME and PATH environment variables:
         - Open System Properties.
         - Navigate to Environment Variables.
         - Add JAVA_HOME with the JDK installation path.
         - Update the PATH variable to include %JAVA_HOME%\bin.
4. Verify Java installation by running java --version in a terminal.

### Setting Up Spring Tools

1. Download Spring Tools from the [official website](https://spring.io/tools).
2. Run the installer and select the package to install.
3. Choose your installation folder and complete the setup.
4. Launch Spring Tools to start using it.

### Installing Apache Maven

1. Check if you have Java installed by running java --version.
2. Download Apache Maven from the [official website](https://maven.apache.org/download.cgi).
3. Extract the archive to your desired location.
4. Set up M2_HOME and MAVEN_HOME environment variables:
    - Create M2_HOME and point it to the Maven installation directory.
    - Update the PATH variable to include %M2_HOME%\bin.
5. Verify Maven installation by running mvn --version in a terminal.

### Built With

1. [Java JDK](https://www.oracle.com/java/) - Java Development Kit
2. [Spring Tools](https://spring.io/tools/) - IDE for Spring applications
3. [Apache Maven](https://maven.apache.org/) - Dependency Management

### Installation Steps
To start using the framework:

1. https://github.com/MoinKhancse/Shohoz.com_Playwright_Automation.git the repository.
2. Clone, i.e, download your copy of the repository to your local machine using
```
git clone https://github.com/MoinKhancse/Shohoz.com_Playwright_Automation.git
```
3. Import the project into your preferred IDE (Eclipse, IntelliJ IDEA, or Spring Tools).
4. Make any desired changes or additions to the project.

##### Maven Dependencies

###### Playwright TestNG

```xml
<dependencies>
		<!-- https://mvnrepository.com/artifact/com.microsoft.playwright/playwright -->
        <dependency>
            <groupId>com.microsoft.playwright</groupId>
            <artifactId>playwright</artifactId>
            <version>1.43.0</version>
        </dependency>

		<!-- https://mvnrepository.com/artifact/org.testng/testng -->
		<dependency>
			<groupId>org.testng</groupId>
			<artifactId>testng</artifactId>
			<version>7.8.0</version>
			<scope>test</scope>
		</dependency>
	<dependency>
	    <groupId>org.apache.poi</groupId>
	    <artifactId>poi</artifactId>
	    <version>5.3.0</version>
	</dependency>

	<dependency>
	    <groupId>com.aventstack</groupId>
	    <artifactId>extentreports</artifactId>
	    <version>5.1.2</version>
	</dependency>

	<dependency>
	    <groupId>org.apache.poi</groupId>
	    <artifactId>poi-ooxml</artifactId>
	    <version>5.3.0</version>
	</dependency>
</dependencies>
```

## Step Wise Details

## Drivers Setting

package Drivers;

import java.util.List;

import com.aventstack.extentreports.ExtentTest;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.WaitUntilState;

public class BaseDriver {
	public static Playwright playwright;
	public static BrowserType browserType;
	public static Browser browser;
	public static BrowserContext browserContext;
	public static Page page;
	protected ExtentTest test;
	
	public static void startPlaywright(String browserName, String headless) {
		playwright = Playwright.create();
		
		if(browserName.equalsIgnoreCase("chrome") || browserName.equalsIgnoreCase("msedge") 
				|| browserName.equalsIgnoreCase("chromium")) {
			browserType = playwright.chromium();
		}
		else if (browserName.equalsIgnoreCase("webkit")) {
			browserType = playwright.webkit();
		}
		else {
			browserType = playwright.firefox();
		}
		
		if(headless.equalsIgnoreCase("true")) {
			browser = browserType.launch(new BrowserType.LaunchOptions().setChannel(browserName).setHeadless(true).setArgs(List.of("--start-maximized")));
		}
		else {
			browser = browserType.launch(new BrowserType.LaunchOptions().setChannel(browserName).setHeadless(false).setArgs(List.of("--start-maximized")));
		}
		
		browserContext = browser.newContext(new Browser.NewContextOptions().setViewportSize(null));
		page = browserContext.newPage();	
	}
	public static void launchApplication(String url) throws InterruptedException {
		page.navigate(url, new Page.NavigateOptions().setWaitUntil(WaitUntilState.LOAD));
		Thread.sleep(2000);	
	}
	public void closePlaywright() {
		try {
			page.close();
			browser.close();
			playwright.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}


package org.base;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public static WebDriver driver;

	public static WebDriver browserLaunch(String browsername) {
		if (browsername.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browsername.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new ChromeDriver();
		} else if (browsername.equalsIgnoreCase("ie")) {
			WebDriverManager.iedriver().setup();
		}
		return driver;
	}

	public static void urlLaunch(String url) {
		driver.get(url);
		driver.manage().window().maximize();
	}
	public static void navigateToUrl(String url) {
		driver.navigate().to(url);
	}

	public static void urlCheck(String contains) {
		String url = driver.getCurrentUrl();
		boolean b = url.contains(contains);
		SoftAssert s=new SoftAssert();
		s.assertEquals(b, true, "FOUND: Mismatch in URL");
	}

	public static void implicitWait(int sec) {
		driver.manage().timeouts().implicitlyWait(sec, TimeUnit.SECONDS);
	}

	public static void browserClose() {
		driver.close();
	}

	public static void browserQuit() {
		driver.quit();
	}

	public static void waitUntillWebelement(WebElement webelement, int seconds) {
		WebDriverWait w = new WebDriverWait(driver, seconds);
		w.until(ExpectedConditions.visibilityOf(webelement));
	}

	public static void waitUntillWebelementClickable(WebElement webelement, int seconds) {
		WebDriverWait w = new WebDriverWait(driver, seconds);
		w.until(ExpectedConditions.elementToBeClickable(webelement));
	}

	public static void waitUntillPageLoad(int seconds) {
		driver.manage().timeouts().pageLoadTimeout(seconds, TimeUnit.SECONDS);
	}

	public static void sleep(Integer millisec) throws InterruptedException {
		Thread.sleep(millisec);
	}

	public static void sendKeys(WebElement e, String value) {
		e.sendKeys(value);
	}
	public static void sendKeysAfterDeletingExistingText(WebElement e, String value) {
		e.sendKeys(Keys.CONTROL, "a");
		e.sendKeys(Keys.DELETE);
		e.sendKeys(value);
	}
	
	public static void javascriptSendkeys(WebElement e,String value) {
		JavascriptExecutor js= (JavascriptExecutor)driver;
		js.executeScript("arguments[0].setAttribute('value',"+"'"+value+"'"+")", e);
	}

	public static void click(WebElement e) {
		e.click();
	}
	public static void actionsClick(WebElement e) {
		Actions a= new Actions(driver);
		a.click(e).perform();
	}
	public static void javascriptClick(WebElement e) {
		JavascriptExecutor js= (JavascriptExecutor)driver;
		js.executeAsyncScript("arguments[0].click()", e);
	}
	public static void javascriptScrollDownTo(WebElement webelement) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true)", webelement);
	}
	
	public static void javascriptScrollUpTo(WebElement webelement) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(false)", webelement);
	}
	public static String getCurrentUrl() {
		String url = driver.getCurrentUrl();
		return url;
	}

	public static String getText(WebElement e) {
		String text = e.getText();
		return text;
	}
	

	public static String getTitle() {
		String title = driver.getTitle();
		return title;
	}

	public static String getAttribute(WebElement e, String k) {
		String v="";
		if (k.equalsIgnoreCase("value")) {
			v =v+ e.getAttribute("value");
		} else {
			v =v+ e.getAttribute("innerText");
		}
		return v;
	}

	public static void btnEsc() throws AWTException {
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_ESCAPE);
		r.keyRelease(KeyEvent.VK_ESCAPE);
	}

	public static void btnEnter() throws AWTException {
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
	}

	public static boolean isEnabled(WebElement e) {
	boolean b = e.isEnabled();
	return b;
	}

	public static boolean isSelected(WebElement e) {
		boolean b = e.isSelected();
		return b;
	}

	public static void takeScrnsht(String Scenario) throws IOException {
		String sr = System.getProperty("user.dir");
		long time = System.currentTimeMillis();
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File des = new File(sr + "\\ScreenShots\\IMG_" + Scenario + "_" + time + ".png");
		FileUtils.copyFile(src, des);
	}

	//Select Class by visibileText
	public static void dropdownSelectByVisibleText(WebElement e, String VisibleText) {
		Select s=new Select(e);
		s.selectByVisibleText(VisibleText);
	}
	
	//SoftAssertion	
	public static void softAssertEquals(String actual, String expected, String Msg) {
		SoftAssert s= new SoftAssert();
		s.assertEquals(actual, expected, Msg);
		s.assertAll();
	}
	
	public static void softAssertEquals(Boolean actual, Boolean expected, String Msg) {
		SoftAssert s= new SoftAssert();
		s.assertEquals(actual, expected, Msg);
		s.assertAll();
		
	}
	
	public static void softAssertContains (String stringActual, String contains, String msg) {
		boolean contain = stringActual.contains(contains);
		SoftAssert s= new SoftAssert();
		softAssertEquals(contain, true, msg);	
		s.assertAll();
	}
	
	public static void softAssertEquals(int actualInteger, int expectedInteger, String msg) {
		SoftAssert s=new SoftAssert();
		s.assertEquals(actualInteger, expectedInteger, msg);
	s.assertAll();
	}
	public static void softAssertEquals(double actualDouble, double expectedDouble, String msg) {
		SoftAssert s=new SoftAssert();
		s.assertEquals(actualDouble, expectedDouble, msg);
	s.assertAll();
	}
	public static void imageUpload(String filePath) throws AWTException, InterruptedException {
		StringSelection selection = new StringSelection(filePath);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
		Robot r = new Robot();
		Thread.sleep(2000);
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
		r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_V);
		r.keyRelease(KeyEvent.VK_CONTROL);
		r.keyRelease(KeyEvent.VK_V);
		Thread.sleep(2000);
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
		
	}
	public static boolean isFileDownloaded(String downloadPath, String fileName) {
		  File dir = new File(downloadPath);
		  File[] dirContents = dir.listFiles();

		  for (int i = 0; i < dirContents.length; i++) {
		      if (dirContents[i].getName().equals(fileName)) {
		          return true;
		      }
		          }
		      return false;
		  }
}

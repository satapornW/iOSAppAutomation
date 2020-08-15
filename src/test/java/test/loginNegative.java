package test;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Time;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.offset.PointOption;

public class loginNegative {
	
	private static IOSDriver driver;
	private static TouchAction touchAction;

	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		
		startUp();
		incorrectEmail();
		incorrectPassword();
		
	}
	
	public static void startUp() throws MalformedURLException, InterruptedException {
		
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("platformName", "IOS");
		cap.setCapability("platformVersion", "13.0");
		cap.setCapability("deviceName", "X");
		//cap.setCapability(CapabilityType.BROWSER_NAME, "safari");
		cap.setCapability("app", "/Users/satapornworasilpchai/Library/Developer/Xcode/DerivedData/radiant-ehjiavxrjfpydoaaefpmhtjadhjg/Build/Products/Release-iphonesimulator/Kismet.app");	
		//cap.setCapability("app", "/Users/satapornworasilpchai/Library/Developer/Xcode/DerivedData/WebDriverAgent-ciegwgvxzxdrqthilmrmczmqvrgu/Build/Products/Debug-iphonesimulator/IntegrationApp.app");
		URL url = new URL("http://127.0.0.1:4723/wd/hub");
		driver = new IOSDriver(url, cap);
		
		//Wait for the soft push to update
		System.out.println("Start wait");
		TimeUnit.SECONDS.sleep(15);
		System.out.println("End wait");
	
	}
	
	public static void incorrectEmail() throws InterruptedException {
		
		String email = "doesnotexist@email.com";
		String password = "12345678";
		
		touchAction = new TouchAction(driver);
		touchAction.tap(new PointOption().withCoordinates(180, 630)).perform();
		
		//Hard code login navigation
		TimeUnit.SECONDS.sleep(1);
		//driver.findElement(MobileBy.AccessibilityId(("Already have an account? Log In"))).click();
		//driver.findElementByXPath("(//XCUIElementTypeOther[@name=\"Already have an account? Log In\"])[3]").click();
		touchAction.tap(new PointOption().withCoordinates(271,145)).perform();
		
		//Input email and password
		TimeUnit.SECONDS.sleep(1);
		touchAction.tap(new PointOption().withCoordinates(34,174)).perform();
		driver.getKeyboard().pressKey(email);
		
		TimeUnit.SECONDS.sleep(1);
		touchAction.tap(new PointOption().withCoordinates(34,264)).perform();
		driver.getKeyboard().pressKey(password);
		
		driver.findElementByName("Next").click();
		
		//Validate message, and make an assertion here
		
		String message = driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Invalid email or password. Please try again.\"]").getText();
		System.out.println(message);
		
		TimeUnit.SECONDS.sleep(1);
		driver.findElementByXPath("//XCUIElementTypeButton[@name=\"OK\"]").click();
		
		//Go back to main, this will clear the field. 
		TimeUnit.SECONDS.sleep(2);
		driver.findElementByXPath("//XCUIElementTypeButton[@name=\"header-back\"]").click();
	}
	
	public static void incorrectPassword() throws InterruptedException {
		
		String email = "accdc@yahoo.com";
		String password = "12345678";
		
		touchAction = new TouchAction(driver);
		touchAction.tap(new PointOption().withCoordinates(180, 630)).perform();
		
		//Hard code login navigation
		TimeUnit.SECONDS.sleep(1);
		//driver.findElement(MobileBy.AccessibilityId(("Already have an account? Log In"))).click();
		//driver.findElementByXPath("(//XCUIElementTypeOther[@name=\"Already have an account? Log In\"])[3]").click();
		touchAction.tap(new PointOption().withCoordinates(271,145)).perform();
		
		//Input email and password
		TimeUnit.SECONDS.sleep(1);
		touchAction.tap(new PointOption().withCoordinates(34,174)).perform();
		driver.getKeyboard().pressKey(email);
		
		TimeUnit.SECONDS.sleep(1);
		touchAction.tap(new PointOption().withCoordinates(34,264)).perform();
		driver.getKeyboard().pressKey(password);
		
		driver.findElementByName("Next").click();
		
		//Validate message, and make an assertion here
		
		String message = driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Invalid email or password. Please try again.\"]").getText();
		System.out.println(message);
		
		TimeUnit.SECONDS.sleep(1);
		driver.findElementByXPath("//XCUIElementTypeButton[@name=\"OK\"]").click();
		
		//Go back to main, this will clear the field. 
		TimeUnit.SECONDS.sleep(2);
		driver.findElementByXPath("//XCUIElementTypeButton[@name=\"header-back\"]").click();
		
	}

}

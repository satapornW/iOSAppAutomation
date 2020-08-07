package test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.offset.PointOption;

/*
 * Validate that login with existing user works
 * We'll login with a static profile
 */

public class login {
	
	private static IOSDriver driver;
	private static TouchAction touchAction;
	
	public static void main(String[] args) throws InterruptedException, MalformedURLException {
		
		startUp();
		
		login();
	}
	
	//initiate Simulator and login
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
		
	//Find login and log-in
	
	public static void login() throws InterruptedException {
		
		/*
		 * Hard code position because selector does not exists on the main page
		 * We are selecting email registration
		 */
		touchAction = new TouchAction(driver);
		touchAction.tap(new PointOption().withCoordinates(183, 632)).perform();
		
		
		System.out.println("Finish startup");
		System.out.println("Start login");
		
		String email = "testlate@yahoo.com";
		String password = "11111111";
		
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
		
		//Deal with notification
		//Skip for now
		TimeUnit.SECONDS.sleep(1);
		driver.findElementByXPath("//XCUIElementTypeOther[@name=\"Maybe later\"]").click();
		
		//Validate Today
		
	}
		
}

package test;

import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.offset.PointOption;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ValidateProfile {
	
	private static IOSDriver driver;
	private static TouchAction touchAction;
	
	@Test
	public void a_startUp() throws MalformedURLException, InterruptedException {
		
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
	@Test
	public void b_loginEmail() throws InterruptedException {
		
		/*
		 * Hard code position because selector does not exists on the main page
		 * We are selecting email registration
		 */
		touchAction = new TouchAction(driver);
		touchAction.tap(new PointOption().withCoordinates(180, 630)).perform();
		
		
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
	
	@Test
	public void c_testProfile() throws  InterruptedException, MalformedURLException{
		
		TimeUnit.SECONDS.sleep(1);
		touchAction.tap(new PointOption().withCoordinates(340,75)).perform();
		TimeUnit.SECONDS.sleep(1);
		String profileName = driver.findElementByXPath("(//XCUIElementTypeOther[@name=\"test late\"])[2]").getAttribute("name");
		assertEquals("test late", profileName);
		
	}
	
	@Test
	public void d_followingCount() throws InterruptedException {
		
		System.out.println("Start Following Count Check");
		TimeUnit.SECONDS.sleep(2);
		//String followingCount = driver.findElementByXPath("//XCUIElementTypeOther[@name=\"25 Following\"]").getAttribute("name");
		String followingCount;
		try {
			followingCount = driver.findElementByAccessibilityId("25 Following").getAttribute("name");
		} catch (Exception e) {
			followingCount = driver.findElementByXPath("//XCUIElementTypeOther[@name=\"25 Following\"]").getAttribute("name");

		}
		
		assertEquals("25 Following", followingCount);
		
		driver.findElementByXPath("//XCUIElementTypeOther[@name=\"25 Following\"]").click();
		TimeUnit.SECONDS.sleep(1);
		
		//Make sure it's not empty
		String profileHere = driver.findElementByXPath("(//XCUIElementTypeOther[@name=\"Cam Nguyen @Cam.Nguyen Following\"])[3]").getAttribute("name");
		assertEquals("Cam Nguyen @Cam.Nguyen Following", profileHere);
		
		TimeUnit.SECONDS.sleep(1);
		driver.findElementByXPath("//XCUIElementTypeButton[@name=\"header-back\"]").click();
		assertEquals("25 Following", followingCount);
		
		System.out.println("End following count check");
	}
	
	@Test
	public void e_followers() {
		
		String followersCount = driver.findElementByXPath("//XCUIElementTypeOther[@name=\"2 Followers\"]").getAttribute("name");
		assertEquals("2 Followers", followersCount);
		
	}
	
	@Test
	public void f_posts() {
		
	}

}

/*
 * Safari will remember the login, so we need to check for if the user already logged in.
 * If the user already logged in we need a different flow after the Facebook button init. 
 */

package test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.offset.PointOption;

public class loginFacebook {

	private static IOSDriver driver;
	private static TouchAction touchAction;
	
	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		
		startUp();
		loginWithFacebook();
		
	}
	
	public static void startUp() throws MalformedURLException, InterruptedException {
		
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("platformName", "IOS");
		cap.setCapability("platformVersion", "13.0");
		cap.setCapability("deviceName", "X");
		//cap.setCapability(CapabilityType.BROWSER_NAME, "safari");
		cap.setCapability("app", "/Users/satapornworasilpchai/Library/Developer/Xcode/DerivedData/radiant-ehjiavxrjfpydoaaefpmhtjadhjg/Build/Products/Release-iphonesimulator/Kismet.app");	
		
		/*
		 * This will accept ios pop up (continue or allow) by default.
		 * Not consistent at all.
		 */
		//cap.setCapability("autoAcceptAlerts", true);
		
		URL url = new URL("http://127.0.0.1:4723/wd/hub");
		driver = new IOSDriver(url, cap);
		
		//Wait for the soft push to update
		System.out.println("Start wait");
		TimeUnit.SECONDS.sleep(15);
		System.out.println("End wait");
	
	}
	
	public static void loginWithFacebook() throws InterruptedException {
		
		//Facebook credential
		System.out.println("Start Facebook");
		String email = "ni_shuhjhh_mo@tfbnw.net";
		String password = "aaaa1111!";
		
		touchAction = new TouchAction(driver);
		touchAction.tap(new PointOption().withCoordinates(180, 580)).perform();
		
		TimeUnit.SECONDS.sleep(5);
		
		//Continue
		System.out.println("Enter facebook modal Facebook");
		
		touchAction.tap(new PointOption().withCoordinates(250, 465)).perform();
		
		
		TimeUnit.SECONDS.sleep(5);
		
		//Facebook page
		driver.findElementByXPath("//XCUIElementTypeOther[@name=\"main\"]/XCUIElementTypeTextField").sendKeys(email);
		TimeUnit.SECONDS.sleep(1);
		driver.findElementByXPath("//XCUIElementTypeOther[@name=\"main\"]/XCUIElementTypeSecureTextField").sendKeys(password);
		TimeUnit.SECONDS.sleep(1);
		//Log in
		driver.findElementByXPath("//XCUIElementTypeButton[@name=\"Log In\"]").click();
		
		
		//Facebook confirm
		TimeUnit.SECONDS.sleep(2);
		
		touchAction.tap(new PointOption().withCoordinates(250, 465)).perform();

		TimeUnit.SECONDS.sleep(2);
		
		//Deal with notification modal
		driver.findElementByXPath("//XCUIElementTypeOther[@name=\"Turn on notifications\"]").click();
		TimeUnit.SECONDS.sleep(1);
		touchAction.tap(new PointOption().withCoordinates(300, 500)).perform();
		
	}
	
}

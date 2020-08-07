/*
 * Note you should not run google auth too many times in a a day.
 * Your account may get deactivated.
 */


package test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.HideKeyboardStrategy;
import io.appium.java_client.touch.offset.PointOption;

public class loginGoogle {

	private static IOSDriver driver;
	private static TouchAction touchAction;
	
	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		
		startUp();
		loginWithGoogle();
		
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
	
	
	//Google login attempt
	
	public static void loginWithGoogle() throws InterruptedException {
		
		//Gmail credential
		System.out.println("Start google");
		String email = "twofive42";
		
		touchAction = new TouchAction(driver);
		touchAction.tap(new PointOption().withCoordinates(180, 530)).perform();
		
		TimeUnit.SECONDS.sleep(5);
		
		//Continue
		touchAction.tap(new PointOption().withCoordinates(250, 465)).perform();
		
		//Init Gmail 
		//driver.findElementByXPath("//XCUIElementTypeOther[@name=\"Sign in - Google Accounts\"]/XCUIElementTypeOther[5]").sendKeys(email);
		TimeUnit.SECONDS.sleep(2);
		System.out.println("Type email");
		touchAction.tap(new PointOption().withCoordinates(90, 290)).perform();
		driver.getKeyboard().pressKey(email);
		TimeUnit.SECONDS.sleep(1);
		driver.findElementByXPath(String.format("//XCUIElementTypeButton[@name='%s']", "Done")).click();
		TimeUnit.SECONDS.sleep(1);
		touchAction.tap(new PointOption().withCoordinates(300, 500)).perform();
		
		
		TimeUnit.SECONDS.sleep(3);
		
		//Input password
		//driver.findElementByXPath("//XCUIElementTypeOther[@name=\"Sign in - Google Accounts\"]/XCUIElementTypeOther[4]").sendKeys("aaaa1111!");
		System.out.println("Type password");
		touchAction.tap(new PointOption().withCoordinates(90, 290)).perform();
		driver.getKeyboard().pressKey("aaaa1111!");
		TimeUnit.SECONDS.sleep(1);
		driver.findElementByXPath(String.format("//XCUIElementTypeButton[@name='%s']", "Done")).click();
		TimeUnit.SECONDS.sleep(1);
		touchAction.tap(new PointOption().withCoordinates(300, 500)).perform();
		
		TimeUnit.SECONDS.sleep(4);
		
		driver.findElementByXPath("//XCUIElementTypeOther[@name=\"Turn on notifications\"]").click();
		TimeUnit.SECONDS.sleep(1);
		touchAction.tap(new PointOption().withCoordinates(300, 500)).perform();
		
	}
	
	

	
}

package test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.offset.PointOption;


//String locationOfApp="/Users/satapornworasilpchai/Library/Developer/Xcode/DerivedData/radiant-ehjiavxrjfpydoaaefpmhtjadhjg/Build/Products/Debug-iphonesimulator/Kismet.app";

public class simpleRegistration {
	
	private static IOSDriver driver;

	public static void main(String[] args) throws InterruptedException, MalformedURLException {
	
		startUp();
		
		createNewUser();
		
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
		
		/*
		 * Hard code position because selector does not exists on the main page
		 * We are selecting email registration
		 */
		TouchAction touchAction = new TouchAction(driver);
		touchAction.tap(new PointOption().withCoordinates(183, 632)).perform();
		
		System.out.println("Finish startup");
		
	}
	
	//Create new user via email
	public static void createNewUser() throws InterruptedException {
		
		System.out.println("Creating a new User");
		freshUser user = new freshUser();
		
		TimeUnit.SECONDS.sleep(2);
		
		//In iOS Simulator, turn off hardware keyboard before running sendKeys
		//Input values for a new user
		driver.findElementByXPath("//XCUIElementTypeOther[@name=\"Full name\"]/XCUIElementTypeTextField").sendKeys(user.fullName);
		driver.findElementByXPath("//XCUIElementTypeOther[@name=\"Your email\"]/XCUIElementTypeTextField").sendKeys(user.email);
		driver.findElementByXPath("//XCUIElementTypeOther[@name=\"Create a password\"]/XCUIElementTypeSecureTextField").sendKeys("11111111");
		
		//Move forward
		driver.findElementByXPath("(//XCUIElementTypeOther[@name=\"Next\"])[2]").click();
		
		//Skip Age and Gender for now
		//May need to hardcode location since XPath can change value.
		System.out.println("try to skip");
		TimeUnit.SECONDS.sleep(2);
		driver.findElementByXPath("(//XCUIElementTypeOther[@name=\"Next\"])[7]").click();
		System.out.println("age and sex Modal skipped");
		
		//Pick Category screen
		TimeUnit.SECONDS.sleep(1);
		driver.findElementByXPath("//XCUIElementTypeOther[@name=\"TV & Movies\"]").click();
		
		
		//Will need a better way to input data for pick recs
		List<String> pickMoviesList = Arrays.asList(
				"//XCUIElementTypeOther[@name=\"Fleabag\"]", //0
				"//XCUIElementTypeOther[@name=\"Workaholics\"]",
				"//XCUIElementTypeOther[@name=\"Six Feet Under\"]",
				"//XCUIElementTypeOther[@name=\"The Big Lebowski\"]",
				"//XCUIElementTypeOther[@name=\"Love Actually\"]",
				"//XCUIElementTypeOther[@name=\"Empire Records\"]",
				"//XCUIElementTypeOther[@name=\"Broad City\"]",
				"//XCUIElementTypeOther[@name=\"Bridget Jones's Diary\"]",
				"//XCUIElementTypeOther[@name=\"This Is the End\"]",
				"//XCUIElementTypeOther[@name=\"Four Weddings and a Funeral\"]",
				"//XCUIElementTypeOther[@name=\"Barry\"]", //10
				"//XCUIElementTypeOther[@name=\"Weeds\"]",
				"//XCUIElementTypeOther[@name=\"Parasite\"]",
				"//XCUIElementTypeOther[@name=\"Mad Max: Fury Road\"]",
				"//XCUIElementTypeOther[@name=\"The Walking Dead\"]",
				"//XCUIElementTypeOther[@name=\"Parasite\"]",
				"//XCUIElementTypeOther[@name=\"Kingsman: The Secret Service\"]",
				"//XCUIElementTypeOther[@name=\"The IT Crowd\"]",
				"//XCUIElementTypeOther[@name=\"The Leftovers\"]",
				"//XCUIElementTypeOther[@name=\"Bird Box\"]", //19
				"//XCUIElementTypeOther[@name=\"The Big Sick\"]",
				"//XCUIElementTypeOther[@name=\"The Martian\"]"
				);
		
		//Start the picking process. 
		TimeUnit.SECONDS.sleep(2);
		
		for (Iterator<String> i = pickMoviesList.iterator(); i.hasNext();) {
		    String item = i.next();
		    System.out.println(item);
		    driver.findElementByXPath(item).click();
		}
		
		//driver.findElementByXPath("(//XCUIElementTypeOther[@name=\"Next\"])[2])").click();
		driver.findElementByName("Next").click();
		
		//Deal with notification
		//Skip for now
		TimeUnit.SECONDS.sleep(1);
		driver.findElementByXPath("//XCUIElementTypeOther[@name=\"Maybe later\"]").click();
		
	}
	
	
	
}

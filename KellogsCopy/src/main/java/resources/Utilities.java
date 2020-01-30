package resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;

public class Utilities {
	static WebDriver driverObject;

	public static WebDriver browserinitialization() throws IOException {
		Properties propertiesObject = new Properties();
		FileInputStream fileObj = new FileInputStream(
				System.getProperty("user.dir") + "/src/main/java/resources/DataDriven.properties");
		propertiesObject.load(fileObj);
		if (propertiesObject.getProperty("browser").equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", Constants.getChromePath());
			driverObject = new ChromeDriver();
		} else if (propertiesObject.getProperty("browser").equalsIgnoreCase("firefox")) {
//			System.setProperty("webdriver.firefox.marionette",Constants.getFirefoxPath());
			System.setProperty("webdriver.gecko.driver", Constants.getFirefoxPath());
			driverObject = new FirefoxDriver();
		} else {
			System.setProperty("webdriver.ie.driver", Constants.getIEPath());
			driverObject = new InternetExplorerDriver();
		}
		driverObject.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		return driverObject;
	}

	public Properties propertiesFileLoad() throws IOException {
		Properties propertiesObject = new Properties();
		FileInputStream fileObj = new FileInputStream(
				System.getProperty("user.dir") + "/src/main/java/resources/DataDriven.properties");
		propertiesObject.load(fileObj);
		return propertiesObject;
	}

	public void handlingFrames(WebDriver driverObj, By elementID) {
		// TODO Auto-generated method stub
		System.out.println("inside util");
		int frameContainingElement;
		int numberOfFrames = driverObj.findElements(By.tagName("iframe")).size();
		for (frameContainingElement = 0; frameContainingElement < numberOfFrames; frameContainingElement++) {
			driverObj.switchTo().frame(frameContainingElement);
			int elementPresent = driverObj.findElements(elementID).size();
			System.out.println(elementPresent);
			if (elementPresent > 0) {
				driverObj.findElement(elementID).click();
				driverObj.switchTo().defaultContent();
				break;
			} else {
				driverObj.switchTo().defaultContent();
			}
		}
	}
	
	public String handlingMultipleWindows(WebDriver driverObj) throws IOException {
		Set<String> ids = driverObj.getWindowHandles();
		Iterator<String> iteratorObject = ids.iterator();
		System.out.println("value through utli"+ids);
		String parentId = iteratorObject.next();
		while (iteratorObject.hasNext()) {
			driverObj.switchTo().window(iteratorObject.next());
			System.out.println(driverObj.getTitle());
			}
		return parentId;
	}

//	public void takeScreenShot(String URL) throws IOException {
//		File src = ((TakesScreenshot) driverObject).getScreenshotAs(OutputType.FILE);
//		
//		FileUtils.copyFile(src, new File("C:\\Users\\sakgupta1\\CopyOfSecondProject\\screenshot" + "screenshot.png"));
//	}

}

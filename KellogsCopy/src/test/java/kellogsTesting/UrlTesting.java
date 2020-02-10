package kellogsTesting;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import objectRepository.HomePageObjects;
import resources.Utilities;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;
import ru.yandex.qatools.ashot.screentaker.ViewportPastingStrategy;

import java.io.FileOutputStream;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.codec.PngImage;

public class UrlTesting extends Utilities {
	WebDriver driverObject;
	Properties propertiesObject = null;
	HomePageObjects HomePageObject = null;

	@BeforeTest
	public void objectInitialization() throws IOException {
		driverObject = browserinitialization();
//		driverObject.manage().deleteAllCookies();
		propertiesObject = propertiesFileLoad();
		driverObject.manage().window().maximize();
		HomePageObject = new HomePageObjects(driverObject);

	}

	@Test
	public void URLTesting() throws IOException, InterruptedException, DocumentException {

		String URL = "";
		String parentID = "";
		String newString2 = "";
		int indexToInsert = 7;
		ArrayList testCaseData = null;
		String localeName = null;
		String pageName = null;
		String noOfURLs = propertiesObject.getProperty("numberOfURLs");
		int numberOfURLs = Integer.parseInt(noOfURLs);
//		System.out.println(numberOfURLs);
		excelDataDriven dataObject = new excelDataDriven();
		for (int i = 1; i < (numberOfURLs + 1); i++) {
			URL = "";
			newString2 = "";
			String newString = "URL" + i;
//			System.out.println("this value " + newString);
			testCaseData = dataObject.getData(newString);
//			System.out.println(testCaseData);
			int arraySize = testCaseData.size();
//			System.out.println(arraySize);
			if (arraySize > 0) {
				for (int index = 1; index < arraySize; index++) {
					String text = (String) testCaseData.get(index);
					URL = URL + text;
					if (index == 2) {
						localeName = text;
//						System.out.println(localeName);
						localeName = localeName.replace("/", "_");

					} else if (index == 3) {
						pageName = text;
//						System.out.println(pageName);
						pageName = pageName.replace("/", "_");
//						System.out.println(pageName);

					}

				}

//				System.out.println(URL);

				String credentials = propertiesObject.getProperty("credentials");

				newString2 = URL.substring(0, indexToInsert + 1) + credentials + URL.substring(indexToInsert + 1);
//				System.out.println("line number" + newString2);
//				if(i!=2) {
//					driverObject.switchTo().window(parentID);    // part of twitter code
//				}
				driverObject.get(newString2);
				Thread.sleep(10000L);
//				System.out.println("value of i " + i);
											// FB Code
//				if (i != 1) {
//					HomePageObject.sendObjectFacebookSharingBtn().click();
//					Thread.sleep(4000L);
//					parentID = handlingMultipleWindows(driverObject);
//					WebDriverWait wait = new WebDriverWait(driverObject, 10);
//					if(i==2) {
//						wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")))
//						.sendKeys(propertiesObject.getProperty("facebookUserName"));
//						HomePageObject.sendObjectFacebookPasswordField().sendKeys(propertiesObject.getProperty("facebookPassword"));
////						driverObject.findElement(By.id("pass")).sendKeys("Sakshar7213%&");
//						HomePageObject.sendObjectFacebookLoginBtn().click();
////						driverObject.findElement(By.xpath("//input[@name='login']")).click();
//					}
//			        JavascriptExecutor js = (JavascriptExecutor)driverObject;  
//			        js.executeScript("scrollBy(0, 4500)");  
//					final Screenshot screenshot = new AShot().coordsProvider(new WebDriverCoordsProvider()).takeScreenshot(driverObject);
//					final BufferedImage image = screenshot.getImage();
//					ImageIO.write(image, "PNG", new File(System.getProperty("user.dir") + "/ScreenshotsOutput/"
//							+ localeName + pageName + "_screenshot.png"));
//					driverObject.close();
//					driverObject.switchTo().window(parentID);
//				}
				
											// Twitter Code 
//				if (i != 1) {	
//					if(i!=2) {
//						parentID = handlingMultipleWindows(driverObject);
//						driverObject.switchTo().window(parentID);
//						System.out.print("page title after siwtch" + driverObject.getCurrentUrl());
//						HomePageObject.sendObjectTwitterSharingBtn().click();
//						Thread.sleep(2000L);
//						String parentIDs = handlingMultipleWindows(driverObject);
//						WebDriverWait waitforTweetBtn = new WebDriverWait(driverObject, 10);
//						waitforTweetBtn.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@value='Tweet']"))).click();
//						driverObject.switchTo().window(parentID);
//						String parentIDss = handlingMultipleWindows(driverObject);
//						driverObject.navigate().refresh();
//						Thread.sleep(5000L);
//						final Screenshot screenshot = new AShot().coordsProvider(new WebDriverCoordsProvider()).takeScreenshot(driverObject);
//						final BufferedImage image = screenshot.getImage();
//						ImageIO.write(image, "PNG", new File(System.getProperty("user.dir") + "/ScreenshotsOutput/"
//								+ localeName + pageName + "_screenshot.png"));
//					}
//					if(i!=2) {
//						driverObject.switchTo().window(parentID);
//					}
//					HomePageObject.sendObjectTwitterSharingBtn().click();
//					Thread.sleep(2000L);
//					parentID = handlingMultipleWindows(driverObject);
//					System.out.println("value of parentID" +parentID);
//					WebDriverWait wait = new WebDriverWait(driverObject, 10);
//					if(i==2) {
//						wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username_or_email")))
//						.sendKeys(propertiesObject.getProperty("twitterUserName"));
//						HomePageObject.sendObjectTwitterPasswordField().sendKeys(propertiesObject.getProperty("twitterPassword"));
//						driverObject.findElement(By.xpath("//input[@value='Log in and Tweet']")).click();;
//						WebDriverWait waitforTweetBtn = new WebDriverWait(driverObject, 10);
//						waitforTweetBtn.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@value='Tweet']"))).click();
//						driverObject.switchTo().window(parentID);
//						JavascriptExecutor jsopentab = (JavascriptExecutor)driverObject;  
//						jsopentab.executeScript("window.open('https://twitter.com/home','_blank');");
//						Thread.sleep(10000L);
//						String parentIDs = handlingMultipleWindows(driverObject);
////						ArrayList<String> tabs = new ArrayList<String> (driverObject.getWindowHandles());
////						driverObject.switchTo().window(tabs.get(0));
//						System.out.println("page title value" + driverObject.getTitle());
//				        JavascriptExecutor js = (JavascriptExecutor)driverObject;  
////				        js.executeScript("scrollBy(0, 4500)");  
//						final Screenshot screenshot = new AShot().coordsProvider(new WebDriverCoordsProvider()).takeScreenshot(driverObject);
//						final BufferedImage image = screenshot.getImage();
//						ImageIO.write(image, "PNG", new File(System.getProperty("user.dir") + "/ScreenshotsOutput/"
//								+ localeName + pageName + "_screenshot.png"));
////						driverObject.close();
////						ArrayList<String> tabs = new ArrayList<String> (driverObject.getWindowHandles());
////						driverObject.switchTo().window(tabs.get(0));
////						System.out.println("page title value" + driverObject.getTitle());
//
//					}
//					
//				}
				
//
				/// Browser Resize to Mobile screenshot
				System.out.println(driverObject.manage().window().getSize());
				 Dimension d = new Dimension(375,812);
				 driverObject.manage().window().setSize(d);
				 System.out.println(driverObject.manage().window().getSize());
				///
				
				
				
				
				final Screenshot screenshot = new AShot().shootingStrategy(new ViewportPastingStrategy(1000))
						.takeScreenshot(driverObject);
				final BufferedImage image = screenshot.getImage();
				ImageIO.write(image, "PNG", new File(System.getProperty("user.dir") + "/ScreenshotsOutput/" + localeName
						+ pageName + "_screenshot.png"));
				
				 Document document = new Document(PageSize.A4, 10, 10, 10, 10);
				    String input = System.getProperty("user.dir") + "/ScreenshotsOutput/" + localeName
							+ pageName + "_screenshot.png"; // .gif and .jpg are ok too!
				    String output = System.getProperty("user.dir") + "/ScreenshotsOutput/"+localeName
							+ pageName + "_screenshot.pdf";
				    try {
				      FileOutputStream fos = new FileOutputStream(output);
				      PdfWriter writer = PdfWriter.getInstance(document, fos);
				      writer.open();
				      document.open();
				      Image im = Image.getInstance(input);
//				        float scalePortrait = Math.min(PageSize.A4.getWidth() / image.getWidth(),
//				        		PageSize.A4.getHeight() / image.getHeight());
				      
				      im.scaleToFit(PageSize.A4.getWidth()/2, PageSize.A4.getHeight()/2);
				      document.add(im);
				      document.close();
				      writer.close();
				    }
				    catch (Exception e) {
				      e.printStackTrace();
				    }

				

				
				
				//PDF COde
				
		
				
				


			}
		}

	}
	
//	@Test(dataProvider = "loginCredentials")
//	public void completeJourney() {
//		
//	}
	
	

//	@AfterTest
//	private void killBrowserInstance() {
//		if (driverObject != null) {
//			driverObject.quit();
//
//		}
//
//	}
}

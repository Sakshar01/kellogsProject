package kellogsTesting;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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
//import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import java.io.FileOutputStream;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.codec.PngImage;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;

public class UrlTesting extends Utilities {
	WebDriver driverObject;
	Properties propertiesObject = null;
	HomePageObjects HomePageObject = null;

	@BeforeTest
	public void objectInitialization() throws IOException {
		driverObject = browserinitialization();
		driverObject.manage().deleteAllCookies();

		propertiesObject = propertiesFileLoad();
		driverObject.manage().window().maximize();
		HomePageObject = new HomePageObjects(driverObject);

	}

	@SuppressWarnings("deprecation")
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
		excelDataDriven dataObject = new excelDataDriven();
		for (int i = 1; i < (numberOfURLs + 1); i++) {
			URL = "";
			newString2 = "";
			String newString = "URL" + i;
			testCaseData = dataObject.getData(newString);
			int arraySize = testCaseData.size();
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

					} else if (index == 4) {
						pageName = text;
//						System.out.println(pageName);
						pageName = pageName.replace("/", "_");
//						System.out.println(pageName);

					}

				}

				String credentials = propertiesObject.getProperty("credentials");
				newString2 = URL.substring(0, indexToInsert + 1) + credentials + URL.substring(indexToInsert + 1);
				driverObject.get(newString2);
				Thread.sleep(4000L);
				final Screenshot screenshot = new AShot().shootingStrategy(new ViewportPastingStrategy(1000))
						.takeScreenshot(driverObject);
//
				final BufferedImage image = screenshot.getImage();
				ImageIO.write(image, "PNG", new File(System.getProperty("user.dir") + "/ScreenshotsOutput/" + localeName
						+ pageName + "_screenshot.png"));

				Document document = new Document(PageSize.A4, 10, 10, 10, 10);
				String input = System.getProperty("user.dir") + "/ScreenshotsOutput/" + localeName + pageName
						+ "_screenshot.png"; // .gif and .jpg are ok too!
				String output = System.getProperty("user.dir") + "/ScreenshotsOutput/" + localeName + pageName + "0"
						+ "_screenshot.pdf";
				try {
					FileOutputStream fos = new FileOutputStream(output);
					PdfWriter writer = PdfWriter.getInstance(document, fos);
					writer.open();
					document.open();
					Image im = Image.getInstance(input);

					im.scaleToFit(PageSize.A4.getWidth() / 2, PageSize.A4.getHeight() / 2);
					document.add(im);
					document.close();
					writer.close();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}

	}
}

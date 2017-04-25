package firefox;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.logging.Level;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class MovieImg {
	
	public static boolean GetFromWebByID(String imgID) throws InterruptedException {
		
		boolean result = true;
	    LoggingPreferences pref = new LoggingPreferences();
	    pref.enable(LogType.BROWSER, Level.OFF);
	    pref.enable(LogType.CLIENT, Level.OFF);
	    pref.enable(LogType.DRIVER, Level.OFF);
	    pref.enable(LogType.PERFORMANCE, Level.OFF);
	    pref.enable(LogType.PROFILER, Level.OFF);
	    pref.enable(LogType.SERVER, Level.OFF);
	    DesiredCapabilities desiredCapabilities = DesiredCapabilities.firefox();
	    desiredCapabilities.setCapability(CapabilityType.LOGGING_PREFS, pref);
	    
		String driverLocation = GetFifreFoxDriver();
		System.setProperty("webdriver.gecko.driver", driverLocation);
		BufferedImage bfImg = null;
		// Create a new instance of the Firefox driver
		WebDriver driver = null;
		WebElement element = null;
		
		try
		{
			driver = new FirefoxDriver(desiredCapabilities);
			driver.get(MovieInfo.Data.getIMGUrl() + MovieInfo.Data.getResultQueryMovieCD());
			// Find the text input element by its name
			try {
				element = driver.findElement(By.id(imgID));
			} catch (NoSuchElementException e) {

				result = false;
				return result;
			}

			URL url = new URL(element.getAttribute("src"));
			bfImg = ImageIO.read(url);

			if (MovieInfo.Data.getMovieCD() != 0) {
				ImageIO.write(bfImg, "jpg", new File("C:\\Projects\\poster\\" + MovieInfo.Data.getMovieCD() + ".jpg"));
			}

		}catch(Exception e)
		{
			e.printStackTrace();
		} finally{
			driver.close();
			Thread.sleep(1000); // modify for effect
			driver.quit();
		}
		
		return result;
	}

	
	public static void GetFromWebByID_test() throws InterruptedException {
		// Set the path for the geckodriver.exe
		
		String driverLocation = GetFifreFoxDriver();
		
		System.setProperty("webdriver.gecko.driver", driverLocation);
		
		BufferedImage bfImg = null;
		// Create a new instance of the Firefox driver
		WebDriver driver = null;
		
		try
		{

			driver = new FirefoxDriver();
			driver.get(MovieInfo.Data.getIMGUrl() + "136872");
			// Find the text input element by its name
			WebElement element = driver.findElement(By.id("targetImage"));
			URL url = new URL(element.getAttribute("src"));
			bfImg = ImageIO.read(url);
			
			ImageIO.write(bfImg, "jpg", new File("img/" + "136872" + ".jpg"));

			// Check the title of the page and image source
			System.out.println("Page title is: " + driver.getTitle());
			System.out.println("Image SRC is: " + element.getAttribute("src"));
		}catch(Exception e)
		{
			e.printStackTrace();
		} finally{

			driver.close();
			Thread.sleep(1000); // modify for effect
			driver.quit();
		}
	}
	
	private static String GetFifreFoxDriver() {
		String driverLocation = null;
		
		if(!NaverImg.AmIAtHome) {
			driverLocation = "C:\\Projects\\Dropbox\\Development\\Java\\selenium\\geckodriver\\geckodriver.exe";
		} else {			
			driverLocation = "geckodriver//geckodriver";
		}
		return driverLocation;
	}
}

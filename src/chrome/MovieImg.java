package chrome;

import java.io.File;
import java.net.URL;
import javax.imageio.ImageIO;
import org.openqa.selenium.By;
import java.awt.image.BufferedImage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.NoSuchElementException;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;

public class MovieImg {
	
	public static boolean GetFromWebByID(String imgID) throws InterruptedException {
		
		boolean result = true;
	    
		String driverLocation = GetChromeDriver();
		System.setProperty("webdriver.chrome.driver", driverLocation);
		BufferedImage bfImg = null;
		// Create a new instance of the Chrome driver
		WebDriver driver = null;
		WebElement element = null;
		
		try
		{
			driver = new ChromeDriver();
			driver.get(MovieInfo.Data.getIMGUrl() + MovieInfo.Data.getResultQueryMovieCD());
			// Find the text input element by its name
			try {
				
				element = driver.findElement(By.id(imgID));
			} catch (Exception e) {

				result = false;
				return result;
			}

			URL url = new URL(element.getAttribute("src"));
			bfImg = ImageIO.read(url);

			if (MovieInfo.Data.getMovieCD() != 0) {
				String path = MovieInfo.Data.workImagePath;
				
				if(NaverImg.AmIAtHome) {
					path = MovieInfo.Data.homeImagePath;
				}
				
				ImageIO.write(bfImg, "jpg", new File(path + MovieInfo.Data.getMovieCD() + ".jpg"));
			}

		}catch(Exception e) {
			e.printStackTrace();
		} finally{
			driver.close();
			Thread.sleep(1000); // modify for effect
			driver.quit();
		}
		
		return result;
	}

	
	public static void GetFromWebByID_test() throws InterruptedException {
		// Set the path for the chromedriver.exe
		
		String driverLocation = GetChromeDriver();
		
		System.setProperty("webdriver.chrome.driver", driverLocation);
		
		BufferedImage bfImg = null;
		// Create a new instance of the Chrome driver
		WebDriver driver = null;
		
		try
		{

			driver = new ChromeDriver();
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
	
	private static String GetChromeDriver() {
		String driverLocation = null;
		
		if(!NaverImg.AmIAtHome) {
			driverLocation = "C:\\Projects\\Dropbox\\Development\\Java\\selenium\\chromedriver\\chromedriver.exe";
		} else {			
			driverLocation = "chromedriver//chromedriver";
		}
		return driverLocation;
	}
}

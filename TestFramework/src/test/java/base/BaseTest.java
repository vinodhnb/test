package base;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public static WebDriver driver;
	public static Properties prop = new Properties();
	public static Properties loc = new Properties();
	public static FileReader fr;
	public static FileReader fr1;
	
	@BeforeTest
	public void setUp() throws IOException {
		if(driver==null) {
			fr = new FileReader(System.getProperty("user.dir")+"\\src\\test\\resources\\configfiles\\config.properties");
			fr1 = new FileReader(System.getProperty("user.dir")+"\\src\\test\\resources\\configfiles\\locators.properties");
			prop.load(fr);
			loc.load(fr1);
		}
		
		if(prop.getProperty("browser").equalsIgnoreCase("chrome")) {
			ChromeOptions options = new ChromeOptions();
			options.setPageLoadStrategy(PageLoadStrategy.EAGER);
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(options);
			driver.manage().window().maximize();
			driver.get(prop.getProperty("testurl"));
		}
		else if(prop.getProperty("browser").equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.get(prop.getProperty("testurl"));
		}
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
		//driver.close();
		System.out.println("Tear down successful");
	}
}

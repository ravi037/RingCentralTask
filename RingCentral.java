
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;


public class RingCentral {
	
	public static WebDriver driver;
	
	//initialize browser and invoke application url
	public WebDriver intializebrowser() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.get("https://www.saucedemo.com/inventory.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		return driver;
	}
	
	//Login to application
	public WebDriver login() {
		
				driver.findElement(By.id("user-name")).sendKeys("standard_user");
				driver.findElement(By.id("password")).sendKeys("secret_sauce");
				driver.findElement(By.id("login-button")).click();
				return driver;
	}
	// Filter price high to low and add all items to cart
	public WebDriver pricehightolow() {
		
				Select s=new Select(driver.findElement(By.cssSelector(".product_sort_container")));
				s.selectByVisibleText("Price (high to low)");
				List<WebElement> w=driver.findElements(By.cssSelector(".inventory_item_price"));
				for(int i=0;i<w.size();i++) {
					w.get(i).getText();
				}
				//verify price is sorted from high to low
				Assert.assertEquals(w.get(0).getText(), "$49.99");
				List<WebElement> add=driver.findElements(By.xpath("//button[@class='btn btn_primary btn_small btn_inventory']"));
				for(int j=0;j<add.size();j++) {
				driver.findElement(By.xpath("//button[@class='btn btn_primary btn_small btn_inventory']")).click();
				}
				return driver;
				
	}
	// Filter price low to high and remove added items from cart
	public WebDriver pricelowtohigh() {
		
				Select s1=new Select(driver.findElement(By.cssSelector(".product_sort_container")));
				s1.selectByVisibleText("Price (low to high)");
				List<WebElement> w1=driver.findElements(By.cssSelector(".inventory_item_price"));
				for(int i=0;i<w1.size();i++) {
					w1.get(i).getText();
				}
				
				//verify price is sorted from low to high
				Assert.assertEquals(w1.get(0).getText(), "$7.99");
				List<WebElement> remove=driver.findElements(By.xpath("//button[@class='btn btn_secondary btn_small btn_inventory']"));
				for(int j=0;j<remove.size();j++) {
				driver.findElement(By.xpath("//button[@class='btn btn_secondary btn_small btn_inventory']")).click();
				}
				return driver;
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
	
		RingCentral r=new RingCentral();
		r.intializebrowser();
		r.login();
		r.pricehightolow();
		r.pricelowtohigh();
		
		
		driver.quit();
		
		
	}

}

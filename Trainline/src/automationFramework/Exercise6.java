package automationFramework;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;


public class Exercise6 {
	
	
	WebDriver driver =  new FirefoxDriver(); 
	Homepage homepage = new Homepage(driver);
	
	//public variables
	public String URL = "https://www.thetrainline.com";
	public String homeTitle = "Trainline";
	public String fromLocation = "Brighton";
	public String toLocation = "London Bridge";
	
	
	@BeforeTest
	public void goHomepage() 
	{
		driver.get(URL);
		driver.manage().window().maximize();
	}
	
	@Test (priority = 1)
	public void verifyHomepage()
	{
		String pageTitle = driver.getTitle();
		Assert.assertTrue("Page title is incorrect", pageTitle.contains(homeTitle));
	}
	
	@Test (priority = 2)
	public void insertLocation()
	{
		
		homepage.from.sendKeys(fromLocation);
		homepage.to.sendKeys(toLocation);
	}
	
	@Test (priority = 3)
	public void insertDate()
	{
		if (homepage.oneway.isSelected())
		{
			homepage.oneway.click();
		}
		
		homepage.tomorrow.click();
		homepage.nextday.click();
	}
	
	@Test (priority = 4)
	public void selectSubmit()
	{
		homepage.submit.click();
	}
	
	@Test (priority = 5)
	public void verifyPricepage() throws InterruptedException
	{
		Thread.sleep(5000);
		if (driver.findElements(By.xpath(".//*[@id='timetable']/div[2]")).size() == 0){
			Assert.fail("Wrong page");
		}
		
	}
	
	@AfterTest
	public void finish()
	{
		driver.quit();
		System.out.println("All done");
	}
}

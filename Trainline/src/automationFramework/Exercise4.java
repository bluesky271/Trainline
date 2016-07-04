package automationFramework;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.*;

public class Exercise4 {
	public static void main(String[] args) throws Exception {		
		
	
		WebDriver driver =  new FirefoxDriver(); 
		driver.get("https://www.thetrainline.com");
		
		//Create a new homepage object, passing in our driver
		Homepage homepage;
		homepage = new Homepage(driver);	

		driver.manage().window().maximize();
		
		String pageTitle = driver.getTitle();
		Assert.assertTrue("Page title is incorrect", pageTitle.contains("Trainline"));
		
		//Interact with the from field
		homepage.from.sendKeys("Brighton");
		homepage.to.sendKeys("London Bridge");
		
		if (homepage.oneway.isSelected())
		{
			homepage.oneway.click();
		}
		
		homepage.tomorrow.click();
		homepage.nextday.click();
		homepage.submit.click();
		
		Thread.sleep(5000);
		
		if (driver.findElements(By.xpath(".//*[@id='timetable']/div[2]")).size() == 0){
			Assert.fail("Wrong page");
		}
		
		driver.close();
		
		System.out.println("All done");
	}
}

package automationFramework;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Exercise3 {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver;
		
		WebElement from;
		WebElement to;
		WebElement find;
		
		driver = new FirefoxDriver();
		driver.get("https://www.thetrainline.com");
		
		driver.manage().window().maximize();
		
		//check page if it contain the word "Trainline"
		String pageTitle = driver.getTitle();
		Assert.assertTrue("Page title is correct", pageTitle.contains("Trainline"));
		
		from = driver.findElement(By.xpath(".//*[@id='originStation']"));
		from.sendKeys("Brighton");
		
		to = driver.findElement(By.xpath(".//*[@id='destinationStation']"));
		to.sendKeys("London Bridge");
		
		//if "one way" is already selected, unselect it. 
		WebElement oneway = driver.findElement(By.xpath(".//*[@id='isOneWay']"));
		if (oneway.isSelected())
		{
			oneway.click();
		}	
		
		//click on tomorrow using full xpath
		driver.findElement(By.xpath(".//*[@id='extendedSearchForm']/div[2]/div[1]/div/div[1]/button[2]")).click();
		
		//click on next day using advanced searches: any text contain "Next day"
		driver.findElement(By.xpath(".//*[contains(text(),'Next day')]")).click();
		
		find = driver.findElement(By.xpath(".//*[@id='submitButton']"));
		find.click();
		
		Thread.sleep(5000);
		
		//check page if it contain the "timetable" element
		if (driver.findElements(By.xpath(".//*[@id='timetable']/div[2]")).size() == 0){
			Assert.fail("Wrong page");
		}
		
		driver.close();
		
		System.out.println("All done");

	}

}

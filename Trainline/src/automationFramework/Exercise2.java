package automationFramework;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Exercise2 {

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

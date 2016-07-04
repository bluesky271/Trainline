package automationFramework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;



public class Exercise1 {

	public static void main(String[] args){
		// TODO Auto-generated method stub
		WebDriver driver;
		
		WebElement from;
		WebElement to;
		WebElement find;
		
		driver = new FirefoxDriver();
		driver.get("https://www.thetrainline.com");
		
		driver.manage().window().maximize();
		
		from = driver.findElement(By.xpath(".//*[@id='originStation']"));
		from.sendKeys("Brighton");
		
		to = driver.findElement(By.xpath(".//*[@id='destinationStation']"));
		to.sendKeys("London Bridge");
		
		find = driver.findElement(By.xpath(".//*[@id='submitButton']"));
		find.click();
		
		driver.close();
		
		
		System.out.println("All done");
	}

}

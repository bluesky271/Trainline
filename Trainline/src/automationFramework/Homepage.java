package automationFramework;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;

public class Homepage {

	WebDriver driver;
	
	@FindBy(xpath=".//*[@id='originStation']")
	WebElement from;

	@FindBy(xpath=".//*[@id='destinationStation']")
	WebElement to;
	
	@FindBy(xpath=".//*[@id='submitButton']")
	WebElement find;
	
	@FindBy(xpath=".//*[@id='isOneWay']")
	WebElement oneway;
	
	@FindBy(xpath=".//*[contains(text(),'Tomorrow')]")
	WebElement tomorrow;
	
	//select Next Day	
	@FindBy(xpath=".//*[contains(text(),'Next day')]")
	WebElement nextday;	
	
	//now search
	@FindBy(xpath=".//*[@id='submitButton']")
	WebElement submit;
	
	
	public Homepage(WebDriver driver) {
		this.driver = driver;	
		PageFactory.initElements(driver, this);
      System.out.println("Homepage object has been created");
	}
	
	public void setOutDate(Integer DaysInFuture){
		Boolean datefound = false;
		SimpleDateFormat sdf;
		
		//Set the out date
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, DaysInFuture);		
		//setup the text to find in the calendar
		sdf = new SimpleDateFormat("MMMM yyyy");
		String headerToFind = sdf.format(calendar.getTime());
		sdf = new SimpleDateFormat("d");
		String dayToFind = sdf.format(calendar.getTime());
		
		while(datefound == false){			
			//get a list of all the datepicker boxes and iterate them
			List<WebElement> calendars = driver.findElements(By.xpath(".//*[@id='ui-datepicker-div']/*"));
		    for (WebElement cal: calendars) { 
		    	if (cal.getText().contains(headerToFind)){
			    	cal.findElement(By.linkText(dayToFind)).click();
		    		datefound = true;
		    		break;
		    	}	    	
		    }		    
	    	//If we didn't find the date can we click next and loop bck around again?
		    if (datefound == false){	
		    	Boolean isPresent = driver.findElements(By.xpath(".//*[@id='ui-datepicker-div']/div[2]/div/a/span")).size() > 0;
		    	if (isPresent == true){
		    		driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div[2]/div/a/span")).click();
		    	}else{
		    		break;
		    	}
		    }
		}	 
		
		if (datefound == false){
			throw new IllegalStateException("Date [" + calendar.toString() + "] could not be selected");
		}	
	}


}

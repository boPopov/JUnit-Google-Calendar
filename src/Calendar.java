import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Calendar class for testing Google Calendar functionalities
 * @author Bojan
 */

public class Calendar {
	
	/**
	 * Variables
	 * driver - WebDriver object for initialization of the browser.
	 * url - String object is a start point in the WebDriver.
	 */
	private static WebDriver driver;
	private String url;
	
	/**
	 * Constructor
	 * @param url - String object that needs to initialize the url variable
	 */
	public Calendar(String url) {
		System.setProperty("webdriver.gecko.driver",  "/home/bojan/Desktop/Firefox/geckodriver");
		driver = new FirefoxDriver();
		this.url = url;
	}
	
	/**
	 * function that sets the url into the driver.
	 * @throws InterruptedException
	 */
	public void startPage() throws InterruptedException {
		driver.get(url);
		Thread.sleep(1000);
	}
	
	/**
	 * function that clicks on the login login button
	 * @return String from the web page
	 * @throws InterruptedException
	 */
	public String clickLogin() throws InterruptedException {
		Thread.sleep(1500);
		driver.findElement(By.id("gb_70")).click();
		Thread.sleep(1500);
		return driver.findElement(By.xpath("//div[@jsname=\"tJHJj\"]")).getText().trim();
	}
	
	/**
	 * Function that returns the number of events in the calendar view
	 * @return integer
	 */
	private int getCurrentItems() {
		return driver.findElements(By.xpath("//content[@class=\"z80M1\"]")).size();
	}
	
	/**
	 * function that clicks on the settings button in the web page.
	 * @return WebElement object.
	 * @throws InterruptedException
	 */
	public int clickSettings() throws InterruptedException {
		int getCurrentItems = getCurrentItems();
		WebElement settingsButton = driver.findElement(By.xpath("//div[@aria-label=\"Settings menu\"]"));
		settingsButton.click();
		Thread.sleep(2500);
		return settingsButton.findElements(By.xpath("//content[@class=\"z80M1\"]")).size() - getCurrentItems;
	}
	
	/**
	 * function that enters the given email into the web page field for email
	 * @param email - String
	 * @return String from the web page
	 * @throws InterruptedException
	 */
	public String enterEmailAddress(String email) throws InterruptedException {
		driver.findElement(By.id("identifierId")).sendKeys(email);
		Thread.sleep(1500);
		driver.findElement(By.id("identifierNext")).click();
		Thread.sleep(1500);
		return driver.findElement(By.xpath("//div[@jsname=\"tJHJj\"]")).getText();
	}
	
	/**
	 * function that enters the password in the web page field for password
	 * @param password
	 * @return WebElement object from the page
	 * @throws InterruptedException
	 */
	public WebElement enterPassword(String password) throws InterruptedException{
		driver.findElement(By.xpath("//input[@jsname=\"YPqjbf\"]")).sendKeys("bojan281996");
		Thread.sleep(1500);
		driver.findElement(By.id("passwordNext")).click();
		Thread.sleep(1100);
		return driver.findElement(By.xpath("//a[@title=\"Google Account: Bojan Popov  \n" + 
				"(bojanpopovred@gmail.com)\"]"));
	}
	
	/**
	 * function that changes the page in the drive variable
	 * @param url - String that sets the new url in the drive variable
	 * @return - String from the web page
	 * @throws InterruptedException
	 */
	public String changePage(String url) throws InterruptedException {
		driver.get(url);
		Thread.sleep(1500);
		WebElement element = driver.findElement(By.xpath("//span[@class=\"gb_Ee gb_He\"]"));
		return (String)((JavascriptExecutor)driver).executeScript("return arguments[0].innerHTML;", element);
	}
	
	/**
	 * function that counts how many views are on the web page
	 * @return a integer number.
	 */
	public int countView() {
		return driver.findElements(By.xpath("//div[@role=\"columnheader\"]")).size();
	}
	
	/**
	 * function that changes the view of the web page.
	 * @param aria_lable_value - String with the name of the view.
	 * @throws InterruptedException
	 */
	public void changeView(String aria_lable_value) throws InterruptedException {
		driver.findElement(By.xpath("//div[@class=\"HLI7qf Cd9hpd\"]")).click();
		Thread.sleep(1500);		
		driver.findElement(By.xpath("//content[@aria-label=\""+aria_lable_value+"\"]")).click();
		Thread.sleep(1000);
	}
	
	/**
	 * Function that clicks on the show weekends button
	 * @throws InterruptedException
	 */
	public void removeWeekends() throws InterruptedException {
		Thread.sleep(2500);
		driver.findElement(By.xpath("//span[@class=\"NlWrkb snByac\"]")).click();
		Thread.sleep(1500);		
		driver.findElement(By.xpath("//content[@aria-label=\"Show weekends\"]")).click();
		Thread.sleep(1500);
	}
	
	/**
	 * Function that gets the view name
	 * @return - String
	 */
	private String getViewName() {
		return driver.findElement(By.xpath("//div[@class=\"U26fgb c7fp5b FS4hgd wXaa9 mAozAc\"]")).getText().toLowerCase();
	}
	
	/**
	 * Function that returns the active view
	 * @param viewName - String of the view name
	 * @return String
	 */
	private String setViewName(String viewName) {
		if(viewName.equals("schedule"))
			return "day";
		else if(viewName.equals("4 days"))
			return "period";
		else if(viewName.equals("week"))
			return driver.findElement(By.xpath("//div[@jsname=\"KpB2Ud\"]")).getText();
		else if(viewName.equals("day"))
			return driver.findElement(By.xpath("//div[@class=\"MmhHI KSxb4d pCcXPe\"]")).getText();
		else 
			return "";
	}
	
	/**
	 * function that goes to the previous view (day / week / month / year)
	 * @throws InterruptedException
	 */
	public void previous() throws InterruptedException {
		String view = getViewName();
		view = setViewName(view);
		Thread.sleep(2500);
		driver.findElement(By.xpath("//div[@aria-label=\"Previous "+view+"\"]")).click();
		Thread.sleep(1500);
	}
	
	/**
	 * Function that clicks on the previous button in the calendar page
	 * @return String
	 * @throws InterruptedException
	 */
	public String previousButton() throws InterruptedException {
		String view = getViewName();
		driver.findElement(By.xpath("//div[@aria-label=\"Previous "+view+"\"]")).click();
		Thread.sleep(1500);
		return setViewName(view);
	}
	
	/**
	 * Function that clicks on the next button on the calendar
	 * @return String
	 * @throws InterruptedException
	 */
	public String nextButton() throws InterruptedException {
		String view = getViewName();
		driver.findElement(By.xpath("//div[@aria-label=\"Next "+view+"\"]")).click();
		Thread.sleep(1500);
		return driver.findElement(By.xpath("//div[@jsname=\"KpB2Ud\"]")).getText();
	}
	
	/**
	 * Function that clicks on the button Create and clicks on more details where a new form is opened for displaying more data about the creating event.
	 * @return String
	 * @throws InterruptedException
	 */
	public String openingCreateEvent() throws InterruptedException {
		//Click the button
		driver.findElement(By.xpath("//button[@aria-label=\"Create\"]")).click();
		Thread.sleep(1500);
		//Click more details
		driver.findElement(By.xpath("//div[@jsname=\"rhPddf\"]")).click();
		Thread.sleep(1500);
		return driver.findElement(By.xpath("//div[@class=\"bojM1c snByac\"]")).getText();
	}
	
	/**
	 * Function that sets the date and time of the event.
	 * @param date - String of the date
	 * @param startTime - String of the start time format x:xx am/pm
	 * @param endTime - String of the end time format x:xx am/pm
	 * @throws InterruptedException 
	 */
	private void setEventDataAndTimeParameters(String date, String startTime, String endTime) throws InterruptedException {
		WebElement input_date_start_event = driver.findElement(By.id("xStDaIn"));
		input_date_start_event.click();
		input_date_start_event.sendKeys(date);
		WebElement input_date_end_event = driver.findElement(By.id("xEnDaIn"));
		input_date_end_event.clear();
		input_date_end_event.sendKeys(date);
		//Time setup
		WebElement input_time_start_event = driver.findElement(By.id("xStTiIn"));
		input_time_start_event.click();
		input_time_start_event.sendKeys(startTime);
		Thread.sleep(1500);
		WebElement input_time_end_event = driver.findElement(By.id("xEnTiIn"));
		input_time_end_event.click();
		input_time_end_event.sendKeys(endTime);
		Thread.sleep(1500);
	}
	
	/**
	 * Function that enters the parameters in the create event form
	 * @param date - String of the date
	 * @param time_start - String starting time of the event
	 * @param time_end - String end time of the event
	 * @param name - String name of the event
	 * @throws InterruptedException
	 */
	public void enterParameters(String date, String time_start, String time_end, String name) throws InterruptedException {
		//Name of the event
		driver.findElement(By.id("xTiIn")).sendKeys(name);
		Thread.sleep(1500);
		//Date setup
		setEventDataAndTimeParameters(date, time_start, time_end);
	}
	
	/**
	 * Function that sets the rest of the parameters in the create event form
	 * @throws InterruptedException
	 */
	public void restParametersSettings() throws InterruptedException {
		//Color
		driver.findElement(By.xpath("//div[@aria-label=\"Calendar colour, event colour\"]")).click();
		int color = getRandomColor();
		Thread.sleep(500);
		driver.findElement(By.xpath("//div[@data-color-index=\""+ color +"\"]")).click();
		Thread.sleep(1500);
		//Save event
		saveEvent();
	}
	
	/**
	 * Returns the number of events in the current view
	 * @return integer
	 */
	public int getNumberOfEvents() {
		return driver.findElements(By.xpath("//div[@jscontroller=\"Bo3nHd\"]")).size();
	}
	
	/*
	 * Function that return a random color
	 */
	private int getRandomColor() {
		Random r = new Random();
		int color = -1;
		int number = r.nextInt(11);
		if(number == 0)
			color=-1;
		else if(number == 1)
			color=68;
		else if(number == 2)
			color=70;
		else if(number == 3)
			color=78;
		else if(number == 4)
			color=79;
		else if(number == 5)
			color=74;
		else if(number == 6)
			color=80;
		else if(number == 7)
			color=82;
		else if(number == 8)
			color=83;
		else if(number == 9)
			color=89;
		else if(number == 10)
			color=85;
		
		return color;
	}
	
	/**
	 * Function that removes the notifications in the create event form and adds a new one
	 * @return integer number of newly added notifications
	 * @throws InterruptedException
	 */
	public int numberOfNotifications() throws InterruptedException {
		List<WebElement> notification_buttons = driver.findElements(By.xpath("//div[@aria-label=\"Remove notification\"]"));
		notification_buttons.get(1).click();
		notification_buttons.get(0).click();
		Thread.sleep(1500);
		//Add
		driver.findElement(By.xpath("//div[@aria-label=\"Add notification\"]")).click();
		Thread.sleep(1500);
		return driver.findElements(By.xpath("//div[@aria-label=\"Remove notification\"]")).size();
	}
	
	/**
	 * Function that clicks on the search button, enters a name and makes a list of existing names with that name
	 * @param eventName - String of the events name
	 * @throws InterruptedException
	 */
	private void searchEvent(String eventName) throws InterruptedException {
		driver.findElement(By.xpath("//div[@class=\"d6McF\"]")).click();
		Thread.sleep(1500);
		WebElement search = driver.findElement(By.xpath("//input[@aria-label=\"Search\"]"));
		search.click();
		search.click();
		search.sendKeys(eventName);
		Thread.sleep(1500);
		//Button search is clicked
		driver.findElement(By.xpath("//button[@aria-label=\"Search\"]")).click();
		Thread.sleep(1500);
		List<WebElement> eventsWithSameName = driver.findElements(By.xpath("//div[@class=\"L1Ysrb\"]"));
		eventsWithSameName.get(0).click();
		Thread.sleep(500);
	}
	
	/**
	 * Function that removes an event
	 * @param event - String name of the event
	 * @throws InterruptedException
	 */
	public void removeEvent(String event) throws InterruptedException {
		searchEvent(event);
		driver.findElement(By.xpath("//div[@aria-label=\"Delete event\"]")).click();
		Thread.sleep(1500);
		driver.findElement(By.xpath("//button[@aria-label=\"Clear search\"]")).click();
		Thread.sleep(250);
		goBackButton();
	}
	
	/**
	 * Function that clicks the back button, once its done with the event search
	 * @throws InterruptedException
	 */
	public void goBackButton() throws InterruptedException {
		driver.findElement(By.xpath("//div[@aria-label=\"Go back\"]")).click();
		Thread.sleep(1500);
	}
	
	/**
	 * Mock function that returns the number of calendars imported
	 * @return integer
	 */
	public int getNumberOfItemsInMyCalendar(){
		WebElement divElement = driver.findElement(By.xpath("//div[@jsname=\"YfRV3e\"]")); //Number of calendars
		return divElement.findElements(By.xpath("//div[@class=\"XXcuqd\"]")).size(); //Each calendar involved
	}
	
	/**
	 * Function that clicks on my calendar button
	 * @throws InterruptedException
	 */
	public void clickMyCalendars() throws InterruptedException {
		driver.findElement(By.xpath("//div[@aria-controls=\"dws12b\"]")).click();	
		Thread.sleep(1000);
	}
	
	/**
	 * Function that clicks on the other calendar button
	 * @throws InterruptedException
	 */
	public void clickOtherCalendars() throws InterruptedException {
		driver.findElement(By.xpath("//div[@aria-controls=\"tkQpTb\"]")).click();
		Thread.sleep(1000);
	}
	
	/**
	 * Mock method call for adding new calendar in myCalendar list
	 * @throws InterruptedException 
	 */
	public void addCalendar(String emailCalendar) throws InterruptedException {
		WebElement element = driver.findElement(By.xpath("//input[@class=\"whsOnd zHQkBf\"]"));
		element.click();
		Thread.sleep(110);
		element.sendKeys(emailCalendar);
		Thread.sleep(250);
		String email = "\"" + emailCalendar + "\"";
		driver.findElement(By.xpath("//div[@data-email=" + email + "]")).click();
		Thread.sleep(300);
		driver.findElement(By.xpath("//span[@class=\"NPEfkd RveJvd snByac\"]")).click();
		Thread.sleep(1000);
	}
	
	/**
	 * Mock method that calls getNumberOfItemsInMyCalendar() method
	 * @return integer
	 */
	public int getNumberOfCalendarsMyCalendars() {
		return getNumberOfItemsInMyCalendar();
	}
	
	/**
	 * Mock method, Warning not implemented cause arial-label is missing in the page
	 * @param event - String name of the events
	 */
	public void disableEvents(String event) {
		
	}
	
	/**
	 * Function that changes the view into year or month depending on the input
	 * @param view - String name of the view
	 * @return String for validation
	 * @throws InterruptedException
	 */
	public String changeBiggerView(String view) throws InterruptedException {
		changeView(view);
		return driver.findElement(By.xpath("//div[@class=\"BXL82c\"]")).getText().trim();
	}
	
	/**
	 * Function that changes the view of the smaller calendar
	 * @throws InterruptedException
	 */
	public void miniCalendarPrevious() throws InterruptedException {
		driver.findElement(By.xpath("//div[@aria-label=\"Previous month\"]")).click();
		Thread.sleep(1000);
	}
	
	/**
	 * Function that changes the view of the smaller calendar
	 * @throws InterruptedException
	 */
	public void miniCalendarNext() throws InterruptedException {
		driver.findElement(By.xpath("//div[@aria-label=\"Next month\"]")).click();
		Thread.sleep(1000);
	}
	
	/**
	 * Function that returns the current month displayed on the smaller calendar
	 * @return - String name of month
	 */
	public String getMiniCalendarMonth() {
		return driver.findElement(By.xpath("//span[@jsname=\"B1A7Xe\"]")).getText();
	}
	
	/**
	 * Function that clicks on the save button
	 * @throws InterruptedException
	 */
	public void saveEvent() throws InterruptedException {
		//Save event
		driver.findElement(By.xpath("//div[@aria-label=\"Save\"]")).click();
		Thread.sleep(1500);
	}
	
	/**
	 * Function that edits an existing event
	 * @param eventName
	 * @throws InterruptedException
	 */
	public void editEvent(String eventName) throws InterruptedException {
		searchEvent(eventName);
		driver.findElement(By.xpath("//div[@aria-label=\"Edit event\"]")).click();
		Thread.sleep(1500);
	}
	
	/**
	 * Function that changes the date and time of an existing event
	 * @param date - String of the date
	 * @param startTime - String of the starting time of the event format x:xx am/pm
	 * @param endTime - String of the ending time fo the event format x:xx am/pm
	 * @throws InterruptedException
	 */
	public void changeDateAndTime(String date, String startTime, String endTime) throws InterruptedException {
		//Date setup
		setEventDataAndTimeParameters(date, startTime, endTime);
	}
	
	/*
	 * Function that logs out the user.
	 */
	public String logout() throws InterruptedException {
		driver.findElement(By.xpath("//a[@class=\"gb_b gb_hb gb_R\"]")).click();
		Thread.sleep(1900);
		driver.findElement(By.xpath("//a[@id=\"gb_71\"]")).click();
		Thread.sleep(1900);
		return driver.findElement(By.xpath("//div[@id=\"profileIdentifier\"]")).getText();
	}
	
	/*
	 * function that closes the drive object.
	 */
	public void closeDriver() {
		driver.close();
	}
	
	/**
	 * Getter for the driver
	 * @return WebDriver object
	 */
	public WebDriver getDriver() {
		return driver;
	}
	
}

import static org.junit.Assert.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.Mockito;

public class CalendarTest {
	
	@Mock
	static DAOCalendar dao;
	@Mock
	static DAOEvents event;
	
	static Calendar c;
	static TestService service;
	private static int numberOfEventOnCalendar;
	
	@BeforeAll
	public static void start() throws InterruptedException {
		System.out.println("Starting point");
		service = new TestService();
		c = new Calendar("https://www.google.com/?hl=en");
		c.startPage();
	}
	
	@Test
	public void testaa() throws InterruptedException {
		String[] return_login_string_split  = c.clickLogin().split("\n");
		assertTrue((return_login_string_split[0] + " " + return_login_string_split[1]).equals("Sign in with your Google Account"));
	}

	@Test
	public void testab() throws InterruptedException {
		String email = "yourEmail@gmail.com";
		String string = c.enterEmailAddress(email);
		String[] return_password_string_split = string.split("\n");
		assertTrue((return_password_string_split[0] + " " + return_password_string_split[1]).equals("Welcome "+email));
	}
	
	@Test
	public void testac() throws InterruptedException {
		assertTrue(c.enterPassword("password_here").isEnabled());
	}
	
	@Test
	public void testad() throws InterruptedException {
		assertTrue(c.changePage("https://calendar.google.com/calendar/r?pli=1").equals("Calendar"));
	}
	
	@ParameterizedTest
	@ValueSource(strings= {"Month M:February 2019", "Year Y:2019"})
	public void testba(String rawString) throws InterruptedException {
		String[] rawArray = rawString.split(":");
		assertEquals(rawArray[1].trim(), c.changeBiggerView(rawArray[0]));
	}

	@ParameterizedTest
	@ValueSource(strings = {"Day D:1", "4 days X:4", "Week W:5", "Week W:7"})
	public void testbb(String raw_data) throws InterruptedException { //Works
		String[] raw_list = raw_data.split(":");
		String view = raw_list[0];
		boolean changed = false;
		int days = Integer.parseInt(raw_list[1]);
		c.changeView(view);
		if(view.equals("Week W") && days == 5) {
			c.removeWeekends();
			changed = true;
		}
		assertEquals(c.countView(), days);
		if(changed) {
			changed = false;
			c.removeWeekends();
		}
	}
	
	@Test
	public void testbc() throws InterruptedException {
		assertEquals(7, c.clickSettings());
	}
	
	@Test
	public void testbd() throws NumberFormatException, InterruptedException { //Works
		int expectedDay = service.getFirstDayOfCurrentWeek() - 7;
		if(expectedDay < 1)
			expectedDay = service.getMonthBefore(expectedDay);
		assertEquals(Integer.parseInt(c.previousButton()), expectedDay);
		c.nextButton();
	}
	
	@Test
	public void testbe() throws NumberFormatException, InterruptedException { //Works
		assertEquals(service.getFirstDayOfCurrentWeek() + 7, Integer.parseInt(c.nextButton()));
		c.previousButton();
	}
	
	@Test
	public void testbf() throws InterruptedException {
		numberOfEventOnCalendar = c.getNumberOfEvents();
		assertEquals("Add title", c.openingCreateEvent());
	}
	
	@Test
	public void testbg() throws InterruptedException {
		c.enterParameters("7 Feb 2019","2:30pm","9:35pm","My free time");
		assertEquals(1,c.numberOfNotifications());
	}
	
	@Test
	public void testbh() throws InterruptedException {
		c.restParametersSettings();
		assertEquals(c.getNumberOfEvents() - 1, numberOfEventOnCalendar);
	}
	
	@Test
	public void testca() throws InterruptedException {
		numberOfEventOnCalendar = c.getNumberOfEvents();
		c.removeEvent("My free time");
		assertEquals(c.getNumberOfEvents(), numberOfEventOnCalendar - 1);
	}
	
	@Test
	public void testda() throws InterruptedException {
		int items = c.getNumberOfItemsInMyCalendar();
		c.clickMyCalendars();
		assertEquals(items - 4, c.getNumberOfItemsInMyCalendar());
		c.clickMyCalendars();
	}
	
	@Test
	public void testdb() throws InterruptedException {
		int items = c.getNumberOfItemsInMyCalendar();
		c.clickOtherCalendars();
		assertEquals(items - 1, c.getNumberOfItemsInMyCalendar());
		c.clickOtherCalendars();
	}
	
	@Test
	public void testea() throws InterruptedException {
		dao = Mockito.mock(DAOCalendar.class);
		DAOServiceCalendar daoService = new DAOServiceCalendar(dao);
		DAOServiceCalendar spy = Mockito.spy(daoService);
		Mockito.doNothing().when(spy).addCalendar(Mockito.isA(String.class));
			Mockito.when(dao.returnNumberOfCalendars()).thenReturn(6);
		dao.addCalendar("dorian_dzugovski@live.com");
		assertEquals(6, dao.returnNumberOfCalendars());
	}
	
	@Test
	public void testeb() {
		event = Mockito.mock(DAOEvents.class);
		DAOServiceEvents daoService = new DAOServiceEvents(event);
		DAOServiceEvents spy = Mockito.spy(daoService);
		Mockito.doNothing().when(spy).disabledEvent(Mockito.isA(String.class));
		Mockito.when(event.numberOfEvents()).thenReturn(3);
		event.disabledEvent("Birthdays");
		assertEquals(3, event.numberOfEvents());
	}
	
	@Test
	public void testec() {
		event = Mockito.mock(DAOEvents.class);
		DAOServiceEvents daoService = new DAOServiceEvents(event);
		DAOServiceEvents spy = Mockito.spy(daoService);
		Mockito.doNothing().when(spy).disabledEvent(Mockito.isA(String.class));
		Mockito.when(event.numberOfEvents()).thenReturn(4);
		event.disabledEvent("Holidays");
		assertEquals(4, event.numberOfEvents());
	}
	
	@Test
	public void testfa() throws InterruptedException {
		c.miniCalendarPrevious();
		assertEquals("January 2019", c.getMiniCalendarMonth().trim());
		c.miniCalendarNext();
	}
	
	@Test
	public void testfb() throws InterruptedException {
		c.miniCalendarNext();
		assertEquals("March 2019", c.getMiniCalendarMonth().trim());
		c.miniCalendarPrevious();
	}
	
	/**
	 * Change the events parameters of an existing event.
	 * Move it to next week
	 * @throws InterruptedException 
	 */
	@Test
	public void testfc() throws InterruptedException {
		c.nextButton();
		int numberOfEvents = c.getNumberOfEvents();
		c.previousButton();
		c.editEvent("Test Event");
		c.changeDateAndTime("15 Feb 2019", "1:55pm", "1:15am");
		c.saveEvent();
		c.goBackButton();
		c.nextButton();
		assertEquals(numberOfEvents + 2 , c.getNumberOfEvents());
	}
	
	/**
	 * Function that logs out the user
	 */
	@Test
	public void testga() throws InterruptedException {
		assertTrue(c.logout().equals("bojanpopovred@gmail.com"));
	}
	
	@AfterAll
	public static void end() throws InterruptedException {
		c.closeDriver();
		System.out.println("End point");
	}

}

public class DAOCalendar{
	
	private Calendar calendarService;
	
	public DAOCalendar(Calendar service) {
		this.calendarService = service; 
	}

	/*@Override
	public void addCalendar(String emailCalendar) throws InterruptedException {
		calendarService.addCalendar(emailCalendar);
	}*/

	public int returnNumberOfCalendars() {
		return calendarService.getNumberOfCalendarsMyCalendars();
	}

	public void addCalendar(String emailCalendar) throws InterruptedException {
		calendarService.addCalendar(emailCalendar);
	}
	
}

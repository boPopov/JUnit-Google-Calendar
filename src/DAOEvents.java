
public class DAOEvents {
	
	private Calendar calendar;
	
	public DAOEvents(Calendar calendar) {
		this.calendar= calendar;
	}
	
	public void disabledEvent(String event) {
		calendar.disableEvents(event);
	}
	
	public int numberOfEvents() {
		return calendar.getNumberOfCalendarsMyCalendars();
	}
	
}
